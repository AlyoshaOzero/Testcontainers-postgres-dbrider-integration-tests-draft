package com.example.demo.util;

import com.example.demo.api.SomeType;
import org.dbunit.dataset.datatype.DataType;
import org.dbunit.dataset.datatype.DataTypeException;
import org.dbunit.ext.postgresql.InetType;
import org.dbunit.ext.postgresql.IntervalType;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;
import org.dbunit.ext.postgresql.UuidType;

import java.sql.Types;
import java.util.Set;

public class CustomPostgresqlDataTypeFactory extends PostgresqlDataTypeFactory {

    private static final Set<String> NUMERIC_TYPES = Set.of("int", "decimal", "float", "numeric", "real", "double", "serial");

    @Override
    public DataType createDataType(int sqlType, String sqlTypeName, String tableName, String columnName) throws DataTypeException {
        if (sqlType == Types.ARRAY) {
            if (sqlTypeName.toLowerCase().contains("text") || sqlTypeName.toLowerCase().contains("char")) {
                return new ArrayDataType(sqlTypeName, sqlType, false, false, "text", String.class);
            } else if (NUMERIC_TYPES.stream().anyMatch(numericType -> sqlTypeName.toLowerCase().contains(numericType))) {
                return new ArrayDataType(sqlTypeName, sqlType, true, false, "numeric", Number.class);
            } else {
                // TODO: work with different Enum types
                return new ArrayDataType(sqlTypeName, sqlType, false, true, "some_type", SomeType.class);
            }
        }
        if (sqlType == Types.VARCHAR) {
            if (isEnumType(sqlTypeName)) {
                // TODO: work with different Enum types
                return new EnumDataType<>(sqlTypeName, SomeType.class);
            }
        }
        if (sqlType == Types.OTHER) {
            if ("uuid".equalsIgnoreCase(sqlTypeName)) {
                return new UuidType();
            } else if ("interval".equalsIgnoreCase(sqlTypeName)) {
                return new IntervalType();
            } else if ("inet".equalsIgnoreCase(sqlTypeName)) {
                return new InetType();
            } else if (sqlTypeName.equalsIgnoreCase("jsonb") || sqlTypeName.equalsIgnoreCase("json")) {
                return new JsonbDataType(sqlTypeName);
            }
        }
        return super.createDataType(sqlType, sqlTypeName, tableName, columnName);
    }

    @Override
    public boolean isEnumType(String sqlTypeName) {
        // TODO: work with different Enum types
        if (sqlTypeName.contains("some_type")) {
            return true;
        } else {
            return super.isEnumType(sqlTypeName);
        }
    }
}