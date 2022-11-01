package edu.trail.app.facade;

import edu.trail.app.services.CreateInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/risk")

public class RiskController {

    @Autowired
    private CreateInstanceService createInstanceService;

    @PostMapping("/create/{processId}")
    public void createInstance(@PathVariable String processId, @RequestBody Map<String, Object> variables){
        createInstanceService.createInstance(processId,variables);
    }
}
