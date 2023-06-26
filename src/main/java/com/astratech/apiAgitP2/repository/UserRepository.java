package com.astratech.apiAgitP2.repository;

import com.astratech.apiAgitP2.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel,Long> {
}
