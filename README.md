# simple-cache

A simple caching app based java. Implemented for memory cache but you can implement this for different cache types.


## UML Class Diagram

<p align="left">
  <img src="https://github.com/mesutusenmez/simple-cache/blob/main/src/main/resources/simple-cache-uml.jpg" width="70%" title="hover text">
</p>


## How to use

1. Add the following maven repository and dependency to pom.xml:

		<repositories>
		  <repository>
		    <id>mu-repo</id>
		    <url>https://github.com/mesutusenmez/mvn-repo/tree/main/snapshots</url>
		  </repository>
		</repositories>
		


		<dependency>
			<groupId>com.mu.cache</groupId>
			<artifactId>simple-cache</artifactId>
			<version>1.0.2-SNAPSHOT</version>
		</dependency>

2. Create a single cache(expire time is 30 sec)
        
        Config config = new Config.ConfigBuilder(30).build();
        SingleCache<String> cache = new InMemoryCache<>(config);
        
3. Put a value to the single cache

        cache.put("value1");

4. Read the single cache

        String cacheVal = cache.get();
        
5. Clear the single cache

        cache.clear();
        
6. Create a cache list(expire time is 30 sec)

        Config config = new Config.ConfigBuilder(30).build();
        CacheList<String> caches = new InMemoryCacheList<>(config);
        
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
