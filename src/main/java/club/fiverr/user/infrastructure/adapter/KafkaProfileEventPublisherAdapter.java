package club.fiverr.user.infrastructure.adapter;

import club.fiverr.user.application.port.out.ProfileEventPublisherPort;
import club.fiverr.user.domain.model.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;

@Component
public class KafkaProfileEventPublisherAdapter implements ProfileEventPublisherPort {

    private static final Logger log = LoggerFactory.getLogger(KafkaProfileEventPublisherAdapter.class);
    private static final String TOPIC_PROFILE_EVENTS = "fiverr.user.profile-events";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProfileEventPublisherAdapter(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishProfileCreated(UserProfile profile) {
        sendEvent("PROFILE_CREATED", profile);
    }

    @Override
    public void publishProfileUpgradedToSeller(UserProfile profile) {
        sendEvent("PROFILE_UPGRADED_TO_SELLER", profile);
    }

    private void sendEvent(String eventType, UserProfile profile) {
        Map<String, Object> payload = Map.of(
                "eventType", eventType,
                "profileId", profile.getId().toString(),
                "accountId", profile.getAccountId().toString(),
                "username", profile.getUsername(),
                "isSeller", profile.isSeller(),
                "sellerLevel", profile.getSellerLevel().name(),
                "occurredAt", Instant.now().toString()
        );
        try {
            kafkaTemplate.send(TOPIC_PROFILE_EVENTS, profile.getId().toString(), payload);
        } catch (Exception ex) {
            log.warn("Non-blocking Kafka publish warning for profile {}: {}", profile.getId(), ex.getMessage());
        }
    }
}
