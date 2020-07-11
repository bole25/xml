package com.example.requestservice.configuration;

import com.example.requestservice.rabbit.QueueConsumerCart;
import com.example.requestservice.rabbit.QueueConsumerReject;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

   private static final String LISTENER_METHOD = "receiveMessage";

    @Value("${queue.name.cart}")
    private String queueNameCart;

    @Value("${queue.name.reject}")
    private String queueNameReject;

    @Value("${fanout.exchange}")
    private String fanoutExchange;

    @Bean
    Queue queueCart() {
        return new Queue(queueNameCart, true);
    }


    @Bean
    Queue queueReject() {
        return new Queue(queueNameReject, true);
    }

    @Bean
    FanoutExchange exchange(){
        return new FanoutExchange(fanoutExchange);
    }

    @Bean
    Binding binding1(Queue queueCart, FanoutExchange exchange) {
        return BindingBuilder.bind(queueCart).to(exchange);
    }

    @Bean
    Binding binding2(Queue queueReject, FanoutExchange exchange) {
        return BindingBuilder.bind(queueReject).to(exchange);
    }

    @Bean
    SimpleMessageListenerContainer containerCart(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapterCart){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueNameCart);
        container.setMessageListener(listenerAdapterCart);
        return  container;
    }

    @Bean
    SimpleMessageListenerContainer containerReject(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapterReject){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueNameReject);
        container.setMessageListener(listenerAdapterReject);
        return  container;
    }

    @Bean
    MessageListenerAdapter listenerAdapterCart(QueueConsumerCart consumer){
        return new MessageListenerAdapter(consumer, LISTENER_METHOD);
    }

    @Bean
    MessageListenerAdapter listenerAdapterReject(QueueConsumerReject consumer){
        return new MessageListenerAdapter(consumer, LISTENER_METHOD);
    }
}
