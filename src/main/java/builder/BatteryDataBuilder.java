package builder;
import okhttp3.*;
import org.json.JSONObject;

import java.awt.*;
import java.io.File;

public class BatteryDataBuilder{
    public static Response getResponse(String path) {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("bugreport", "bugreport",
                        RequestBody.create(mediaType, new File("/Users/mmt6054/Desktop/BatteryReport/731/A5N90073verver.txt")))
                .build();

        Request request = new Request.Builder()
                .url("http://172.16.25.95:9999")
                .post(requestBody)
                .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
