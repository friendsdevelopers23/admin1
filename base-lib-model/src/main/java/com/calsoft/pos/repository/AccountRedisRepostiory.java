package com.calsoft.pos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.calsoft.pos.model.AccountRedis;


public interface AccountRedisRepostiory extends CrudRepository<AccountRedis, String> {

}