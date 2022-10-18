package com.example.demo.conf;

import com.example.demo.util.CustomPostgresqlDataTypeFactory;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.connection.RiderDataSource;
import com.github.database.rider.junit5.api.DBRider;
import org.dbunit.database.DefaultMetadataHandler;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

@DBRider
@Inherited
@Documented
@Target(ElementType.TYPE)
@DataJpaTest
@Retention(RetentionPolicy.RUNTIME)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@DBUnit(schema = "public", dataTypeFactoryClass = CustomPostgresqlDataTypeFactory.class,
        metaDataHandler = DefaultMetadataHandler.class, qualifiedTableNames = true,
        caseInsensitiveStrategy = Orthography.LOWERCASE, expectedDbType = RiderDataSource.DBType.POSTGRESQL)
@TestPropertySource(locations = "classpath:application.properties")
@ContextConfiguration(classes = TestConf.class/*, initializers = {DatabaseContainerInitializer.class}*/)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public @interface PersistenceLayerTest {}
