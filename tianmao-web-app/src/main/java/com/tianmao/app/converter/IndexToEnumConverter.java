package com.tianmao.app.converter;

import com.tianmao.app.util.NumericUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * 索引转枚举值
 *
 * @author roach
 * @date 2017/11/24
 */
public class IndexToEnumConverter implements ConverterFactory<String, Enum> {

    private static final Logger logger = LoggerFactory.getLogger(IndexToEnumConverter.class);

    @Override
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToEnum(getEnumType(targetType));
    }

    private class StringToEnum<T extends Enum> implements Converter<String, T> {

        private final Class<T> enumType;

        public StringToEnum(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        public T convert(String source) {
            if (StringUtils.isEmpty(source)) {
                return null;
            }
            if (NumericUtil.isNumeric(source)) {
                try {
                    Method method = enumType.getMethod("values");
                    Object[] invoke = (Object[]) method.invoke(enumType);
                    int index = Integer.valueOf(source);
                    return (T) invoke[index];
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
            return (T) Enum.valueOf(this.enumType, source.trim());
        }
    }

    public static Class<?> getEnumType(Class<?> targetType) {
        Class<?> enumType = targetType;
        while (enumType != null && !enumType.isEnum()) {
            enumType = enumType.getSuperclass();
        }
        if (enumType == null) {
            throw new IllegalArgumentException(
                    "The target type " + targetType.getName() + " does not refer to an enum");
        }
        return enumType;
    }

}
