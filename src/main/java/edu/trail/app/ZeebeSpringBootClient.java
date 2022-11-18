package edu.trail.app;

import edu.trail.app.services.CreateInstanceService;
import edu.trail.app.services.MessageService;
import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeDeployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableZeebeClient
@EnableScheduling
@ZeebeDeployment(resources = "classpath*:/*.dmn")
public class ZeebeSpringBootClient {

    public ZeebeSpringBootClient() {
    }

    @Autowired
    public ZeebeSpringBootClient(CreateInstanceService createInstanceInj, MessageService messageServiceinj) {
        createInstance = createInstanceInj;
        messageService = messageServiceinj;
    }

    @Autowired
    private static CreateInstanceService createInstance;

    @Autowired
    private static MessageService messageService;

    public static void main(String[] args) {
        SpringApplication.run(ZeebeSpringBootClient.class, args);

//        Map<String,Object > variables = new HashMap<>();
//        String riskname = "3 Risk From Intellij Springboot";
//        variables.put("riskName",riskname);
//
//        createInstance.createInstance("Process_RiskAssessment", variables);
//        messageService.sendRejectMessage(riskname);
    }
}