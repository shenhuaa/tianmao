package tianmao.web.app.converter;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

/**
 * String 转 Date
 * @author roach
 * @date 2017/11/20
 */
public class StringToDateConverter implements Converter<String, Date> {

    private static final Logger logger = LoggerFactory.getLogger(StringToDateConverter.class);
    private final static String[] dateFormats = {
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd",
    };

    @Override
    public Date convert(String dateString) {
        try {
            return DateUtils.parseDate(dateString, dateFormats);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
