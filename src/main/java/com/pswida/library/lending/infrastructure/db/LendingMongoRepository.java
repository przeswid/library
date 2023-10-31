package com.pswida.library.lending.infrastructure.db;

import com.pswida.library.lending.infrastructure.db.document.LendingDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

interface LendingMongoRepository extends MongoRepository<LendingDocument, String> {
}
