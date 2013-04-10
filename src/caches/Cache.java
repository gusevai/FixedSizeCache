package caches;

/**
 * Created with IntelliJ IDEA.
 * User: Иришка
 * Date: 10.04.13
 */
public interface Cache<K, V> {
    void put(K key, V value);

    V get(K key);
}
