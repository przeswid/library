package com.pswida.library.lending.infrastructure.services;

import com.pswida.library.lending.domain.LendedBookWithAvailbility;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Component
@AllArgsConstructor
class CatalogServiceAdapter {

  public static final String CATALOG_URI = "http://localhost:8080/bookcopy/";

  private final CatalogTranslator catalogTranslator;

  private final WebClient webClient = WebClient.create(CATALOG_URI);

  public Optional<LendedBookWithAvailbility> getLendedBookCopy(String bookCopyId) {
    return Optional.ofNullable(webClient.method(HttpMethod.GET)
      .uri(CATALOG_URI + "/" + bookCopyId)
      .retrieve()
      .bodyToMono(BookCopyDto.class)
      .block()).map(catalogTranslator::toLendedBookWithAvailbility);
  }
}
