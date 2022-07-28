package com.taha.webfluxrealtimechat.messages.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "messages-long-lived")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LongLivedMessage {
    private HotMessage message;
    private String receiverId;
    private LocalDateTime createdAt;
}
