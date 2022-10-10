package kg.ANA.dto;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Costumer {
    public static void main(String[] args) {
        String sensorName = "ANATAN";
//        String url = "http://localhost:8080/sensors/registration";
//        Map<String, Object> jsonData = new HashMap<>();
//        jsonData.put("name", sensorName);
        HttpHeaders request = new HttpHeaders();
        request.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Object> res = new HttpEntity<>(jsonData, request);
        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.postForEntity(url, res, String.class);
        Random random = new Random();
        double maxTemperature = 100;
        double minTemperature = -100;
        String url_1 = "http://localhost:8080/measurements/add";
        for (int i = 0; i < 500; i++) {
            System.out.println(i);
            Map<String, Object> jsonData_1 = new HashMap<>();
            jsonData_1.put("value", maxTemperature * random.nextDouble() + minTemperature * random.nextDouble());
            jsonData_1.put("raining", random.nextBoolean());
            jsonData_1.put("sensor", Map.of("name", sensorName));
            HttpEntity<Object> res_1 = new HttpEntity<>(jsonData_1, request);
            restTemplate.postForEntity(url_1, res_1, String.class);
        }
    }

}
