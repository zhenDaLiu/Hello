package yuntongxun.ytx.fast.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import yuntongxun.ytx.fast.redis.RedisExpiredListener;

/**
 * redis 监听配置
 * @author sintang
 * @date 2019-08-10
 **/
@Configuration
public class RedisListenerConfig {

    @Autowired
    private RedisExpiredListener redisExpiredListener;

    /**
     * 事件是用  __keyspace@DB__:KeyPattern 或者  __keyevent@DB__:OpsType 的格式来发布消息的。
     * DB表示在第几个库；KeyPattern则是表示需要监控的键模式（可以用通配符，如：__key*__:*）；OpsType则表示操作类型。因此，如果想要订阅特殊的Key上的事件，应该是订阅keyspace。
     * @param factory
     * @return
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory factory){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        // 监听4号库，key过期通知
        container.addMessageListener(redisExpiredListener, new PatternTopic("__keyevent@4__:expired"));
        return container;
    }
}
