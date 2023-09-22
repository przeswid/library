package org.boldare.books.infrastructure;

import org.boldare.books.domain.client.Client;
import org.boldare.books.domain.client.ClientRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public final class ClientRepositoryInMemory implements ClientRepository {

  public static final ClientRepository INSTANCE = new ClientRepositoryInMemory();

  private ClientRepositoryInMemory() {
  }

  private final Set<Client> clients = new HashSet<>();

  public void add(Client client) {
    clients.add(client);
  }

  public Optional<Client> getById(String clientIdentifier) {
    return clients.stream().filter(b -> b.identifier().equals(clientIdentifier)).findFirst();
  }

  public Collection<Client> getAll() {
    return clients;
  }

  public void removeAll() {
    clients.clear();
  }

}
