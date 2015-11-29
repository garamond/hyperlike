package ch.grmnd.hyperlike.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class RectangleTest extends ModelTestBase {



    @Test
    public void testBoundariesNorth() throws Exception {
        Rectangle r = new Rectangle(new LatLon(0d,0d), new LatLon(1d,1d));
        List<List<List<Double>>> expected = Arrays.asList(Arrays.asList(Arrays.asList(0.0, 0.0), Arrays.asList(1.0, 0.0), Arrays.asList(1.0, 1.0), Arrays.asList(0.0, 1.0), Arrays.asList(0.0, 0.0)));
        Assert.assertEquals(expected, r.getCoordinates());
    }

    @Test
    public void testBoundariesSouth() throws Exception {
        Rectangle r = new Rectangle(new LatLon(-2d,-2d), new LatLon(-1d,-1d));
        List<List<List<Double>>> expected = Arrays.asList(Arrays.asList(Arrays.asList(-2.0, -2.0), Arrays.asList(-1.0, -2.0), Arrays.asList(-1.0, -1.0), Arrays.asList(-2.0, -1.0), Arrays.asList(-2.0, -2.0)));
        Assert.assertEquals(expected, r.getCoordinates());
    }

    @Test
    public void testEquals() throws Exception {
        Rectangle r1 = new Rectangle(new LatLon(0d,0d), new LatLon(1d,1d));
        String json = objectMapper.writeValueAsString(r1);
        Rectangle r2 = objectMapper.readValue(json, Rectangle.class);
        Assert.assertEquals(r1, r2);
        Assert.assertEquals(r1.hashCode(), r2.hashCode());

    }
}