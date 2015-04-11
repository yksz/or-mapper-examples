package org.example.dao.util;

import java.util.Objects;

public class EnumUtils {

    public static <E extends Enum<E>> E getByName(Class<E> type, String name) {
        Objects.requireNonNull(type, "type must not be null");
        Objects.requireNonNull(name, "name must not be null");

        E[] enums = type.getEnumConstants();
        for (E e : enums) {
            if (e.name().equalsIgnoreCase(name)) {
                return e;
            }
        }
        return null;
    }

    public static <E extends Enum<E>> E getByOrdinal(Class<E> type, int ordinal) {
        Objects.requireNonNull(type, "type must not be null");

        E[] enums = type.getEnumConstants();
        if (ordinal >= 0 && ordinal < enums.length) {
            return enums[ordinal];
        } else {
            return null;
        }
    }

}
