package org.intelliliu.nikita.builder.impl;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Message;
import org.intelliliu.nikita.builder.Builder;

public class DefaultBuilder extends NullDescriptorBuilder implements Builder {

    public Class clazz;

    public DefaultBuilder(Class clazz) {
        this.clazz = clazz;
    }

    public Message buildMessage(Descriptor descriptor, Object object) {
        return null;
    }

    public void buildMessageField(Message.Builder dynamicBuilder, FieldDescriptor fieldDescritptor, Object value) {
        dynamicBuilder.setField(fieldDescritptor, value);
    }

    public Object buildObject(Object message) {
        return message;
    }
}
