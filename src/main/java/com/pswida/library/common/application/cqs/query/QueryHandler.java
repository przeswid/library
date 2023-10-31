package com.pswida.library.common.application.cqs.query;

import lombok.extern.log4j.Log4j2;
import org.springframework.util.StopWatch;

@Log4j2
public abstract class QueryHandler<T extends Query<R>, R extends QueryResult> {

  public R handle(T query) {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    R queryResult;

    try {
      queryResult = doHandle(query);
    } catch (Exception e) {
      log.error("Error while handling query: " + query, e);
      throw e;
    }

    stopWatch.stop();
    log.debug("Query {} handled in {} ms", query, stopWatch.getTotalTimeMillis());

    return queryResult;
  }

  protected abstract R doHandle(T query);

  protected abstract Class<T> queryType();
}
