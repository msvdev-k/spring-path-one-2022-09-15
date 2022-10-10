package org.msvdev.ee;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("org.msvdev.ee")
public class AppConfig {

    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory() {

        return new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }
}