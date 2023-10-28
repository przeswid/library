package com.pswida.library.discussion.infrastructure.kafka;

import lombok.Builder;

@Builder
record DiscussionCreatedKafkaEvent(String discussionId, String ownerId) {
}
