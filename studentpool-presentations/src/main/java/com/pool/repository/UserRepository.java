package com.pool.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pool.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	public Optional<UserEntity> findByUserId(String userId);
	public Optional<UserEntity> findByEmail(String email);
}
