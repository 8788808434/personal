package com.gasagency.gas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gasagency.gas.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

}
