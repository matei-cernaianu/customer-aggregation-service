package com.matei.customeraccountaggregation.repository;

import com.matei.customeraccountaggregation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
