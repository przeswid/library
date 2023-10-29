package com.pswida.library.catalog.infrastructure.db;

import com.pswida.library.catalog.infrastructure.db.document.BookCopyDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

interface BookCopyRepositoryMongoDb extends MongoRepository<BookCopyDocument, String> {

}
