package org.boldare.books.domain.client;

import java.util.Optional;

public interface ClientRepository {

  void add(Client client);

  Optional<Client> getById(String clientId);
}
