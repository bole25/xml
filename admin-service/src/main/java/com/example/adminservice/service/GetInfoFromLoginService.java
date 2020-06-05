package com.example.adminservice.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

@Service
public class GetInfoFromLoginService {

	public String getData(String path) {
		
		String inputLine = "";
		
        try {
            URL url = new URL("http://localhost:8089"+path);
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
