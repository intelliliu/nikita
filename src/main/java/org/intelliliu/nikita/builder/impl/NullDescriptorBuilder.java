package org.intelliliu.nikita.builder.impl;

import com.google.protobuf.Descriptors.Descriptor;
import org.intelliliu.nikita.context.DescriptorGenerateContext;
import org.intelliliu.nikita.builder.Builder;

public abstract class NullDescriptorBuilder implements Builder {
    public Descriptor buildDescriptor(DescriptorGenerateContext context, Class Clazz) {
        return null;
    }
}
