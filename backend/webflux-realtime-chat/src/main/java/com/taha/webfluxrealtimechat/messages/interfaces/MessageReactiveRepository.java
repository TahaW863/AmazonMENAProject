package com.taha.webfluxrealtimechat.messages.interfaces;

import com.taha.webfluxrealtimechat.messages.model.HotMessage;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

/*
     * @Tailable means that the cursor will be tailable, meaning that it will keep on
     * appending new data to the collection. This is useful for long-lived cursors,
     * like those used in the tailing log feature.
     * 
 */
public interface MessageReactiveRepository extends ReactiveMongoRepository<HotMessage, String> {
    @Tailable
    Flux<HotMessage> findByChannelId(String channelId);
}
