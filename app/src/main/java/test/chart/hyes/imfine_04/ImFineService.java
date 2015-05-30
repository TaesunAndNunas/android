package test.chart.hyes.imfine_04;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Part;

/**
 * Created by hyes on 2015. 5. 27..
 */
public interface ImFineService {

    @POST("/join")
    void join(
            @Part("profile") UserProfile profile,
            Callback<Response> cb
    );


    @Multipart
    @POST("/detail")
    void sendData(
            @Part("id") int id,
            @Part("name") String name,
            @Part("event") Event event,
            Callback<Response> cb
    );

    @Multipart
    @PUT("/detail")
    void  updateDetail(
            @Part("id") int id,
            @Part("name") String name,
            @Part("detail") String detail,
            Callback<Response> cb
    );

}
