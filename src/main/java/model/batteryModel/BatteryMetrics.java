package model.batteryModel;

public class BatteryMetrics {

    private float DevicePowerPrediction;
    private float CPUPowerPrediction;

    public float getDevicePowerPrediction() {
        return DevicePowerPrediction;
    }

    public void setDevicePowerPrediction(float devicePowerPrediction) {
        DevicePowerPrediction = devicePowerPrediction;
    }

    public float getCPUPowerPrediction() {
        return CPUPowerPrediction;
    }

    public void setCPUPowerPrediction(float CPUPowerPrediction) {
        this.CPUPowerPrediction = CPUPowerPrediction;
    }

}
