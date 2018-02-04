package com.taoroot.service;


import com.taoroot.redis.entiy.FarmAgentMessage;

/**
 * @author: taoroot
 * @date: 2018/1/29
 * @description:
 */
public interface IAgentService {

    FarmAgentMessage registerHandler(FarmAgentMessage farmAgentMessage);

    FarmAgentMessage temperatureHandler(FarmAgentMessage farmAgentMessage);

    FarmAgentMessage humidityHandler(FarmAgentMessage farmAgentMessage);
}
