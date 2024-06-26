package com.alpha.repository;

import org.springframework.data.repository.Repository;

import com.alpha.entity.TokenSession;


public interface TokenSessionRepository extends Repository<TokenSession, String> {

    void save(TokenSession tokenSession);

}
