package com.pool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pool.modal.UserEntity;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);
}
