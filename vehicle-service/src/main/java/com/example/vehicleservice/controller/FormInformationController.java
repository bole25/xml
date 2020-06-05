package com.example.vehicleservice.controller;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/formDetails")
public class FormInformationController {

    @GetMapping("/brands")
    public CloseableHttpResponse getBrands(){
        System.out.println("pogodjen vehicle endpoint");

        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("http://admin-service:8082/brand/getall");
        try {
             return httpclient.execute(get);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
