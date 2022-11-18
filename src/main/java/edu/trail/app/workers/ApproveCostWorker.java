package edu.trail.app.workers;

import edu.trail.app.dto.MultiInstanceInputDto;
import edu.trail.app.dto.MultiInstanceOutputDto;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.ZeebeVariablesAsType;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ApproveCostWorker {

    @ZeebeWorker(type = "approveCost", autoComplete = true)
    public MultiInstanceOutputDto approveCost(@ZeebeVariablesAsType MultiInstanceInputDto measure, final ActivatedJob job) {
        Random rd = new Random();
        var result = rd.nextBoolean();

        var output = new MultiInstanceOutputDto();
        output.setApprovalResult(measure.getControlValue());
        output.getApprovalResult().setApproved(result);

        System.out.println(
                "Approve Cost worker\n>>> [type: {" + job.getType() + "}, costApproval : " + result);
        return output;

    }
}
