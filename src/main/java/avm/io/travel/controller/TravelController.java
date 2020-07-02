package avm.io.travel.controller;

import avm.io.travel.dto.TravelDTO;
import avm.io.travel.service.ITravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/travels")
public class TravelController {

     @Autowired
     private ITravelService service;

    @RequestMapping(method = RequestMethod.POST)
    public TravelDTO save(@RequestBody TravelDTO travel) {
        return service.save(travel);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public TravelDTO show(@PathVariable long id) {
        return service.show(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<TravelDTO> list() {
        return service.list();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAll() {
        service.deleteAll();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteById(@PathVariable long id) {
        service.deleteById(id);
    }


}
