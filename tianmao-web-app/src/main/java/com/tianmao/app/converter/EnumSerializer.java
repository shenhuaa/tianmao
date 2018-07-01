package com.tianmao.app.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 枚举转数字
 *
 * @author roach
 * @date 2017/11/20
 */
public class EnumSerializer extends StdSerializer<Enum> {

    private static final Logger logger = LoggerFactory.getLogger(EnumSerializer.class);

    public EnumSerializer(Class<Enum> t) {
        super(t);
    }

    @Override
    public void serialize(Enum value, JsonGenerator generator, SerializerProvider serializers) throws IOException {
        generator.writeStartObject();
        try {
            Class enumClass = value.getDeclaringClass();
            int index = value.ordinal();
            Method method = enumClass.getMethod("getRemark");
            String remark = (String) method.invoke(value);
            generator.writeNumberField("index", index);
            generator.writeStringField("remark", remark);
        } catch (Exception e) {
            logger.error("枚举转数字类型出错：", e.getMessage());
        }
        generator.writeEndObject();
    }
}