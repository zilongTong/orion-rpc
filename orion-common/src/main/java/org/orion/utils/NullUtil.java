package org.orion.utils;

import java.util.HashMap;
import java.util.Objects;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-22
 */
public final class NullUtil {
    public static Integer nonNullInteger(Integer integer) {
        return Objects.isNull(integer) ? 0 : integer;
    }

    public static int nonNullIntegerToInt(Integer integer) {
        return Objects.isNull(integer) ? 0 : integer;
    }

    public static <K, V> HashMap<K, V> nonNullHashMap(HashMap<K,V> map) {
        return Objects.isNull(map) ? new HashMap<>() : map;
    }
}
