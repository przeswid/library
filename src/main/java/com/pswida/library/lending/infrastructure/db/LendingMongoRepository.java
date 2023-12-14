package com.pswida.library.lending.infrastructure.db;

import com.pswida.library.lending.infrastructure.db.entity.LendingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

interface LendingMongoRepository extends JpaRepository<LendingEntity, String> {
}
