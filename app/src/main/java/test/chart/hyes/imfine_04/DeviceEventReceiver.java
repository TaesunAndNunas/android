//package test.chart.hyes.imfine_04;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.util.Log;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * Created by hyes on 2015. 5. 13..
// */
//public class DeviceEventReceiver extends BroadcastReceiver {
//
//    private String date_changed;
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//
//            final String action = intent.getAction();
//
//            if (Intent.ACTION_DATE_CHANGED.equals(action)) {
//
//                Log.i("test", "-------------date changed----------------");
//
//                long currentTimeMillis = System.currentTimeMillis();
//                Date date = new Date(currentTimeMillis);
//                SimpleDateFormat curDate = new SimpleDateFormat("yy년MM월dd일");
//
//                date_changed = curDate.format(date);
//
//                Header header = new Header(date_changed);
//
//                MainActivity main = new MainActivity();
//                main.addHeaderItems(header);
//
//
//            }
//    }
//}
//
