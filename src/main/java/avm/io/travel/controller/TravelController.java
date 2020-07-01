package avm.io.travel.controller;

import avm.io.travel.dto.TravelDTO;
import avm.io.travel.persistance.entity.Travel;
import avm.io.travel.service.ITravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/travels")
public class TravelController {

     @Autowired
     private ITravelService service;

    @RequestMapping(method = RequestMethod.POST)
    public TravelDTO save(@RequestBody TravelDTO travel) {
        return service.save(travel);
    }


}
