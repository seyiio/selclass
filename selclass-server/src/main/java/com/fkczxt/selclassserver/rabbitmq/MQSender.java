package com.fkczxt.selclassserver.rabbitmq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@Slf4j
public class MQSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void  sendselclassMessage(String message){
        log.info("发送消息" + message);
        rabbitTemplate.convertAndSend("selclassExchange", "selclass.message", message);
    }
}
