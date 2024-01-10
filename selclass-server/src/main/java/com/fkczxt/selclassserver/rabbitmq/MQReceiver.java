package com.fkczxt.selclassserver.rabbitmq;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.fkczxt.selclassserver.config.JsonUtil;
import com.fkczxt.selclassserver.mapper.ClassMapper;
import com.fkczxt.selclassserver.mapper.CsMapper;
import com.fkczxt.selclassserver.pojo.Class;
import com.fkczxt.selclassserver.pojo.RespBean;
import com.fkczxt.selclassserver.pojo.SelclassMessage;
import com.fkczxt.selclassserver.service.impl.ClassServiceImpl;
import com.fkczxt.selclassserver.service.impl.CsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j

public class MQReceiver {

@Autowired
   private CsServiceImpl csService;

    @RabbitListener(queues = "selclassQueue")

    public void receive(String message) {
        log.info("接收消息：" + message);
        SelclassMessage selclassMessage = JsonUtil.jsonStr2Object(message, SelclassMessage.class);
        if (selclassMessage != null) {
            csService.selclass(selclassMessage.getCs());
        }

    }

}
