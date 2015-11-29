package ch.grmnd.hyperlike.model.collect;

import ch.grmnd.hyperlike.model.ModelTestBase;
import ch.grmnd.hyperlike.model.LatLon;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
public class CollectionInputTest extends ModelTestBase {

    @Test
    public void testSerializationEquals() throws Exception {

        Map<String, Double> values = new HashMap<>();
        values.put("foo", 1.2);
        values.put("bar", 99.2);
        CollectionInput collectionInput = new CollectionInput(new LatLon(0.0,0.0), new Date(0), values);
        String json = objectMapper.writeValueAsString(collectionInput);
        CollectionInput collectionInput2 = objectMapper.readValue(json, CollectionInput.class);
        Assert.assertEquals(collectionInput, collectionInput2);
        Assert.assertEquals(collectionInput.hashCode(), collectionInput2.hashCode());

    }
}