package com.pswida.library.identity.infrastructure.db.user.event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "identity", name = "user_events")
@Data
@AllArgsConstructor
@NoArgsConstructor
class UserEventEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  @Column(name = "user_id")
  String userId;
  @Column(name = "event_type")
  String eventType;
  String payload;

  public UserEventEntity(String userId, String eventType, String payload) {
    this.userId = userId;
    this.eventType = eventType;
    this.payload = payload;
  }
}
