package com.blueripple.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blueripple.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

	public Optional<UserEntity> findByUserName(String name);
}
