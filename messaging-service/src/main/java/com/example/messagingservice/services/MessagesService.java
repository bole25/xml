package com.example.messagingservice.services;

import com.example.messagingservice.dto.MessageDTO;
import com.example.messagingservice.feignClinet.RequestClient;
import com.example.messagingservice.model.Message;
import com.example.messagingservice.model.UserMessages;
import com.example.messagingservice.repository.MessageRepository;
import com.example.messagingservice.repository.UserMessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class MessagesService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    private UserMessagesRepository userMessagesRepository;

    @Autowired
    private RequestClient requestClient;

    public Set<Message> getSentMessages(String username){
        return messageRepository.getSentMessages(username);
    }

    public Set<Message> getReceivedMessages(String username){
        return messageRepository.getReceivedMessages(username);
    }

    @Transactional
    public boolean sendMessage(String sender, MessageDTO messageDTO){
        //TODO proveri da li sender uopste ima pravo da posalje poruku prodavcu
        // (Da li ime prihvacen (RESERVED) request od tog prodavca)
        Boolean connected = requestClient.checkIfConencted(sender, messageDTO.getReceiver());
        if(!connected){
            return false;
        }

        Message message = new Message(sender, messageDTO);
        message = messageRepository.save(message);

        UserMessages senderMessages = userMessagesRepository.getUserMessagesByUsername(sender);
        if(senderMessages == null){
            return false;
        }
        UserMessages receiverMessages = userMessagesRepository.getUserMessagesByUsername(messageDTO.getReceiver());
        if(receiverMessages == null){
            return false;
        }

        userMessagesRepository.addSentMessages(senderMessages.getId(), message.getId());
        userMessagesRepository.addReceivedMessages(receiverMessages.getId(),message.getId());

        return true;
    }
}
