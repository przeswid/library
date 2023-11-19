package com.pswida.library.tracker.infrastructure.db;

import com.pswida.library.tracker.infrastructure.db.document.ProcessTrackerDocument;
import org.springframework.data.jpa.repository.JpaRepository;

interface ProcessTrackerSpringDataRepository extends JpaRepository<ProcessTrackerDocument, String> {
}
