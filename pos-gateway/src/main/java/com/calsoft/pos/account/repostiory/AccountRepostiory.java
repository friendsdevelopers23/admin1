package com.calsoft.pos.account.repostiory;

import org.springframework.data.repository.CrudRepository;

import com.calsoft.pos.account.model.Account;


public interface AccountRepostiory extends CrudRepository<Account, String> {

}