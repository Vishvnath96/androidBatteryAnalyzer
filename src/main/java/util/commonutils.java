package util;

import model.batteryModel.ServiceList;
import model.grafanaMetricModel.KeyMetric;
import model.grafanaMetricModel.MetricPayload;
import model.report.ResultCases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

public class commonutils {


    public static String getMyPublicIP(){
        try {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader( whatismyip.openStream()));
            String ip = in.readLine(); //you get the IP as a String
            System.out.println("public ip is :\t"+ip);
            return ip;
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static String buildCasesTable(MetricPayload metricPayload) {
        String scenario = "";
        if(metricPayload.getDeviceMeta().getPlatform().equalsIgnoreCase("overnight")){
            scenario = "Overnight";
        }
        else {
            scenario = "Half an Hour";
        }
        StringBuilder tableString = new StringBuilder();
        tableString.append("<font size=4 color=blue>Battery stats for"+" ");
        tableString.append(scenario);
        tableString.append(" ");
        tableString.append("Scenario:</font>");
        tableString.append("<table border=\"0.5\">");
        tableString.append("<tr bgcolor=\"#FEF9E7\">");
        tableString.append("<th width=\"200\" height=\"50\">Battery Metrices</th>");
        tableString.append("<th width=\"200\" height=\"50\">Values</th>");
        tableString.append("</tr>");

        ArrayList<KeyMetric> services = ReadDatafromJson.getServicesList(metricPayload);
        for (KeyMetric testcase : services){

            if(testcase.getMetricType().equals("BatteryUsedDevice") || testcase.getMetricType().equals("BatteryUsedCpu")){
                tableString.append("<tr bgcolor=\"#AED6F1\">");
                tableString.append("<td align=\"center\" width=\"200\" height=\"50\">" + testcase.getMetricType() + "</td>");
                tableString.append("<td align=\"center\" rowspan = 1>" + testcase.getValue() + "</td>");
                tableString.append("</tr>");
            }
        }
        tableString.append("</table>");
        return tableString.toString();
    }

    public static String buildServiceTable(MetricPayload metricPayload) {

        StringBuilder tableString = new StringBuilder();
        tableString.append("<font size=4 color=blue>Running Service Info:<br></font>");
        tableString.append("<table border=\"0.5\">");
        tableString.append("<tr bgcolor=\"#F6DDCC\">");
        tableString.append("<th>Service Name</th>");
        tableString.append("<th>Launch Count</th>");
        tableString.append("</tr>");

        ArrayList<KeyMetric> services = ReadDatafromJson.getServicesList(metricPayload);
        for (KeyMetric testcase : services){
            if(testcase.getMetricType().equals("ServiceName")){
                tableString.append("<tr bgcolor=\"#F9EBEA\">");
                tableString.append("<td>" + testcase.getMethodName() + "</td>");
                tableString.append("<td align=\"center\">" + testcase.getValue() + "</td>");
                tableString.append("</tr>");
            }

        }
        tableString.append("</table>");

        return tableString.toString();
    }
}
