package ru.itis.kpfu.telegram.model;

import com.pengrad.telegrambot.model.Update;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    private String httpMethod;
    private String body;
}
