package com.pswida.library.tracker.infrastructure.db;

import com.pswida.library.tracker.domain.ProcessTrackerId;
import com.pswida.library.tracker.domain.ProcessTrackerSnapshot;
import com.pswida.library.tracker.infrastructure.db.document.ProcessTrackerDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface ProcessTrackerMapper {

  ProcessTrackerDocument mapProcessTrackerSnapshotToProcessTrackerDocument(ProcessTrackerSnapshot tracker);

  ProcessTrackerSnapshot mapProcessTrackerDocumentToProcessTrackerSnapshot(ProcessTrackerDocument bookDocument);

  default ProcessTrackerId mapProcessTrackerId(String processTrackerId) {
    return new ProcessTrackerId(processTrackerId);
  }

  default String mapProcessTrackerId(ProcessTrackerId processTrackerId) {
    return processTrackerId.id();
  }

}
