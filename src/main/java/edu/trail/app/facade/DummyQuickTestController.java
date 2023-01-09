package edu.trail.app.facade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@CrossOrigin
@RestController
@RequestMapping("/dummy")
public class DummyQuickTestController {

    @GetMapping
    public String getJson() throws IOException {
       var jsonStr = Files.readString(Path.of("/Users/jothikiruthikaviswanathan/Downloads/PlanDefinitions.json")) ;

       return jsonStr;
    }
}
