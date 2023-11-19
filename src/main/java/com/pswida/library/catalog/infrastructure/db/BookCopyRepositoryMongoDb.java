package com.pswida.library.catalog.infrastructure.db;

import com.pswida.library.catalog.infrastructure.db.entity.BookCopyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

interface BookCopyRepositoryMongoDb extends JpaRepository<BookCopyEntity, String> {

}
