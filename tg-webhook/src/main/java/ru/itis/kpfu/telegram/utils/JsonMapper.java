package ru.itis.kpfu.telegram.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

import java.io.IOException;

@UtilityClass
public class JsonMapper {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public <T> T fromJson(String dataJSON, Class<T> target) {
        try {
            return objectMapper.readValue(dataJSON, target);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new IllegalArgumentException();
        }
    }

}
