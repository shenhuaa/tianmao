package tianmao.web.app.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.sql.Time;

/**
 * String 转 Time
 * @author roach
 * @date 2017/11/20
 */
public class StringToTimeConverter implements Converter<String, Time> {

    private static final Logger logger = LoggerFactory.getLogger(StringToTimeConverter.class);

    @Override
    public Time convert(String timeString) {
        try {
            if (timeString.matches("^\\d{2}\\s*:\\s*\\d{2}$")) {
                timeString = timeString + ":00";
            }
            return Time.valueOf(timeString);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
