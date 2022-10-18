package com.example.demo.util;

import org.dbunit.dataset.ITable;
import org.dbunit.dataset.datatype.AbstractDataType;
import org.dbunit.dataset.datatype.TypeCastException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.sql.*;
import java.util.stream.Stream;

public class ArrayDataType extends AbstractDataType {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final String typeName;
    private final Class<?> type;
    private final boolean isEnum;

    public ArrayDataType(String name, int sqlType, boolean isNumber, boolean isEnum, String typeName, Class<?> type) {
        super(name, sqlType, type, isNumber);
        this.typeName = typeName;
        this.type = type;
        this.isEnum = isEnum;
    }

    @Override
    public Object typeCast(Object value) throws TypeCastException {
        if (value == null || value == ITable.NO_VALUE) {
            return null;
        }

        if (value instanceof String) {
            return new String[]{(String) value};
        }
        if (value instanceof String[]) {
            return value;
        }

        if (value instanceof Date || value instanceof Time || value instanceof Timestamp) {
            return new String[]{value.toString()};
        }

        if (value instanceof Boolean) {
            return new String[]{value.toString()};
        }

        if (value instanceof Number) {
            try {
                return new String[]{value.toString()};
            } catch (NumberFormatException e) {
                throw new TypeCastException(value, this, e);
            }
        }

        if (value instanceof Array) {
            try {
                Array a = (Array) value;
                return a.getArray();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (value instanceof Blob) {
            try {
                Blob blob = (Blob) value;
                byte[] blobValue = blob.getBytes(1, (int) blob.length());
                return typeCast(blobValue);
            } catch (SQLException e) {
                throw new TypeCastException(value, this, e);
            }
        }

        if (value instanceof Clob) {
            try {
                Clob clobValue = (Clob) value;
                int length = (int) clobValue.length();
                if (length > 0) {
                    return clobValue.getSubString(1, length);
                }
                return "";
            } catch (SQLException e) {
                throw new TypeCastException(value, this, e);
            }
        }

        log.warn("Unknown/unsupported object type '{}' - " +
                        "will invoke toString() as last fallback which " +
                        "might produce undesired results",
                value.getClass().getName());
        return value.toString();
    }

    @Override
    public void setSqlValue(Object value, int column, PreparedStatement statement)
            throws SQLException, TypeCastException {
        if (log.isDebugEnabled()) {
            log.debug("setSqlValue(value={}, column={}, statement={}) - start", value, column, statement);
        }
        Array array = statement.getConnection().createArrayOf(this.typeName, toArray(value));
        statement.setObject(column, array);
    }

    @SuppressWarnings("unchecked")
    private Object[] toArray(Object value) {
        if (value instanceof String valueStr) {
            if (!valueStr.isBlank()) {
                valueStr = valueStr.replaceAll("[{}]", "");
                return Stream.of(valueStr.split(","))
                        .map(String::trim)
                        .map(val -> isEnum ? Enum.valueOf((Class<? extends Enum>) type, val.toUpperCase()) : val)
                        .toArray();
            }
        }
        return new Object[0];
    }
}