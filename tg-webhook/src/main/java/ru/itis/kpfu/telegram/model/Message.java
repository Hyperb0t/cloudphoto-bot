package ru.itis.kpfu.telegram.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private String text;
    private Chat chat;
    private Message reply_to_message;
    private String caption;
}
