package edu.trail.app.facade;

import edu.trail.app.services.TaskListService;
import io.camunda.tasklist.dto.Task;
import io.camunda.tasklist.exception.TaskListException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskListService taskListService;

    @GetMapping("/unassigned/{count}/{process}")
    public List<Task> getAllUnassignedTasks(@PathVariable int count, @PathVariable String process ) throws TaskListException {
        return taskListService.getAllUnassignedTasks(count);
    }

    @GetMapping("/get/{taskId}")
    public Task getTask(@PathVariable String taskId ) throws TaskListException {
        return taskListService.getTask(taskId);
    }

    @PostMapping("/complete/{taskId}")
    public void completeTask(@PathVariable String taskId, @RequestBody Map<String, Object> variables)
            throws TaskListException {

        System.out.println("Completing task " + taskId + "` with variables: " + variables);
        taskListService.completeTask(taskId, variables);
    }
}
