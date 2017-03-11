package com.kopiitem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Optional;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringbootTestdbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTestdbApplication.class, args);
    }
}

@RestController
class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("accounts/{username}")
    Account findByUsername(@PathVariable String username) {
        return accountRepository.findByUsername(username).orElseThrow(AccountNotFoundExeption::new);
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "account not found")
    static class AccountNotFoundExeption extends RuntimeException {
    }

}

interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(String username);

}

@Entity
class Account {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    @JsonIgnore
    private String password;

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
