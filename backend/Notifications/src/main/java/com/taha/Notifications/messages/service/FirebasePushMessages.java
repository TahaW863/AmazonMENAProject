package com.taha.Notifications.messages.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.taha.Notifications.messages.model.Note;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FirebasePushMessages {
    private final FirebaseMessaging firebaseMessages;

    public String sendNotification(Note note, String token) throws FirebaseMessagingException {
        log.info("Sending message to token: {}", token);
        Notification notification = Notification.builder()
                .setTitle(note.getSubject())
                .setBody(note.getContent())
                .build();
        Message message = Message.builder()
                .setToken(token)
                .setNotification(notification)
                .putAllData(note.getData())
                .build();
        return firebaseMessages.send(message);
    }
    public String sendData(Note note, String token) throws FirebaseMessagingException {
        log.info("Sending message to token: {}", token);
        Message message = Message.builder()
                .setToken(token)
                .putAllData(note.getData())
                .build();

        return firebaseMessages.send(message);
    }
}
