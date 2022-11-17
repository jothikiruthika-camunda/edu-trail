package edu.trail.app.facade;

import edu.trail.app.services.OperateService;
import edu.trail.app.services.TaskListService;
import io.camunda.operate.exception.OperateException;
import io.camunda.tasklist.dto.Task;
import io.camunda.tasklist.exception.TaskListException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/insights")
public class InsightsController {

    @Autowired
    private OperateService operateService;
    @Autowired
    private TaskListService taskListService;

    @GetMapping
    public Map<String, Long> Insights( ) throws TaskListException, OperateException {
        Map<String, Long> result = new HashMap<>();
        result.put("process-count",  operateService.getUniqueProcessDefinitions().stream().count());
        result.put("task-count",  taskListService.getAllUnassignedTasks(1000).stream().count());

        return result;
    }
}
