package com.example.demo.util;

import org.dbunit.dataset.datatype.AbstractDataType;

import java.sql.Types;

public class EnumDataType<T extends Class<? extends Enum<?>>> extends AbstractDataType {

    EnumDataType(String sqlTypeName, T enumClass) {
        super(sqlTypeName, Types.OTHER, enumClass, false);
    }

    @Override
    public Object typeCast(Object value) {
        return value.toString();
    }
}
