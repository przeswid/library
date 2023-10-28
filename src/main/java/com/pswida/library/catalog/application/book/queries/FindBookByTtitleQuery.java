package com.pswida.library.catalog.application.book.queries;

import com.pswida.library.common.application.cqs.query.Query;

public record FindBookByTtitleQuery(String bookTitle) implements Query<FindBookByTtitleQueryResult> {

}
