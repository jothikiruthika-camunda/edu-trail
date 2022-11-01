package edu.trail.app.facade;

import edu.trail.app.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/message")

public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/publish/{messageName}/{correlationKey}")
    public void publishMessage(@PathVariable String messageName, @PathVariable String correlationKey){
        messageService.publishMessage(messageName,correlationKey);
    }
}
