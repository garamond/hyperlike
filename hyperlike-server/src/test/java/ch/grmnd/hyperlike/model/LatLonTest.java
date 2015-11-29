package ch.grmnd.hyperlike.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LatLonTest extends ModelTestBase {

    @Test
    public void testLatLon() throws Exception {

        LatLon latLon1 = new LatLon(1.0, 1.0);
        LatLon latLon2 = new LatLon(1.0, 1.0);
        Assert.assertEquals(latLon1, latLon2);
        Assert.assertEquals(latLon1.hashCode(), latLon2.hashCode());

    }
}