package com.taoroot.redis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.taoroot.redis.entiy.FarmAgentMessage;
import com.taoroot.service.IAgentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ResourceBundle;

/**
 * @author: taoroot
 * @date: 2018/1/29
 * @description:
 */
public class TopicMessageListener implements MessageListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(TopicMessageListener.class);
    private static final ResourceBundle bundle = ResourceBundle.getBundle("redis");

    private final static String REDIS_BACK_TO_AGENT = bundle.getString("RedisBackToAgentTopic");

    @Autowired
    private IAgentService iAgentService;

    private RedisTemplate redisTemplate;

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        //请使用valueSerializer
        byte[] body = message.getBody();
        byte[] channel = message.getChannel();

        String itemValue = (String) redisTemplate.getValueSerializer().deserialize(body);
        String topic = (String) redisTemplate.getStringSerializer().deserialize(channel);

        // 序列化
        Gson gson = new GsonBuilder().create();
        FarmAgentMessage farmAgentMessage = gson.fromJson(itemValue, FarmAgentMessage.class);
        // 日志打印
        LOGGER.info("agent ==> back: {} ", itemValue);

        // 业务处理
        FarmAgentMessage response = farmAgentMessageHandler(farmAgentMessage);

        // 回应结果
        if (response != null) {
            redisTemplate.convertAndSend(REDIS_BACK_TO_AGENT, new Gson().toJson(response));
        }
    }

    private FarmAgentMessage farmAgentMessageHandler(FarmAgentMessage farmAgentMessage) {
        String type = farmAgentMessage.getType();
        FarmAgentMessage response = null;
        switch (type) {
            case "register":
                response = iAgentService.registerHandler(farmAgentMessage);
                break;
            case "temperature":
                response = iAgentService.temperatureHandler(farmAgentMessage);
                break;
            case "humidity":
                response = iAgentService.humidityHandler(farmAgentMessage);
                break;
            default:
                break;
        }
        return response;
    }
}
