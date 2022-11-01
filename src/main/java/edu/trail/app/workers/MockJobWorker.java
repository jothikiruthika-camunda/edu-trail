package edu.trail.app.workers;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.model.bpmn.BpmnModelException;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import io.camunda.zeebe.spring.client.exception.ZeebeBpmnError;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;

@Component
public class MockJobWorker {

    @ZeebeWorker(type = "mock", autoComplete = true)
    public void handleMockJob(final ActivatedJob job) {
        System.out.println(
        "complete job\n>>> [type: {" + job.getType() + "}, action: " + job.getCustomHeaders().get("action"));
    }

    @ZeebeWorker(type = "checkErm", autoComplete = true)
    public void handleErmValidation(final ActivatedJob job) {
        Random rd = new Random();
        var result = rd.nextBoolean();
        System.out.println(
                "ERM Validation Mock\n>>> [type: {" + job.getType() + "}, action: " +
                        job.getCustomHeaders().get("action") + ", IsValid : " + result);
        if (!result)
        {
            throw new ZeebeBpmnError("ERMUnreachable","The ERM system is not reachable!");
        }

    }
}
