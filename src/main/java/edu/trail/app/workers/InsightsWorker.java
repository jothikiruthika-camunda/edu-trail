package edu.trail.app.workers;

import edu.trail.app.services.OperateService;
import edu.trail.app.services.TaskListService;
import io.camunda.operate.exception.OperateException;
import io.camunda.tasklist.exception.TaskListException;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InsightsWorker {

    @Autowired
    private OperateService operateService;
    @Autowired
    private TaskListService taskListService;

    @ZeebeWorker(type = "getinsights", autoComplete = true)
    public Map<String, Long> getInsights(final ActivatedJob job) throws OperateException, TaskListException {
        System.out.println("complete job\n>>> [type: {" + job.getType() + "}");
        Map<String, Long> result = new HashMap<>();
        result.put("process-count",  operateService.getUniqueProcessDefinitions().stream().count());
        result.put("task-count",  taskListService.getAllUnassignedTasks(1000).stream().count());

        return result;
    }
}
