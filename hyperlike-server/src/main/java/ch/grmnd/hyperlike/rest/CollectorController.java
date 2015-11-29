package ch.grmnd.hyperlike.rest;

import ch.grmnd.hyperlike.model.collect.CollectionInput;
import ch.grmnd.hyperlike.service.collect.CollectorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST interface for collecting survey input from client application.
 */
@RestController
final class CollectorController {

    Logger logger = LoggerFactory.getLogger(getClass());

    public static final String ENDPOINT = "/api/collect";

    @Autowired
    CollectorService collectorService;

    @RequestMapping(value = CollectorController.ENDPOINT, method = RequestMethod.POST)
    void post(@RequestBody CollectionInput input) {
        collectorService.save(input);
    }

}
