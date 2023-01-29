package kg.ANA.dto;




public class MeasurementDTO {
    private Double sum;
    private Boolean nonresidentName;
    private SensorDTO sensor;

    public Double getValue() {
        return sum;
    }

    public void setValue(Double value) {
        this.sum = value;
    }

    public Boolean getRaining() {
        return nonresidentName;
    }

    public void setRaining(Boolean raining) {
        this.nonresidentName = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
