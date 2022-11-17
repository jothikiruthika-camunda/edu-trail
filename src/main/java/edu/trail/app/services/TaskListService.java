package edu.trail.app.services;

import io.camunda.tasklist.CamundaTaskListClient;
import io.camunda.tasklist.auth.SaasAuthentication;
import io.camunda.tasklist.auth.SelfManagedAuthentication;
import io.camunda.tasklist.auth.SimpleAuthentication;
import io.camunda.tasklist.dto.Task;
import io.camunda.tasklist.dto.TaskState;
import io.camunda.tasklist.exception.TaskListException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Component
public class TaskListService {

    @Value("${zeebe.client.cloud.client-id:notProvided}")
    private String clientId;

    @Value("${zeebe.client.cloud.client-secret:notProvided}")
    private String clientSecret;

    @Value("${zeebe.client.cloud.clusterId:notProvided}")
    private String clusterId;

    @Value("${zeebe.client.cloud.region:notProvided}")
    private String region;
    private CamundaTaskListClient camundaTaskListClient;

//    SelfManagedAuthentication sma =
//            new SelfManagedAuthentication()
//                    .clientId(identityClientId)
//                    .clientSecret(identityClientSecret)
//                    .keycloakUrl(keycloakUrl);
//    client =
//            new CamundaTaskListClient.Builder()
//            .shouldReturnVariables()
//                .taskListUrl(tasklistUrl)
//                .authentication(sma)
//                .build();

    private CamundaTaskListClient getCamundaTaskListClient() throws TaskListException {
        if (camundaTaskListClient == null) {
            if (!"notProvided".equals(clientId)) {
                SaasAuthentication sa = new SaasAuthentication(clientId, clientSecret);
                camundaTaskListClient =
                        new CamundaTaskListClient.Builder()
                                .shouldReturnVariables()
                                .taskListUrl("https://" + region + ".tasklist.camunda.io/" + clusterId)
                                .authentication(sa)
                                .build();
            }
            else
            {
            SimpleAuthentication sa = new SimpleAuthentication("demo", "demo");
            camundaTaskListClient = new CamundaTaskListClient.Builder().taskListUrl("http://localhost:8082").shouldReturnVariables().authentication(sa).build();
            }
        }
        return camundaTaskListClient;
    }

    public List<Task> getAllUnassignedTasks(int count) throws TaskListException {
        List<Task> tasks = getCamundaTaskListClient().getTasks(false, TaskState.CREATED,count);
        return tasks;
    }

    public List<Task> getAllUnassignedTasks(int count, String processName) throws TaskListException {
        List<Task> tasks = getCamundaTaskListClient().getTasks(false, TaskState.CREATED,count);
        return tasks;
    }

    public Task getTask(String taskId) throws TaskListException {
        return getCamundaTaskListClient().getTask(taskId);
    }

    public void completeTask(String taskId, Map<String, Object> variables) throws TaskListException {
        getCamundaTaskListClient().completeTask(taskId, variables);
    }
}
