package test.chart.hyes.imfine_04;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.Response;

/**
 * Created by hyes on 2015. 5. 27..
 */
public class Proxy {
   // public static String SERVER_URL = "http://10.73.39.122:8080";
    public static String SERVER_URL = "http://125.209.200.26";


    ImFineService service;
    public Proxy(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(SERVER_URL)
                .build();

        service = restAdapter.create(ImFineService.class);
    }

    public void sendProfile(UserProfile profile, Callback<Response> cb) {

        service.join(profile, cb);
//                profile.getName(),
//                profile.getId(),
//                profile.getPwd1(),
//                profile.getBirth(),
//                profile.getGender(),
//               // profile.getImage_url(),
//                cb);

    }

    public void send(int id, String name, Event event, Callback<Response> cb) {
        service.sendData(id, name, event, cb);
    }

    public void updateDetail(int id, String name, String string, Callback<Response> cb) {
        service.updateDetail(id, name, string, cb);
    }

}
