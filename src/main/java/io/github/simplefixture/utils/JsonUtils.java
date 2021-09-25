package io.github.simplefixture.utils;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonUtils {

    public static <T> Object create(String json, Class<T> clazz){
        return new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateDeserializer())
                .create().fromJson(json, clazz);
    }
}

class DateDeserializer implements com.google.gson.JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonElement element, Type type, JsonDeserializationContext context) {

        ThreadLocal<DateFormat> df = ThreadLocal.withInitial(()->new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        String el = element.getAsString();
        Date convertedDate;
        try {
            if(StringUtils.isNumeric(el)){
                convertedDate = new Date(new Timestamp(Long.parseLong(el)).getTime());
            }else{
                convertedDate = df.get().parse(el);
            }
        } catch (Exception e) {
            SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy hh:mm:ss a");
            try {
                convertedDate = sdf.parse(el);
            } catch (ParseException ex) {
                throw new RuntimeException("DateType value is Not valid", e);
            }
        }
        return convertedDate;
    }
}