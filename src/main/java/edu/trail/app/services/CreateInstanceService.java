package edu.trail.app.services;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Component
@Service
public class CreateInstanceService {
    @Autowired
    private ZeebeClient client;

    public void createInstance(String processId, Map<String, Object> processVariables)
    {
        ProcessInstanceEvent event=    client
                .newCreateInstanceCommand()
                .bpmnProcessId(processId)
                .latestVersion()
                .variables(processVariables )
                .send()
                .join();

        System.out.println("started instance for workflowKey='"+event.getProcessDefinitionKey()+"', " +
                        "bpmnProcessId='"+event.getBpmnProcessId()+"'");
    }
}
