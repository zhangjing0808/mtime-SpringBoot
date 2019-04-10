package com.zhangjing.config;

/**
 * redis缓存配置
 */
//
//@Configuration
//@EnableCaching
//public class RedisConfig extends CachingConfigurerSupport {
//
//	@Value("${spring.redis.host}")
//	private String host;
//	@Value("${spring.redis.port}")
//	private int port;
//	@Value("${spring.redis.timeout}")
//	private int timeout;
//
//	//缓存管理器
//	@Bean
//	public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
//		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
//		//设置缓存过期时间
//		cacheManager.setDefaultExpiration(10000);
//		return cacheManager;
//	}
//	@Bean
//	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory){
//		StringRedisTemplate template = new StringRedisTemplate(factory);
//		//设置序列化工具
//		setSerializer(template);
//		template.afterPropertiesSet();
//		return template;
//	}
//	private void setSerializer(StringRedisTemplate template){
//		@SuppressWarnings({ "rawtypes", "unchecked" })
//		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//		ObjectMapper om = new ObjectMapper();
//		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//		jackson2JsonRedisSerializer.setObjectMapper(om);
//		template.setValueSerializer(jackson2JsonRedisSerializer);
//	}
//}
