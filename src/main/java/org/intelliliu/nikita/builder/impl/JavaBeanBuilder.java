package org.intelliliu.nikita.builder.impl;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.protobuf.DescriptorProtos.DescriptorProto;
import com.google.protobuf.DescriptorProtos.FileDescriptorProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.DescriptorValidationException;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.DynamicMessage;
import com.google.protobuf.Message;
import org.intelliliu.nikita.context.BuilderContext;
import org.intelliliu.nikita.context.DescriptorGenerateContext;
import org.intelliliu.nikita.builder.Builder;
import org.intelliliu.nikita.exception.PBException;


public class JavaBeanBuilder implements Builder {
    static private final Log logger = LogFactory.getLog(JavaBeanBuilder.class);
    public PropertyDescriptor[] propertyDescriptors;
    protected Class clazz;

    protected JavaBeanBuilder() {

    }

    public JavaBeanBuilder(Class clazz) throws IntrospectionException {
        PropertyDescriptor[] temp = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
        List<PropertyDescriptor> properties = new ArrayList<PropertyDescriptor>();
        for (PropertyDescriptor propertyDescriptor : temp) {
            if (propertyDescriptor.getReadMethod() != null && propertyDescriptor.getWriteMethod() != null) {
                properties.add(propertyDescriptor);
            }
        }
        propertyDescriptors = properties.toArray(new PropertyDescriptor[]{});
        this.clazz = clazz;
    }

    public Message buildMessage(Descriptor descriptor, Object object) {
        DynamicMessage.Builder dynamicBuilder = DynamicMessage.newBuilder(descriptor);
        List<FieldDescriptor> fieldDescritptors = descriptor.getFields();
        Map<String, PropertyDescriptor> properties = new HashMap<String, PropertyDescriptor>();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            properties.put(propertyDescriptor.getName(), propertyDescriptor);
        }

        for (FieldDescriptor fieldDescritptor : fieldDescritptors) {
            String fieldName = fieldDescritptor.getName();
            PropertyDescriptor propertyDescriptor = null;
            try {
                propertyDescriptor = properties.get(fieldName);
                logger.info("OBJECT:"+object);
                logger.info(descriptor.getFullName()+"  "+fieldName);
                Object fieldValue = propertyDescriptor.getReadMethod().invoke(object);
                BuilderContext.getContext().getSerializationContext().fieldToMessage(dynamicBuilder, fieldDescritptor, fieldValue);
            } catch (Exception e) {
                logger.error("为class=" + descriptor.getFullName() + "的字段:" + fieldName + " 构建messager失败！", e);
            }
        }

        return dynamicBuilder.build();
    }

    public Object buildObject(Object message) {
        Message msg = (Message) message;
        Map<Descriptors.FieldDescriptor, Object> map = ((Message) message).getAllFields();

        Map<String, PropertyDescriptor> properties = new HashMap<String, PropertyDescriptor>();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            properties.put(propertyDescriptor.getName(), propertyDescriptor);
        }

        Object object = null;
        try {
            object = clazz.newInstance();
            for (Entry<Descriptors.FieldDescriptor, Object> entry : map.entrySet()) {
                Descriptors.FieldDescriptor fieldDescritptor = entry.getKey();
                String tyName = fieldDescritptor.toProto().getTypeName();
                int gang = tyName.indexOf("InnerClass_");
                Object value = entry.getValue();
                PropertyDescriptor propertyDescriptor=null;
                Builder fieldBuilder;
                if (gang > -1) {//内部类
                    propertyDescriptor = properties.get(tyName.substring(gang + 11));
                    fieldBuilder = BuilderContext.getContext().getObjectDictionary()
                            .getBuilder(propertyDescriptor.getReadMethod().getReturnType());
                } else {
                    java.lang.reflect.Type javaType = fieldDescritptor.toProto().getFieldType();
                    propertyDescriptor = properties.get(fieldDescritptor.getName());
                    fieldBuilder = BuilderContext.getContext().getObjectDictionary().getBuilder(javaType);
                }
                propertyDescriptor.getWriteMethod().invoke(object, fieldBuilder.buildObject(value));//若连神功，必先自宫
            }

        } catch (Exception e) {
            logger.error("反序列化:" + clazz.getName() + "ʱ时出错!", e);
        }

        return object;
    }

    public Descriptor buildDescriptor(DescriptorGenerateContext context, Class clazz) {

        String name = clazz.getName();
        String simpleName = clazz.getSimpleName();
        String packageName = clazz.getPackage().getName();

        DescriptorProto.Builder descriptorProtoBuilder = DescriptorProto.newBuilder().setName(simpleName);
        FileDescriptorProto.Builder fileDescriptorProtoBuilder = FileDescriptorProto.newBuilder().setName(name);

        fileDescriptorProtoBuilder.setPackage(packageName);

        List<FileDescriptor> dependencies = new ArrayList<FileDescriptor>();

        int length = propertyDescriptors.length;
        for (int i = 0; i < length; i++) {
            PropertyDescriptor propertyDescriptor = propertyDescriptors[i];
            try {
                logger.info("为java class:" + name + " 字段类型：" + propertyDescriptor.getPropertyType() + " 字段名字" + propertyDescriptor.getName() + "构建PB描述!");
                context.fieldToFieldDescriptor(descriptorProtoBuilder, dependencies, i + 1, propertyDescriptor);
            } catch (Exception e) {
                logger.error("失败", e);
            }
        }

        Map<String, FileDescriptor> map = new HashMap<String, FileDescriptor>();

        for (FileDescriptor fileDescriptor : dependencies) {
            map.put(fileDescriptor.getName(), fileDescriptor);
        }

        FileDescriptor[] deps = new FileDescriptor[map.size()];
        int i = 0;
        for (Entry<String, FileDescriptor> entry : map.entrySet()) {
            deps[i++] = entry.getValue();
            fileDescriptorProtoBuilder.addDependency(entry.getKey());
        }

        DescriptorProto descriptorProto = descriptorProtoBuilder.build();
        fileDescriptorProtoBuilder.addMessageType(descriptorProto);
        FileDescriptorProto fileDescriptorProto = fileDescriptorProtoBuilder.build();

        try {
            FileDescriptor fileDescritptor = FileDescriptor.buildFrom(fileDescriptorProto, deps);
            context.addFileDescritptor(fileDescritptor);

            return fileDescritptor.findMessageTypeByName(simpleName);
        } catch (DescriptorValidationException e) {
            logger.error(e);
        }

        return null;

    }

    public static JavaBeanBuilder create(final Class clazz) {
        try {
            return new JavaBeanBuilder(clazz);
        } catch (IntrospectionException e) {
            throw new PBException("构建builder失败", e);
        }
    }

    public void buildMessageField(Message.Builder dynamicBuilder, FieldDescriptor fieldDescritptor, Object value) {
        dynamicBuilder.setField(fieldDescritptor, buildMessage(fieldDescritptor.getMessageType(), value));
    }

}