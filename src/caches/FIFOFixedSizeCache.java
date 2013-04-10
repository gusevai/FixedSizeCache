package caches;

import java.util.PriorityQueue;

/**
 * Created with IntelliJ IDEA.
 * User: Иришка
 * Date: 10.04.13
 */
public class FIFOFixedSizeCache<K, V> extends FixedSizeCache<K, V> {
    private PriorityQueue keys;

    public FIFOFixedSizeCache(int cacheSize) {
        super(cacheSize);

        keys = new PriorityQueue<K>(cacheSize);
    }

    @Override
    protected void addValue(K key, V value) {
        keys.offer(key);
        map.put(key, value);
    }

    @Override
    protected void removeOutdatedValue() {
        Object oldKey = keys.poll();
        map.remove(oldKey);
    }
}
