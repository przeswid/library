package com.pswida.library.identity.infrastructure.db.user.event;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserEventSpringDataRepository extends JpaRepository<UserEventEntity, String> {

}
