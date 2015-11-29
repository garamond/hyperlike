package ch.grmnd.hyperlike.model.analysis;

import ch.grmnd.hyperlike.model.LatLon;
import ch.grmnd.hyperlike.model.ModelTestBase;
import ch.grmnd.hyperlike.model.Rectangle;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
public class AnalysedValueTest extends ModelTestBase {

    public static final AnalysedValue FIXTURE = new AnalysedValue(null, "Test", 0.0, 1.0, 3.0, 3L,
            new Date(0), new Rectangle(new LatLon(0.0, 0.0), new LatLon(1.0,1.0)));
    static {
        FIXTURE.addComputedValue("foo", 2.1);
    }

    @Test
    public void testAnalysedValue() throws Exception {
        String json = objectMapper.writeValueAsString(FIXTURE);
        AnalysedValue v2 = objectMapper.readValue(json, AnalysedValue.class);
        Assert.assertEquals(FIXTURE, v2);
        Assert.assertEquals(FIXTURE.hashCode(), v2.hashCode());
    }
}