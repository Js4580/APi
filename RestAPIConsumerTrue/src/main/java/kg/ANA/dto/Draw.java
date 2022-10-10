package kg.ANA.dto;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Draw {
    public static void main(String[] args) {
        String url = "http://localhost:8080/measurements";
        RestTemplate restTemplate = new RestTemplate();
        MeasurementResponse measurementResponse = restTemplate.getForObject(url, MeasurementResponse.class);
        if(measurementResponse == null || measurementResponse.getMeasurementDTOS() == null) System.out.println("провал");;
        List<Double> values = measurementResponse.getMeasurementDTOS().stream().map(MeasurementDTO::getValue).toList();
        double [] xData = IntStream.range(0, values.size()).asDoubleStream().toArray();
        double [] yData = values.stream().mapToDouble(m->m).toArray();
        XYChart chart = QuickChart.getChart("График температуры ", "Иксик", "Игрик", "температура",xData,yData);
        new SwingWrapper<>(chart).displayChart();
    }
}
