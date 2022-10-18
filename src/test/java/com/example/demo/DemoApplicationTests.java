package com.example.demo;

import com.example.demo.api.SomeType;
import com.example.demo.conf.PersistenceLayerTest;
import com.example.demo.repo.TestRepository;
import com.github.database.rider.core.api.dataset.DataSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@DataSet("tests.yml")
@PersistenceLayerTest
class DemoApplicationTests {

    @Autowired
    private TestRepository repository;

    @Test
    void findAllWhereLimitMoreThanTest() {
        var res = repository.findAllWhereLimitMoreThan(1);

        Assertions.assertFalse(res.isEmpty());
        Assertions.assertEquals(1, res.size());
        Assertions.assertNotNull(res.get(0).getObj());
        Assertions.assertNotNull(res.get(0).getNumbers());
        Assertions.assertNotNull(res.get(0).getTags());
        Assertions.assertNotNull(res.get(0).getTypes());
        Assertions.assertNotNull(res.get(0).getOtherNumbers());
    }

    @Test
    void findCountWhereMessageExists() {
        Assertions.assertEquals(1, repository.findCountWhereMessageExists());
    }

    @Test
    void findAllByType() {
        var res = repository.findAllByType(SomeType.TWO);

        Assertions.assertFalse(res.isEmpty());
        Assertions.assertEquals(1, res.size());
        Assertions.assertNotNull(res.get(0).getObj());
        Assertions.assertNotNull(res.get(0).getNumbers());
        Assertions.assertNotNull(res.get(0).getTags());
        Assertions.assertNotNull(res.get(0).getTypes());
        Assertions.assertNotNull(res.get(0).getOtherNumbers());
    }

    @Test
    void findAllWhereTypesContains() {
        var res = repository.findAllWhereTypesContains(SomeType.ONE.name());

        Assertions.assertFalse(res.isEmpty());
        Assertions.assertEquals(1, res.size());
        Assertions.assertNotNull(res.get(0).getObj());
        Assertions.assertNotNull(res.get(0).getNumbers());
        Assertions.assertNotNull(res.get(0).getTags());
        Assertions.assertNotNull(res.get(0).getTypes());
        Assertions.assertNotNull(res.get(0).getOtherNumbers());
    }

    @Test
    void findAllExpired() {
        var res = repository.findAllExpired(LocalDate.now());

        Assertions.assertFalse(res.isEmpty());
        Assertions.assertEquals(1, res.size());
        Assertions.assertNotNull(res.get(0).getObj());
        Assertions.assertNotNull(res.get(0).getNumbers());
        Assertions.assertNotNull(res.get(0).getTags());
        Assertions.assertNotNull(res.get(0).getTypes());
        Assertions.assertNotNull(res.get(0).getOtherNumbers());
    }
}
