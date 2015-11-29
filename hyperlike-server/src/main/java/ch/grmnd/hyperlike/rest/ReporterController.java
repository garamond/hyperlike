package ch.grmnd.hyperlike.rest;

import ch.grmnd.hyperlike.model.report.Report;
import ch.grmnd.hyperlike.service.analysis.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
final class ReporterController {

    public static final String ENDPOINT = "/api/report";

    @Autowired
    AnalysisService analysisService;

    @RequestMapping(value = ReporterController.ENDPOINT, method = RequestMethod.GET)
    public Report getReport() {
        return new Report(analysisService.getMeta(), analysisService.getValues());
    }

}
