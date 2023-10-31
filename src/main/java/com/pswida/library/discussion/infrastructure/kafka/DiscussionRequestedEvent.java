package com.pswida.library.discussion.infrastructure.kafka;

import lombok.Builder;

@Builder
record DiscussionRequestedEvent(String ownerId, String eventId) {
}
