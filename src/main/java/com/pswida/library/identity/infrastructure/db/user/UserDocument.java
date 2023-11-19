package com.pswida.library.identity.infrastructure.db.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "identity", name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
class UserDocument {

  @Id
  private String id;
  private String username;
  private String email;

}
