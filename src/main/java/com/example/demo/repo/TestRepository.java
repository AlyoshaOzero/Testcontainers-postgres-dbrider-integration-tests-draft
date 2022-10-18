package com.example.demo.repo;

import com.example.demo.api.SomeType;
import com.example.demo.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TestRepository extends JpaRepository<Test, UUID> {

    @Query(value = "SELECT * FROM tests WHERE CAST(some_object ->> 'limit' AS INTEGER) >= :limit", nativeQuery = true)
    List<Test> findAllWhereLimitMoreThan(@Param("limit") int limit);

    @Query(value = "SELECT COUNT(*) FROM tests WHERE CAST(some_object ->> 'message' AS VARCHAR) IS NOT NULL AND LENGTH(CAST(some_object ->> 'message' AS VARCHAR)) > 0", nativeQuery = true)
    int findCountWhereMessageExists();

    List<Test> findAllByType(@Param("type") SomeType type);

    @Query(value = "SELECT * FROM tests WHERE CAST(:type AS some_type) = ANY(types)", nativeQuery = true)
    List<Test> findAllWhereTypesContains(@Param("type") String type);

    @Query(value = "FROM Test WHERE day <= :day")
    List<Test> findAllExpired(@Param("day") LocalDate day);
}
