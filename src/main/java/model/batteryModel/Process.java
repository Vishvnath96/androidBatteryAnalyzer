package model.batteryModel;

import java.util.List;

public class Process {

    private String name;
    private float foreground_time_msec;
    private int starts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getForeground_time_msec() {
        return foreground_time_msec;
    }

    public void setForeground_time_msec(float foreground_time_msec) {
        this.foreground_time_msec = foreground_time_msec;
    }

    public int getStarts() {
        return starts;
    }

    public void setStarts(int starts) {
        this.starts = starts;
    }


}
