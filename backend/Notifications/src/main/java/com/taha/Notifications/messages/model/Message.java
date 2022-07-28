package com.taha.Notifications.messages.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {
    private String data;
    private String senderId;
    private String receiverId;
    private LocalDateTime date;
}
