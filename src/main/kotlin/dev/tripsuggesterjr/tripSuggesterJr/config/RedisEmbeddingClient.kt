//package dev.tripsuggesterjr.tripSuggesterJr.config

//import org.springframework.ai.embedding.EmbeddingClient

//import org.springframework.data.redis.core.RedisTemplate
////import org.springframework.data.redis.core.RedisTemplate
//
//@Component
//class RedisEmbeddingClient @Autowired constructor(private val redisTemplate: RedisTemplate<String, ByteArray>) : EmbeddingClient {
//
//    fun retrieveEmbedding(key: String): ByteArray? {
//        return redisTemplate.opsForValue().get(key)
//    }
//
//    // You may need to implement other methods of EmbeddingClient interface as per your requirement
//}