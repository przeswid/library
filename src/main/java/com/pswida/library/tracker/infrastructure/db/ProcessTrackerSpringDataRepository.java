package com.pswida.library.tracker.infrastructure.db;

import com.pswida.library.tracker.infrastructure.db.document.ProcessTrackerDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

interface ProcessTrackerSpringDataRepository extends MongoRepository<ProcessTrackerDocument, String> {
}
