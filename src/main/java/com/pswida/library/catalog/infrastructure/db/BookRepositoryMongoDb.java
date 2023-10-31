package com.pswida.library.catalog.infrastructure.db;

import com.pswida.library.catalog.infrastructure.db.document.BookDocument;
import com.pswida.library.tracker.domain.ProcessTracker;
import com.pswida.library.tracker.domain.ProcessTrackerId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

interface BookRepositoryMongoDb extends MongoRepository<BookDocument, String> {

  List<BookDocument> getByTitle(String title);

  Optional<BookDocument> findByDiscussion_TrackerIdAndDiscussion_Status(ProcessTrackerId discussionTrackerId, ProcessTracker.Status status);
}
