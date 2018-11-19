package util;

import model.batteryModel.*;
import model.batteryModel.Process;
import model.grafanaMetricModel.KeyMetric;
import model.grafanaMetricModel.MetricPayload;
import model.grafanaMetricModel.MetricsDeviceMeta;
import sendemail.EmailSender;
import util.RequestGenerator;

import javax.mail.MessagingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CaptureBatteryTracingData {

    static DecimalFormat decimalFormat = new DecimalFormat("#.##");


    public static void generateGrafanaDataFromObject(BatteryRawStats batteryRawMetrics, BatteryMetrics batteryMetrics, String[] deviceName){
        MetricsDeviceMeta metricsDeviceMeta = new MetricsDeviceMeta();
        MetricPayload metricPayload = new MetricPayload();
        KeyMetric devicePower = new KeyMetric();
        KeyMetric cpuPower = new KeyMetric();
        List<KeyMetric> keyMetricList = new ArrayList<KeyMetric>();

        metricsDeviceMeta.setApkVersion(batteryRawMetrics.getVersion_name());
        metricsDeviceMeta.setDeviceName(deviceName[1]);
        if(deviceName[2] != null){
            metricsDeviceMeta.setPlatform(deviceName[2]);
        } else {
            metricsDeviceMeta.setPlatform("Android");
        }

        if(batteryMetrics != null){
            try{
                devicePower.setMethodName("DevicePower");
                devicePower.setMetricType("BatteryUsedDevice");
                devicePower.setValue(Double.valueOf(decimalFormat.format(batteryMetrics.getDevicePowerPrediction())));
                keyMetricList.add(devicePower);
                cpuPower.setMethodName("CpuPower");
                cpuPower.setMetricType("BatteryUsedCpu");
                cpuPower.setValue(Double.valueOf(decimalFormat.format(batteryMetrics.getCPUPowerPrediction())));
                keyMetricList.add(cpuPower);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }

       if(batteryRawMetrics.getApk().getServiceList() != null){
           int counter = 0;
           Collections.sort(batteryRawMetrics.getApk().getServiceList());
           for (ServiceList serviceList: batteryRawMetrics.getApk().getServiceList()){
               KeyMetric serviceKeyMetric = new KeyMetric();
               KeyMetric serviceTimeKeyMetric = new KeyMetric();
               serviceKeyMetric.setMethodName(serviceList.getName());
               serviceKeyMetric.setMetricType("ServiceName");
               serviceKeyMetric.setValue((double) serviceList.getLaunches());
               keyMetricList.add(serviceKeyMetric);

               serviceTimeKeyMetric.setMethodName(serviceList.getName());
               serviceTimeKeyMetric.setMetricType("ServiceTime");
               serviceTimeKeyMetric.setValue(Double.valueOf(decimalFormat.format(serviceList.getStart_time_msec()/1000)));
               keyMetricList.add(serviceTimeKeyMetric);

               counter++;
               if(counter >= 8){
                   break;
               }
           }
       }

        if(batteryRawMetrics.getWakelock() != null){
            int counter = 0;
            Collections.sort(batteryRawMetrics.getWakelock());
            for (WakelockList wakelockList: batteryRawMetrics.getWakelock()){
                KeyMetric keyMetricFullCount = new KeyMetric();
                KeyMetric keyMetricFullTime = new KeyMetric();
                KeyMetric keyMetricPartialCount = new KeyMetric();
                KeyMetric keyMetricPartialTime = new KeyMetric();

                keyMetricFullCount.setMethodName(wakelockList.getName());
                keyMetricFullCount.setMetricType("FullWakelock");
                keyMetricFullCount.setValue((double) wakelockList.getFull_count());
                keyMetricFullTime.setMethodName(wakelockList.getName());
                keyMetricFullTime.setMetricType("FullWakelockTime");
                keyMetricFullTime.setValue(Double.valueOf(decimalFormat.format(wakelockList.getFull_time_msec()/1000)) );

                keyMetricPartialCount.setMethodName(wakelockList.getName());
                keyMetricPartialCount.setMetricType("PartialWakelock");
                keyMetricPartialCount.setValue((double) wakelockList.getPartial_count());
                keyMetricPartialTime.setMethodName(wakelockList.getName());
                keyMetricPartialTime.setMetricType("PartialWakelockTime");
                keyMetricPartialTime.setValue(Double.valueOf(decimalFormat.format(wakelockList.getPartial_time_msec()/1000)));

                keyMetricList.add(keyMetricFullCount);
                keyMetricList.add(keyMetricFullTime);
                keyMetricList.add(keyMetricPartialCount);
                keyMetricList.add(keyMetricPartialTime);
                counter++;
                if(counter >= 5){
                    break;
                }
            }
        }

        if(batteryRawMetrics.getProcess() != null){
            for (Process process: batteryRawMetrics.getProcess()){
                KeyMetric keyMetric = new KeyMetric();
                keyMetric.setMethodName(process.getName());
                keyMetric.setMetricType("AppUsageTime");
                keyMetric.setValue((double) process.getForeground_time_msec()/1000);
                keyMetricList.add(keyMetric);
            }
        }

        metricPayload.setDeviceMeta(metricsDeviceMeta);
        metricPayload.setMetrics(keyMetricList);
        RequestGenerator.createAPIRequest(metricPayload);
        try {
            EmailSender emailSender = new EmailSender(metricPayload);
            emailSender.sendEmail();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
