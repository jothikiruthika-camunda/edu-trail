package edu.trail.app.services;

import io.camunda.zeebe.client.ZeebeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class MessageService {
    @Autowired
    private ZeebeClient client;

    public void publishMessage(String messageName,String correlationKey)
    {
        client.newPublishMessageCommand()
                .messageName(messageName)
                .correlationKey(correlationKey)
                .send();
    }
}
