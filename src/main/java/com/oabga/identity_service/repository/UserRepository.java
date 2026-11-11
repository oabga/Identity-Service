package com.oabga.identity_service.repository;

import com.oabga.identity_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username); // JPA sẽ tự động query xem có tồn tại username chưa
}
