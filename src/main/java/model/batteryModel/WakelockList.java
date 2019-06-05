package model.batteryModel;

public class WakelockList implements Comparable {

    private String name;
    private float full_time_msec;
    private float full_count;
    private float partial_time_msec;
    private float partial_count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getFull_time_msec() {
        return full_time_msec;
    }

    public void setFull_time_msec(float full_time_msec) {
        this.full_time_msec = full_time_msec;
    }

    public float getFull_count() {
        return full_count;
    }

    public void setFull_count(float full_count) {
        this.full_count = full_count;
    }

    public float getPartial_time_msec() {
        return partial_time_msec;
    }

    public void setPartial_time_msec(float partial_time_msec) {
        this.partial_time_msec = partial_time_msec;
    }

    public float getPartial_count() {
        return partial_count;
    }

    public void setPartial_count(float partial_count) {
        this.partial_count = partial_count;
    }


    public int compareTo(Object o) {
        return (int) (((WakelockList)o).full_count - this.full_count);
    }
}
