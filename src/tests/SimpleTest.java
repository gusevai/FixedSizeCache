package tests;

import caches.Cache;
import caches.FIFOFixedSizeCache;
import caches.LRUFixedSizeCache;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Иришка
 * Date: 10.04.13
 */
public class SimpleTest {

    @Test
    public void testOne() {
        Cache<Integer, String> fifoCache = new FIFOFixedSizeCache<Integer, String>(2);
        fifoCache.put(1, "1");
        fifoCache.put(2, "2");
        fifoCache.put(3, "3");
        fifoCache.put(4, "4");

        Cache<Integer, String> lruCache = new FIFOFixedSizeCache<Integer, String>(2);
        lruCache.put(1, "1");
        lruCache.put(2, "2");
        lruCache.put(3, "3");
        lruCache.put(4, "4");

        assertTrue(fifoCache.get(1) == null);
        assertTrue(fifoCache.get(2) == null);
        assertTrue(fifoCache.get(3) != null);
        assertTrue(fifoCache.get(4) != null);

        assertTrue(lruCache.get(1) == null);
        assertTrue(lruCache.get(2) == null);
        assertTrue(lruCache.get(3) != null);
        assertTrue(lruCache.get(4) != null);
    }

    @Test
    public void testTwo() throws InterruptedException {
        Cache<Integer, String> fifoCache = new FIFOFixedSizeCache<Integer, String>(2);
        fifoCache.put(1, "1");
        fifoCache.put(2, "2");
        fifoCache.put(3, "3");
        fifoCache.put(4, "4");

        Cache<Integer, String> lruCache = new LRUFixedSizeCache<Integer, String>(2);
        lruCache.put(1, "1");
        Thread.sleep(5);
        lruCache.put(2, "2");
        lruCache.put(3, "3");
        Thread.sleep(5);
        lruCache.get(2);
        lruCache.put(4, "4");

        assertTrue(fifoCache.get(1) == null);
        assertTrue(fifoCache.get(2) == null);
        assertTrue(fifoCache.get(3) != null);
        assertTrue(fifoCache.get(4) != null);

        assertTrue(lruCache.get(1) == null);
        assertTrue(lruCache.get(2) != null);
        assertTrue(lruCache.get(3) == null);
        assertTrue(lruCache.get(4) != null);
    }
}
