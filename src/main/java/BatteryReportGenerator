import builder.BatteryDataBuilder;
import com.google.gson.Gson;
import model.batteryModel.BatteryMetrics;
import model.batteryModel.BatteryRawStats;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import util.CaptureBatteryTracingData;

import java.io.IOException;


public class BatteryReportGenerator
{
    public static void main(String[] args) throws JSONException {
        System.out.println("battery");
        okhttp3.Response response = BatteryDataBuilder.getResponse(args[0]);
        String jsonData = null;
        try {
            jsonData = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray appStats = jsonObject.getJSONArray("UploadResponse").getJSONObject(0).getJSONArray("appStats");
        System.out.println(jsonData);
        for (int i = 0; i < appStats.length(); i++) {
            JSONObject explrObject = appStats.getJSONObject(i);
            if(explrObject.getJSONObject("RawStats").get("name").equals("com.makemytrip")){
                JSONObject object = explrObject.getJSONObject("RawStats");
                Gson gson = new Gson();
                BatteryRawStats batteryMetrics = gson.fromJson(object.toString(),BatteryRawStats.class);
                BatteryMetrics metrics = gson.fromJson(explrObject.toString(),BatteryMetrics.class);
                System.out.println(batteryMetrics);
                if(metrics != null){
                    CaptureBatteryTracingData.generateGrafanaDataFromObject(batteryMetrics,metrics,args);
                }
            }
        }
    }



}
