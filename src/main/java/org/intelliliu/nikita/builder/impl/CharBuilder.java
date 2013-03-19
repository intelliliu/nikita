package org.intelliliu.nikita.builder.impl;

import com.google.protobuf.Message;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import org.intelliliu.nikita.builder.Builder;

public class CharBuilder extends NullDescriptorBuilder implements Builder {
    public Class clazz;

    public CharBuilder(Class clazz) {
        this.clazz = clazz;
    }

    public Message buildMessage(Descriptor descriptor, Object object) {
        return null;
    }

    public void buildMessageField(Message.Builder dynamicBuilder, FieldDescriptor fieldDescritptor, Object value) {
        char c = (Character) value;
        dynamicBuilder.setField(fieldDescritptor, (int) c);
    }

    public Byte buildObject(Object message) {
        return (Byte) message;
    }

}