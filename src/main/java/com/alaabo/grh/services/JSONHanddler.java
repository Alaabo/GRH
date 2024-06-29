package com.alaabo.grh.services;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
public class JSONHanddler {
//TODO:source of learning
        public void jsonhandller(String path) throws IOException {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(new File(path));
            String name = jsonNode.get("name").asText();
            int age = jsonNode.get("age").asInt();
            String city = jsonNode.get("city").asText();
            String state = jsonNode.get("state").asText();
            String country = jsonNode.get("country").asText();
            System.out.println("Name: " + name);
            System.out.println("Age: " + age);
            System.out.println("City: " + city);
            System.out.println("State: " + state);
            System.out.println("Country: " + country);
        }


}
