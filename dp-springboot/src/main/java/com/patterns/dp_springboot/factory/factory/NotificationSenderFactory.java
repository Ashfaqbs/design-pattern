package com.patterns.dp_springboot.factory.factory;

import com.patterns.dp_springboot.factory.exception.UnsupportedChannelException;
import com.patterns.dp_springboot.factory.sender.NotificationSender;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class NotificationSenderFactory {

    private final Map<String, NotificationSender> senders;

    public NotificationSenderFactory(List<NotificationSender> senders) {
        this.senders = senders.stream()
                .collect(Collectors.toMap(NotificationSender::channel, s -> s));
    }

    /**
     * Factory method — caller gets a NotificationSender without knowing the concrete type.
     * Adding a new channel = add a new @Component. Zero changes here.
     */
    public NotificationSender create(String channel) {
        NotificationSender sender = senders.get(channel.toUpperCase());
        if (sender == null) {
            throw new UnsupportedChannelException(channel);
        }
        return sender;
    }
}
