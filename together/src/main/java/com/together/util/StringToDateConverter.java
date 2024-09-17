package com.together.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/**
 * @author w
 */
public class StringToDateConverter implements Converter<String, Date> {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public Date convert(String value) {

        if(StringUtils.isEmpty(value)) {
            return null;
        }

        value = value.trim();

        try {
            if(value.contains("-")) {
                SimpleDateFormat formatter;
                if(value.contains(":")) {
                    formatter = new SimpleDateFormat(DATE_FORMAT);
                }else {
                    formatter = new SimpleDateFormat(SHORT_DATE_FORMAT);
                }

                return formatter.parse(value);
            }else if(value.matches("^\\d+$")) {
                long lDate = Long.parseLong(value);
                return new Date(lDate);
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("parser %s to Date fail", value));
        }
        throw new RuntimeException(String.format("parser %s to Date fail", value));
    }


}