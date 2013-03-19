package org.intelliliu.nikita.builder;

import com.google.protobuf.Descriptors.Descriptor;
import org.intelliliu.nikita.context.DescriptorGenerateContext;

public interface DescriptorBuilder {

    Descriptor buildDescriptor(DescriptorGenerateContext context, Class Clazz);
}
