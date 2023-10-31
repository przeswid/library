package com.pswida.library.catalog.presentation.rest.book;

import com.pswida.library.catalog.domain.book.BookDiscussion;

record BookDiscussionDto(BookDiscussion.Status status, String discussionId) {

}
