package com.pswida.library.discussion.infrastructure.kafka;

record UserEventMessage(
  String userId,
  String payload
) {
}
