package util;

import model.batteryModel.ServiceList;
import model.grafanaMetricModel.KeyMetric;
import model.grafanaMetricModel.MetricPayload;

import java.util.ArrayList;

public class ReadDatafromJson {

    public static ArrayList<KeyMetric> getServicesList(MetricPayload metricPayload){

        ArrayList<KeyMetric> serviceLists = new ArrayList<KeyMetric>();
        for(KeyMetric arrayList: metricPayload.getMetrics()){
            serviceLists.add(arrayList);
        }
        return serviceLists;
    }
}
