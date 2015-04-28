package hudi.android.hyes.ImFine_v03;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ProxyUP {


    private String lineEnd = "\r\n";
    private String twoHyphens = "--";
    private String boundary = "*****";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void uploadCard(CustomCardMedicine card,
                                     AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();
        params.put("type", "" + card.getTitle());

        //params.put("date", card.medicine_time);


        //params.put("time", ca

//
//        try {
//            params.put("uploadedfile", new File( filePath ));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        client.post("http://192.168.56.1:9999/memo", params, responseHandler);
    }
}