package com.taha.webfluxrealtimechat.messages.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "messages")
@Builder
public class HotMessage {
    @Id
    private String id;
    @NonNull
    private String message;
    @NonNull
    private String senderId;
    @NonNull
    private String receiverId;
    @NonNull
    private String channelId;
}
