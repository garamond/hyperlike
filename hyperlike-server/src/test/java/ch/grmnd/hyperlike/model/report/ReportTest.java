package ch.grmnd.hyperlike.model.report;

import ch.grmnd.hyperlike.model.ModelTestBase;
import ch.grmnd.hyperlike.model.analysis.AnalysedValue;
import ch.grmnd.hyperlike.model.analysis.AnalysedValueTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class ReportTest extends ModelTestBase {

    @Test
    public void testEquals() throws Exception {
        Report r1 = new Report(AnalysedValueTest.FIXTURE, new HashSet<AnalysedValue>());
        String json = objectMapper.writeValueAsString(r1);
        Report r2 = objectMapper.readValue(json, Report.class);
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }
}