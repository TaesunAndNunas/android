package test.chart.hyes.imfine_04;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;

/**
 * Created by hyes on 2015. 5. 27..
 */
public interface ImFineService {
    @Multipart
    @POST("/list")
    void sendData(
            @Part("a") Event event,
            Callback<Response> cb
    );

}
