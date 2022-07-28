package com.taha.webfluxrealtimechat.messages.service;

import com.taha.webfluxrealtimechat.messages.interfaces.MessageReactiveRepository;
import com.taha.webfluxrealtimechat.messages.interfaces.MessageRepository;
import com.taha.webfluxrealtimechat.messages.model.HotMessage;
import com.taha.webfluxrealtimechat.messages.model.LongLivedMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class MessagesService {
    private final MessageReactiveRepository messageReactiveRepository;
    private final MessageRepository messageRepository;

    public MessagesService(MessageReactiveRepository messageReactiveRepository, MessageRepository messageRepository) {
        this.messageReactiveRepository = messageReactiveRepository;
        this.messageRepository = messageRepository;
    }

    public Flux<HotMessage> getMessages(String channelId) {
        return messageReactiveRepository.findByChannelId(channelId);
    }

    public Mono<HotMessage> postMessage(HotMessage hotMessage) {
        return messageReactiveRepository.save(hotMessage);
    }

    public void saveNonReactiveMessage(LongLivedMessage longLivedMessage) {
        messageRepository.save(longLivedMessage);
    }
    public Page<LongLivedMessage> getLongLivedMessages(String receiverId, PageRequest pageRequest) {
        return messageRepository.findByReceiverId(receiverId, pageRequest);
    }
}
