package com.mario.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Mario on 2017/9/5.
 * 计算Controller
 */
@RestController
public class ComputeController {
    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Integer add(@RequestParam Integer a,@RequestParam Integer b){
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/add , HOST:" + instance.getHost() + ", serviceId:" + instance.getServiceId() + " result:" + (a+b));
        return a+b;
    }

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.client.getInstances(applicationName);
    }
}
