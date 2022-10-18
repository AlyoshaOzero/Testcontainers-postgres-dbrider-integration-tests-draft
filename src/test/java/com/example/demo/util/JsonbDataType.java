package com.example.demo.util;

import org.dbunit.dataset.datatype.AbstractDataType;

import java.sql.Types;

public class JsonbDataType extends AbstractDataType {

    JsonbDataType(String sqlTypeName) {
        super(sqlTypeName, Types.OTHER, String.class, false);
    }

    @Override
    public Object typeCast(Object value) {
        return value.toString();
    }
}
