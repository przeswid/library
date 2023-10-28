package com.pswida.library.catalog.infrastructure.events;

import lombok.Builder;

@Builder
record DiscussionCreatedKafkaEvent(String discussionId, String ownerId) {
}
