package org.intelliliu.nikita.context;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.google.protobuf.DescriptorProtos.FileDescriptorProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.DescriptorValidationException;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.MethodDescriptor;
import com.google.protobuf.Descriptors.ServiceDescriptor;
import com.google.protobuf.DynamicMessage;
import com.google.protobuf.Message;
import org.intelliliu.nikita.dictionary.ObjectDictionary;
import org.intelliliu.nikita.dictionary.TypeDictionary;
import org.intelliliu.nikita.builder.MessageBuilder;

public class BuilderContext {

    private final static BuilderContext INSTANCE = new BuilderContext();

    public static BuilderContext getContext() {
        return INSTANCE;
    }

    public static final Message EMPTY_MESSAGE = DynamicMessage.getDefaultInstance(null);

    private TypeDictionary _typeDictionary = new TypeDictionary();
    /**
     * 缓存clazz builder
     */
    private ObjectDictionary _objectDictionary = new ObjectDictionary();
    private SerializationContext serializationContext = new SerializationContext(this);
    private DeserializationContext deserializationContext = new DeserializationContext(this);
    private DescriptorGenerateContext descriptorGenerateContext = new DescriptorGenerateContext(this);
    /**
     * 缓存clazz descriptor:message=builder(descriptor,object)
     */
    private ConcurrentMap<String, FileDescriptor> descriptorDictionary=new ConcurrentHashMap<String, FileDescriptor>();
    private FileDescriptor rootFileDescriptor;
    public ConcurrentHashMap<String, String> methodAsService = new ConcurrentHashMap<String, String>();
    public ConcurrentHashMap<String, String> serviceAsMethod = new ConcurrentHashMap<String, String>();

    public Set<Class> generatedClassSet = new HashSet<Class>();

    private BuilderContext() {
        FileDescriptorProto proto = FileDescriptorProto.newBuilder().build();
        try {
            rootFileDescriptor = FileDescriptor.buildFrom(proto, new FileDescriptor[]
                    {});
        } catch (DescriptorValidationException e) {
            e.printStackTrace();
        }

    }

    public FileDescriptor getRootFileDescriptor() {
        return rootFileDescriptor;
    }

    public SerializationContext getSerializationContext() {
        return serializationContext;
    }

    public DeserializationContext getDeserializationContext() {
        return deserializationContext;
    }

    public DescriptorGenerateContext getDescriptorGenerateContex() {
        return descriptorGenerateContext;
    }

    public void setRootFileDescriptor(FileDescriptor rootFileDescriptor) {
        this.rootFileDescriptor = rootFileDescriptor;
    }

    public TypeDictionary getTypeDictionary() {
        return _typeDictionary;
    }

    public void setTypeDictionary(TypeDictionary typeDictionary) {
        _typeDictionary = typeDictionary;
    }

    public ObjectDictionary getObjectDictionary() {
        return _objectDictionary;
    }

    public void setObjectDictionary(ObjectDictionary objectDictionary) {

        _objectDictionary = objectDictionary;
    }

    public ConcurrentMap<String, FileDescriptor> getDescriptorDictionary() {
        return descriptorDictionary;
    }

    public void setDescriptorDictionary(ConcurrentMap<String, FileDescriptor> descriptorDictionary) {
        this.descriptorDictionary = descriptorDictionary;
    }

    public byte[] toByteArray(Message msg) {
        return msg.toByteArray();
    }

    public Object objectFromSbMessage(java.lang.reflect.Type fieldType, Object message) {
        return deserializationContext.messageToObject(fieldType, message);
    }

    public Message.Builder setMessageFiled(Message.Builder builder, FieldDescriptor responseFieldDescriptor, Object appResponse) throws Exception {
        BuilderContext context = BuilderContext.getContext();
        MessageBuilder messageBuilder = context.getObjectDictionary().getBuilder(responseFieldDescriptor.toProto().getFieldType());
        messageBuilder.buildMessageField(builder, responseFieldDescriptor, appResponse);
        return builder;
    }

    public Descriptor descriptorFromClass(Class<?> clazz) {
        return descriptorGenerateContext.descriptorFromClass(clazz);
    }

    public ServiceDescriptor serviceFromInterface(Class<?> clazz) {
        DescriptorGenerateContext descriptorGenerateContext = new DescriptorGenerateContext(this);
        return descriptorGenerateContext.serviceFromInterface(clazz);
    }

    public MethodDescriptor methodDescriptor(String service, String mthod) {
//        List<FileDescriptor> dependencies = rootFileDescriptor.getDependencies();
        FileDescriptor descriptor = null;
//        for (FileDescriptor fileDescriptor : dependencies) {
//            String temp = fileDescriptor.getName();
//            if (temp.equals(service)) {
//                descriptor = fileDescriptor;
//                break;
//            }
//        }
        descriptor=descriptorDictionary.get(service);
        ServiceDescriptor serviceDescriptor = descriptor.findServiceByName(descriptor.getName().replace(descriptor.getPackage() + ".", ""));
        return serviceDescriptor.findMethodByName(mthod);
    }

}
