package org.intelliliu.nikita.builder.impl;

import com.google.protobuf.Message;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import org.intelliliu.nikita.builder.Builder;

public class ShortBuilder extends NullDescriptorBuilder implements Builder {
    public Class clazz;

    public ShortBuilder(Class clazz) {
        this.clazz = clazz;
    }

    public Message buildMessage(Descriptor descriptor, Object object) {
        return null;
    }

    public void buildMessageField(Message.Builder dynamicBuilder, FieldDescriptor fieldDescritptor, Object value) {
        Short shortValue = (Short) value;
        dynamicBuilder.setField(fieldDescritptor, shortValue.intValue());
    }

    public Short buildObject(Object message) {
        return (Short) message;
    }

}
