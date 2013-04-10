package caches;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Иришка
 * Date: 10.04.13
 */
public class LRUFixedSizeCache<K, V> extends FixedSizeCache<K, V> {

    private Map<K, Long> keys;

    public LRUFixedSizeCache(int cacheSize) {
        super(cacheSize);

        keys = new HashMap<K, Long>(cacheSize);
    }

    @Override
    protected void addValue(K key, V value) {
        keys.put(key, System.currentTimeMillis());
        map.put(key, value);
    }

    @Override
    protected void removeOutdatedValue() {
        K oldKey = Collections.min(keys.entrySet(), new Comparator<Map.Entry<K, Long>>() {
            @Override
            public int compare(Map.Entry<K, Long> o1, Map.Entry<K, Long> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        }).getKey();

        keys.remove(oldKey);

        map.remove(oldKey);
    }

    @Override
    public V get(K key) {
        if (!map.containsKey(key)) {
            return null;
        }

        keys.put(key, System.currentTimeMillis());

        return map.get(key);
    }
}
