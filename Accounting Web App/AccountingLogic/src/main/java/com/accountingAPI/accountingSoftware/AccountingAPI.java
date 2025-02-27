package com.accountingAPI.accountingSoftware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan("com.accountingAPI.accountingSoftware.model")
@EnableJpaRepositories(basePackages = "com.accountingAPI.accountingSoftware.repository")
public class AccountingAPI{
    public static void main(String[] args) {
        SpringApplication.run(AccountingAPI.class, args);
    }
}
