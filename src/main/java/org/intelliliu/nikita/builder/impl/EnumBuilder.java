package org.intelliliu.nikita.builder.impl;


import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Message;
import org.intelliliu.nikita.context.DescriptorGenerateContext;
import org.intelliliu.nikita.builder.Builder;

public class EnumBuilder implements Builder {
    private Class clazz;

    public EnumBuilder(Class clazz) {
        this.clazz = clazz;
    }

    public Message buildMessage(Descriptor descriptor, Object object) {
        return null;
    }

    public void buildMessageField(com.google.protobuf.Message.Builder dynamicBuilder, FieldDescriptor fieldDescritptor, Object value) {

    }

    public Object buildObject(Object message) {
        return null;
    }

    public Descriptor buildDescriptor(DescriptorGenerateContext context, Class Clazz) {
        // TODO Auto-generated method stub
        return null;
    }


}
