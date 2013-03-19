package org.intelliliu.nikita.context;

import com.google.protobuf.DynamicMessage;
import com.google.protobuf.Message;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import org.intelliliu.nikita.builder.MessageBuilder;

public class SerializationContext {

    private BuilderContext builderContext;

    public SerializationContext(BuilderContext builderContext) {
        this.builderContext = builderContext;
    }

    public Message objectToSbMessage(final Object object) {
        Descriptor descriptor = builderContext.descriptorFromClass(object.getClass());
        MessageBuilder builder = builderContext.getObjectDictionary().getBuilder(object.getClass());
        DynamicMessage msg= (DynamicMessage) builder.buildMessage(descriptor, object);
        return msg;
    }

    public Message objectToSbMessage(final Object object,Class clazz) {
        Descriptor descriptor = builderContext.descriptorFromClass(clazz);
        MessageBuilder builder = builderContext.getObjectDictionary().getBuilder(clazz);
        DynamicMessage msg= (DynamicMessage) builder.buildMessage(descriptor, object);
        return msg;
    }

    public BuilderContext getBuilderContext() {
        return builderContext;
    }

    public void setBuilderContext(BuilderContext builderContext) {
        this.builderContext = builderContext;
    }

    public void fieldToMessage(Message.Builder dynamicBuilder, FieldDescriptor fieldDescritptor, Object value) {
        if (value == null) {
            return;
        }

        java.lang.reflect.Type type = fieldDescritptor.toProto().getFieldType() != null ? fieldDescritptor.toProto().getFieldType() : value.getClass();
        MessageBuilder builder = builderContext.getObjectDictionary().getBuilder(type);
        builder.buildMessageField(dynamicBuilder, fieldDescritptor, value);
    }
}
