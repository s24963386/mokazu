package com.mokazu.redis;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDao extends RedisGeneratorDao<String, Account> {

	public boolean add(final Account a) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key = serializer.serialize(a.getId());
				byte[] name = serializer.serialize(a.getPass());
				return connection.setNX(key, name);
			}
		});
		return result;
	}

	public Account get(final String keyId) {
		Account result = redisTemplate.execute(new RedisCallback<Account>() {
			@Override
			public Account doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key = serializer.serialize(keyId);
				byte[] value = connection.get(key);
				if (value == null) {
					return null;
				}
				String pass = serializer.deserialize(value);
				Account a = new Account();
				a.setId(keyId);
				a.setPass(pass);
				return a;
			}
		});
		return result;
	}

}
