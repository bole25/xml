package com.example.messagingservice.services;

import com.example.messagingservice.dto.MessageDTO;
import com.example.messagingservice.model.Message;
import com.example.messagingservice.model.UserMessages;
import com.example.messagingservice.repository.MessageRepository;
import com.example.messagingservice.repository.UserMessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MessagesService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    private UserMessagesRepository userMessagesRepository;

    public Set<Message> getSentMessages(String username){
        return messageRepository.getSentMessages(username);
    }

    public Set<Message> getReceivedMessages(String username){
        return messageRepository.getReceivedMessages(username);
    }

    public boolean sendMessage(String sender, MessageDTO messageDTO){
        Message message = new Message(sender, messageDTO);
        UserMessages senderMessages = userMessagesRepository.getUserMessagesByUsername(sender);
        if(senderMessages == null){
            return false;
        }
        senderMessages.getSent().add(message);
        UserMessages receiverMessages = userMessagesRepository.getUserMessagesByUsername(messageDTO.getReceiver());
        if(receiverMessages == null){
            return false;
        }
        receiverMessages.getReceived().add(message);
        userMessagesRepository.save(senderMessages);
        userMessagesRepository.save(receiverMessages);

        return true;
    }
}
