package avm.io.travel.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CoreController {


    @RequestMapping(method = RequestMethod.GET)
    public String helloWorld() {
        return "Hello World";
    }

}
