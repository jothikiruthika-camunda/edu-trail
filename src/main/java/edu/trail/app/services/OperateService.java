package edu.trail.app.services;

import io.camunda.operate.CamundaOperateClient;
import io.camunda.operate.auth.SaasAuthentication;
import io.camunda.operate.auth.SelfManagedAuthentication;
import io.camunda.operate.auth.SimpleAuthentication;
import io.camunda.operate.dto.ProcessDefinition;
import io.camunda.operate.exception.OperateException;
import io.camunda.operate.search.ProcessDefinitionFilter;
import io.camunda.operate.search.SearchQuery;
import io.camunda.operate.search.Sort;
import io.camunda.operate.search.SortOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperateService {
    private CamundaOperateClient client;
    @Value("${zeebe.client.cloud.client-id:notProvided}")
    private String clientId;

    @Value("${zeebe.client.cloud.client-secret:notProvided}")
    private String clientSecret;

    @Value("${zeebe.client.cloud.clusterId:notProvided}")
    private String clusterId;

    @Value("${zeebe.client.cloud.region:notProvided}")
    private String region;
    private CamundaOperateClient getCamundaOperateClient() throws OperateException {
        if (client == null) {
            if (!"notProvided".equals(clientId)) {
                SaasAuthentication sa = new SaasAuthentication(clientId, clientSecret);
                client =
                        new CamundaOperateClient.Builder()
                                .operateUrl("https://" + region + ".operate.camunda.io/" + clusterId)
                                .authentication(sa)
                                .build();
            } else
            {
            SimpleAuthentication sa = new SimpleAuthentication("demo", "demo", "http://localhost:8081");
            client = new CamundaOperateClient.Builder().operateUrl("http://localhost:8081").authentication(sa).build();
        }}
        return client;
    }

    public List<ProcessDefinition> getProcessDefinitions() throws OperateException {
        ProcessDefinitionFilter processDefinitionFilter = new ProcessDefinitionFilter.Builder().build();
        SearchQuery procDefQuery =
                new SearchQuery.Builder()
                        .withFilter(processDefinitionFilter)
                        .withSize(1000)
                        .withSort(new Sort("bpmnProcessId", SortOrder.DESC))
                        .withSort(new Sort("version", SortOrder.ASC))
                        .build();

        return getCamundaOperateClient().searchProcessDefinitions(procDefQuery);
    }

    public List<ProcessDefinition> getUniqueProcessDefinitions() throws OperateException {
        ProcessDefinitionFilter processDefinitionFilter = new ProcessDefinitionFilter.Builder().build();
        processDefinitionFilter.setVersion((long)1);
        SearchQuery procDefQuery =
                new SearchQuery.Builder()
                        .withFilter(processDefinitionFilter)
                        .withSize(1000)
                        .withSort(new Sort("bpmnProcessId", SortOrder.DESC))
                        .build();

        return getCamundaOperateClient().searchProcessDefinitions(procDefQuery);
    }
}