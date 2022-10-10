package kg.ANA.dto;

import java.util.List;

public class MeasurementResponse {
    private List<MeasurementDTO> measurementDTOS;

    public List<MeasurementDTO> getMeasurementDTOS() {
        return measurementDTOS;
    }

    public void setMeasurementDTOS(List<MeasurementDTO> measurementDTOS) {
        this.measurementDTOS = measurementDTOS;
    }
}
