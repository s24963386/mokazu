
package com.mokazu.utils;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;


public class CacheUtils
{
	public static void clearCaches()
	{
		final List<CacheManager> allCacheManagers = CacheManager.ALL_CACHE_MANAGERS;
		for (final CacheManager cacheManager : allCacheManagers)
		{
			cacheManager.clearAll();
		}
	}
	
	public static void clearCache(String cacheId)
	{
		final List<CacheManager> allCacheManagers = CacheManager.ALL_CACHE_MANAGERS;
		for (final CacheManager cacheManager : allCacheManagers)
		{
			final Cache cache = cacheManager.getCache(cacheId);
			if (cache != null)
			{
				cache.removeAll();
			}
		}
	}
	
	public static void clearAll()
	{
		final List<CacheManager> allCacheManagers = CacheManager.ALL_CACHE_MANAGERS;
		for (final CacheManager cacheManager : allCacheManagers)
		{
			cacheManager.clearAll();
		}
	}
}
