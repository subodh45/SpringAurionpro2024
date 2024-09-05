package com.aurionpro.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aurionpro.main.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long > {


}
