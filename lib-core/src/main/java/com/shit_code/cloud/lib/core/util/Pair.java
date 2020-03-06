package com.shit_code.cloud.lib.core.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author Anthony Chen
 * @date 2020/3/6
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pair<K, V> {
    private K key;
    private V value;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return key.equals(pair.key) &&
                value.equals(pair.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
