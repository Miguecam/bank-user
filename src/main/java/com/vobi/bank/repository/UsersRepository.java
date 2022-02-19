package com.vobi.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vobi.bank.domain.Users;

public interface UsersRepository extends JpaRepository<Users, String> {

}
