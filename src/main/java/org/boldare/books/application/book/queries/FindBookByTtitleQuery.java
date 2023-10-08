package org.boldare.books.application.book.queries;

import org.boldare.books.application.core.cqs.query.Query;

public record FindBookByTtitleQuery(String bookTitle) implements Query<FindBookByTtitleQueryResult> {

}
