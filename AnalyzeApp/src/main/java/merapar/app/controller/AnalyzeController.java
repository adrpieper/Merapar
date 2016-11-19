package merapar.app.controller;


import merapar.app.controller.dto.AnalyzeResponseDTO;
import merapar.app.service.AnalyzeService;
import merapar.app.controller.dto.AnalyzeRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnalyzeController {

    private final AnalyzeService analyzeService;

    @Autowired
    public AnalyzeController(AnalyzeService analyzeService) {
        this.analyzeService = analyzeService;
    }

    @RequestMapping(value = "/analyze", method = RequestMethod.POST)
    public AnalyzeResponseDTO analyze(@RequestBody AnalyzeRequestDTO analyzeRequestDTO) {

        return analyzeService.analyzeFile(analyzeRequestDTO.getUrl());
    }
}
