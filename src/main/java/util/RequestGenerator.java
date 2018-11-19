package util;

import okhttp3.*;

import java.io.IOException;

public class RequestGenerator {


    private static final String CONN = "CreateDBConnection";
    private static final String url = "http://172.16.44.24:8080/monitor/v1/metrics/app";
    static OkHttpClient client;
    //making api request to open connection with the qa DB and inserting data into androidmemoryusage table in database
    public static void createAPIRequest(Object obj) {
        if(client == null){
             client = new OkHttpClient();
        }
        if(obj != null){
            String requestString = GsonUtil.getInstance().serializeToJson(obj);
            System.out.println(requestString);
            if(requestString != null){
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),requestString);

                Request.Builder builder = new Request.Builder();
                builder.addHeader("Content-Type", "application/json")
                        .addHeader("Accept", "application/json")
                        .post(requestBody)
                        .url(url);
                final Request request = builder.build();
                try {
                    Response response = client.newCall(request).execute();
                    System.out.println("Response"+response.code());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
