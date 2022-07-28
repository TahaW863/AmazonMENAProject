package com.taha.webfluxrealtimechat.messages.interfaces;

import com.taha.webfluxrealtimechat.messages.model.HotMessage;
import com.taha.webfluxrealtimechat.messages.model.LongLivedMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface MessageRepository extends MongoRepository<LongLivedMessage, String> {
    Page<LongLivedMessage> findByReceiverId(String receiverId, Pageable pageable);
}
