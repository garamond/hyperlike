package ch.grmnd.hyperlike.rest;

import ch.grmnd.hyperlike.model.report.Report;
import ch.grmnd.hyperlike.service.analysis.AnalysisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
public class ReporterControllerTest extends BaseControllerTest {

    @Autowired
    AnalysisService analysisService;

    @Test
    public void testGetReport() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        Report report = restTemplate.getForEntity(buildUrl(ReporterController.ENDPOINT), Report.class).getBody();
        assertEquals(analysisService.getValues().size(), report.getValues().size());
    }
}