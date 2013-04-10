package caches;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Иришка
 * Date: 10.04.13
 */
public abstract class FixedSizeCache<K, V> implements Cache<K, V> {
    protected final int cacheSize;

    protected HashMap<K, V> map;

    public FixedSizeCache(int cacheSize) {
        this.cacheSize = cacheSize;

        map = new HashMap<K, V>(cacheSize);
    }

    protected abstract void addValue(K key, V value);

    protected abstract void removeOutdatedValue();

    @Override
    public void put(K key, V value) {
        if ((map.size() == cacheSize) && (!map.containsKey(key))) {
            removeOutdatedValue();
        }

        addValue(key, value);
    }

    @Override
    public V get(K key) {
        return map.get(key);
    }
}
