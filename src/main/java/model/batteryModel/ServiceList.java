package model.batteryModel;

public class ServiceList implements Comparable {

    private String name;
    private int launches;
    private float start_time_msec;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLaunches() {
        return launches;
    }

    public void setLaunches(int launches) {
        this.launches = launches;
    }


    public int compareTo(Object o) {
        return ((ServiceList)o).launches - this.launches;
    }

    public float getStart_time_msec() {
        return start_time_msec;
    }

    public void setStart_time_msec(float start_time_msec) {
        this.start_time_msec = start_time_msec;
    }
}
