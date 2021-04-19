package com.anykey.web;

import com.anykey.model.domain.Requests;
import com.anykey.service.RequestsService;
import com.anykey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TbRequestsController {

    private RequestsService requestsService;


    @Autowired
    public TbRequestsController(RequestsService requestsService) {
        this.requestsService = requestsService;
    }

    @RequestMapping("/getRequests")
    public List<Requests> getRequests() {
        return requestsService.findAll();
    }

    @RequestMapping(value = "/addRequest", method = RequestMethod.PUT)
    public String newRequests(@RequestBody Requests requests) {
        Requests saved = requestsService.save(requests);
        return saved.getId();
    }


}
