# simple-cache

A simple caching app based java. Implemented for memory cache but you can implement this for different cache types.


## How to use

1. Add the following maven dependency:

		<dependency>
			<groupId>com.example</groupId>
			<artifactId>simple-cache</artifactId>
			<version>1.0.0-SNAPSHOT</version>
		</dependency>

2. Create a single cache(expire time is 30 sec)

        Cache<String> cache = new InMemoryCache<>(30000);
        
3. Put a value to the single cache

        cache.put("value1");

4. Read the single cache

        String cacheVal = cache.get();
        
5. Remove the single cache

        cache.remove();
        
6. Create a cache list(expire time is 30 sec)

        CacheList<String, String> caches = new InMemoryCacheList<>(30000);
        
7. Put some values to the cache list

        caches.put("key1", "val1");
        caches.put("key2", "val2");
        
8. Read the cache value from the cache list

        String val1 = caches.get("key1");
       
9. Remove the cache from the cache list

        caches.remove("key1");
        
10. Get the size of the cache list

        caches.size();
        
11. Remove all caches from the cache list

        caches.clear();
