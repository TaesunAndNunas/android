package test.chart.hyes.imfine_04;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.Response;

/**
 * Created by hyes on 2015. 5. 27..
 */
public class Proxy {
    public static String SERVER_URL = "http://10.73.39.122:8080";

    ImFineService service;
    public Proxy(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(SERVER_URL)
                .build();

        service = restAdapter.create(ImFineService.class);
    }
    public void send(Event event,Callback<Response> cb){
        String response;
        service.sendData(event, cb );

//        if (response.equals("ok")) {
//            Log.i("test", "ok");
//        }else{
//            Log.i("test","fail");
//        }
    }
}
