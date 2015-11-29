package ch.grmnd.hyperlike;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HyperlikeTestCfg {

    @Bean
    Mongo mongo() {
        return new Fongo("hyperlike-test").getMongo();
    }

}
