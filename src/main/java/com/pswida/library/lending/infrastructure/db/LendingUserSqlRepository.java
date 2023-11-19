package com.pswida.library.lending.infrastructure.db;

import com.pswida.library.lending.infrastructure.db.entity.LendingUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

interface LendingUserSqlRepository extends JpaRepository<LendingUserEntity, String> {
}
