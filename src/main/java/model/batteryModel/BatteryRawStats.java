package model.batteryModel;

import java.util.List;

public class BatteryRawStats {


    private String name;
    private String version_code;
    private String version_name;

    private Apk apk;
    private List<Process> process;
    private List<WakelockList> wakelock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion_code() {
        return version_code;
    }

    public void setVersion_code(String version_code) {
        this.version_code = version_code;
    }

    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }

    public Apk getApk() {
        return apk;
    }

    public void setApk(Apk apk) {
        this.apk = apk;
    }

    public List<Process> getProcess() {
        return process;
    }

    public void setProcess(List<Process> process) {
        this.process = process;
    }

    public List<WakelockList> getWakelock() {
        return wakelock;
    }

    public void setWakelock(List<WakelockList> wakelock) {
        this.wakelock = wakelock;
    }






}
