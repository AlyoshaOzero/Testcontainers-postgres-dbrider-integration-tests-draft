package com.example.demo.entity;

import com.example.demo.api.SomeObject;
import com.example.demo.api.SomeType;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tests")
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonType.class),
        @TypeDef(name = "list-array", typeClass = ListArrayType.class),
        @TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
})
public class Test {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String name;

    @Type(type = "list-array")
    @Column(name = "tags", columnDefinition = "text[]")
    private List<String> tags;

    @Type(type = "list-array")
    @Column(name = "numbers", columnDefinition = "bigint[]")
    private List<Long> numbers;

    @Type(type = "list-array")
    @Column(name = "other_numbers", columnDefinition = "numeric[]")
    private List<BigDecimal> otherNumbers;

    @Type(type = "list-array",
          parameters = {@org.hibernate.annotations.Parameter(name = ListArrayType.SQL_ARRAY_TYPE, value = "some_type")}
    )
    @Column(name = "types", columnDefinition = "some_type[]")
    private List<SomeType> types;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "some_type")
    @Type( type = "pgsql_enum" )
    private SomeType type;

    @Column
    private LocalDate day;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "some_object", columnDefinition = "jsonb")
    @Type(type = "json")
    private SomeObject obj;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Long> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Long> numbers) {
        this.numbers = numbers;
    }

    public List<SomeType> getTypes() {
        return types;
    }

    public void setTypes(List<SomeType> types) {
        this.types = types;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public SomeObject getObj() {
        return obj;
    }

    public void setObj(SomeObject obj) {
        this.obj = obj;
    }

    public SomeType getType() {
        return type;
    }

    public void setType(SomeType type) {
        this.type = type;
    }

    public List<BigDecimal> getOtherNumbers() {
        return otherNumbers;
    }

    public void setOtherNumbers(List<BigDecimal> other_numbers) {
        this.otherNumbers = other_numbers;
    }
}
