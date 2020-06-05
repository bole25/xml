package com.example.vehicleservice.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class GetInfoFromAdminService {

    public String getData(String path){

        HttpURLConnection connection = null;

        String inputLine = "";

        try {
            URL url = new URL("http://admin-service:8082"+path);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            con.getInputStream()));

            String temp;
            while ((temp = in.readLine()) != null)
                inputLine += temp;
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return inputLine;
    }
}
