package com.example.demo.controller;

import com.example.demo.model.EmployeeInfo;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@RestController
public class IPCController {

    @Autowired
    @Lazy
    EurekaClient eurekaClient;

    @Value("${service.micro_1.serviceName}")
    private String micro_1;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/dashboard/{myId}")
    public EmployeeInfo findMe(@PathVariable Long myId) {
        EmployeeInfo emp = null;
        System.out.printf("micro_1_WM1: " + micro_1);
        Application application = eurekaClient.getApplication(micro_1);
        try {
            InstanceInfo instanceInfo = application.getInstances().get(0);
            String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "employee/find/" + myId;
            System.out.println("URL" + url);
            emp = restTemplate().getForObject(url, EmployeeInfo.class);
            System.out.println("RESPONSE " + emp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }

    @RequestMapping("/dashboard")
    public String findAll() {
        Application application = eurekaClient.getApplication(micro_1);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "employee/findAll";
        System.out.println("URL" + url);
        String emp = restTemplate().getForObject(url, String.class);
        System.out.println("RESPONSE " + emp);
        return emp;
    }
}