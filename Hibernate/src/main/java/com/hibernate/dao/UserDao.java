package com.hibernate.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernate.domain.User;

public interface UserDao extends JpaRepository<User, Integer> {
}
