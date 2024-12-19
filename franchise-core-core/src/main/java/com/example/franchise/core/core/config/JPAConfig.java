package com.example.franchise.core.core.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.example.franchise.core.domain.repository")
@EntityScan("com.example.franchise.core.domain.model")
@EnableTransactionManagement
public class JPAConfig {
}
