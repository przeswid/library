package com.pswida.library.common.application.cqs.query;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class QueryDispatcher {

  private final List<QueryHandler<?,?>> commandHandlers;

  public <R extends QueryResult> R dispatch(Query<R> query) {
   return commandHandlers.stream()
      .filter(handler -> handler.queryType().equals(query.getClass()))
      .findFirst()
      .map(handler -> (QueryHandler<Query<R>, R>) handler)
      .orElseThrow(() -> new RuntimeException("No handler found for query: " + query))
      .handle(query);
  }
}
