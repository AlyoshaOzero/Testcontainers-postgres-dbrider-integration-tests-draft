//package com.example.demo.conf;
//
//import org.springframework.boot.test.util.TestPropertyValues;
//import org.springframework.context.ApplicationContextInitializer;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.testcontainers.containers.PostgreSQLContainer;
//
//public class DatabaseContainerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
//
//    private static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15.0-alpine")
//            .withInitScript("init/init-types.sql");
//
//    @Override
//    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
//        postgreSQLContainer.start();
//        TestPropertyValues.of(
//                "db.url=" + "jdbc:tc:postgresql:15.0-alpine:///test",
//                "db.user=" + postgreSQLContainer.getPassword(),
//                "db.password=" + postgreSQLContainer.getUsername()
//        ).applyTo(configurableApplicationContext.getEnvironment());
//    }
//}
