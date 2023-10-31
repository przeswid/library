package com.pswida.library.catalog.infrastructure.events;

import lombok.Builder;

@Builder
record DiscussionRequestedEvent(String ownerId, String eventId) {
}
