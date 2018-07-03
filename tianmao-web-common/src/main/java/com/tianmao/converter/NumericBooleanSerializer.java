package com.tianmao.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * Boolean 转数字
 *
 * @author roach
 * @date 2017/11/20
 */
public class NumericBooleanSerializer extends StdSerializer<Boolean> {

    public NumericBooleanSerializer(Class<Boolean> booleanClass) {
        super(booleanClass);
    }

    @Override
    public void serialize(Boolean bool, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeNumber(bool ? 1 : 0);
    }

}

