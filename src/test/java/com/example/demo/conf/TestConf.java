package com.example.demo.conf;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.connection.RiderDataSource;
import com.github.database.rider.junit5.api.DBRider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

@SpringBootApplication
@EntityScan(basePackages = "com.example.demo.entity")
@EnableJpaRepositories(basePackages = "com.example.demo.repo")
@TestPropertySource(locations = "classpath:application.properties")
public class TestConf {

    public static void main(String[] args) {
        SpringApplication.run(TestConf.class, args);
    }
}
