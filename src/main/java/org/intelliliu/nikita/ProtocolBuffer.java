package org.intelliliu.nikita;

import com.google.protobuf.DynamicMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import org.intelliliu.nikita.context.BuilderContext;
import org.intelliliu.nikita.context.DescriptorGenerateContext;
import org.intelliliu.nikita.context.DeserializationContext;
import org.intelliliu.nikita.context.SerializationContext;

public class ProtocolBuffer {
    private static DescriptorGenerateContext descGener = BuilderContext.getContext().getDescriptorGenerateContex();
    private static SerializationContext seriaContext = BuilderContext.getContext().getSerializationContext();
    private static DeserializationContext deseriaContext = BuilderContext.getContext().getDeserializationContext();

    /**
     * @param obj 必须提供javabean式的getset方法
     * @return
     */
    public static byte[] toBinaryStream(Object obj) {
        return seriaContext.objectToSbMessage(obj).toByteArray();
    }

    public static byte[] toBinaryStream(Object obj,Class clazz) {
        return seriaContext.objectToSbMessage(obj,clazz).toByteArray();
    }

    /**
     * @param clazz  pb的精髓所在，反序列化时必须提供对象描述，从而使二进制流里仅包含数据
     * @param stream
     * @return
     * @throws InvalidProtocolBufferException
     */
    public static Object parseFromStream(Class clazz, byte[] stream) throws InvalidProtocolBufferException {
        return deseriaContext.messageToObject(clazz,
                DynamicMessage.parseFrom(seriaContext.getBuilderContext().descriptorFromClass(clazz), stream));
    }

    /**
     * 如果你知道要序列化哪些对象，初始化一下有利于提高性能
     * @param clazz
     */
    public void preLoadingDescription(Class clazz) {
        descGener.descriptorFromClass(clazz);
    }
}
