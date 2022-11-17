package edu.trail.app.facade;

import edu.trail.app.services.OperateService;
import io.camunda.operate.dto.ProcessDefinition;
import io.camunda.operate.exception.OperateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    private OperateService operateService;

    @GetMapping("/all")
    public List<ProcessDefinition> getAllProcesses() throws OperateException {
      return  operateService.getProcessDefinitions();
    }

    @GetMapping("/count")
    public long getProcessCount() throws OperateException {
        var processess =  operateService.getProcessDefinitions().stream().map(m->m.getBpmnProcessId()).distinct().collect(Collectors.toList());
        return processess.stream().count();
    }
}
