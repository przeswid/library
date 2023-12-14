package com.pswida.library.catalog.infrastructure.db;

import com.pswida.library.catalog.infrastructure.db.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface BookRepositoryMongoDb extends JpaRepository<BookEntity, String> {

  List<BookEntity> getByTitle(String title);

//  Optional<BookDocument> findByDiscussion_TrackerIdAndDiscussion_Status(ProcessTrackerId discussionTrackerId,
//    ProcessTracker.Status status);
}
