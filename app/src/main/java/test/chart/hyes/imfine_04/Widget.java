package test.chart.hyes.imfine_04;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hyes on 2015. 5. 13..
 */
public class Widget extends AppWidgetProvider {

    public static final String ACTION_A ="com.record.widget.A";
    public static final String ACTION_B ="com.record.widget.B";
    public static final String ACTION_C ="com.record.widget.C";
    public static final String ACTION_D ="com.record.widget.D";

    private static final String TAG = "ImFineWidget";
    private Context context;
    private ComponentName recordWidget;
    private RemoteViews views = null;
    private String time_changed, m_t, s_t, f_t, d_t;

    private int m,s,f,d;


    @Override
    public void onEnabled(Context context) {
        Log.i("test", "======================= onEnabled() =======================");
        super.onEnabled(context);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);


//        final int N = appWidgetIds.length;
//
//        for (int i=0; i<N; i++) {
//            int appWidgetId = appWidgetIds[i];
       // RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);

        recordWidget = new ComponentName(context, Widget.class);
        views = new RemoteViews(context.getPackageName(), R.layout.widget);

        //Medicine Intent
        Intent medicineIntent = new Intent(ACTION_A);
        PendingIntent medicinePendingIntent = PendingIntent.getBroadcast(context, 0, medicineIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.widget_medicine, medicinePendingIntent);

        //Sleep Intent
        Intent sleepIntent = new Intent(ACTION_B);
        PendingIntent sleepPendingIntent = PendingIntent.getBroadcast(context, 0, sleepIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.widget_sleep, sleepPendingIntent);

        //Diaper Intent
        Intent diaperIntent = new Intent(ACTION_C);
        PendingIntent diaperPendingIntent = PendingIntent.getBroadcast(context, 0, diaperIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.widget_diaper, diaperPendingIntent);


        //Feed Intent
        Intent feedIntent = new Intent(ACTION_D);
        PendingIntent feedPendingIntent = diaperPendingIntent.getBroadcast(context, 0, feedIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.widget_milk, feedPendingIntent);

        appWidgetManager.updateAppWidget(recordWidget, views);
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);

        int minWidth = newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH);
        int maxWidth = newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_WIDTH);
        int minHeight = newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT);
        int maxHeight = newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_HEIGHT);
        RemoteViews rv = null;

        //크기 변경에 따른 레이아웃 변경코드
//        if(minWidth == 152 && maxWidth == 196 && minHeight == 58 && maxHeight == 84){
//            rv = new RemoteViews(context.getPackageName(), R.layout.widget_layout3x3);
//        } else {
//            rv = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
//        }
//        appWidgetManager.updateAppWidget(appWidgetId, rv);
    }
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        Log.i(TAG, "======================= onDeleted() =======================");
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onDisabled(Context context) {
        Log.i(TAG, "======================= onDisabled() =======================");
        super.onDisabled(context);
    }

    /**
     * UI 설정 이벤트 설정
     */
    public void initUI(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.i(TAG, "======================= initUI() =======================");
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);

//            Intent eventIntent              = new Intent(Const.ACTION_EVENT);
//            Intent activityIntent           = new Intent(Const.ACTION_CALL_ACTIVITY);
//            Intent dialogIntent             = new Intent(Const.ACTION_DIALOG);
//
//            PendingIntent eventPIntent          = PendingIntent.getBroadcast(context, 0, eventIntent        , 0);
//            PendingIntent activityPIntent       = PendingIntent.getBroadcast(context, 0, activityIntent     , 0);
//            PendingIntent dialogPIntent         = PendingIntent.getBroadcast(context, 0, dialogIntent       , 0);
//
//            views.setOnClickPendingIntent(R.id.btn_event            , eventPIntent);
//            views.setOnClickPendingIntent(R.id.btn_call_activity    , activityPIntent);
//            views.setOnClickPendingIntent(R.id.btn_set_alram        , dialogPIntent);

        for(int appWidgetId : appWidgetIds) {
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }


    /**
     * Receiver 수신
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget);
        String action = intent.getAction();
        Log.d(TAG, "onReceive() action = " + action);
        Dao dao = new Dao(context);
        m = dao.getDailyCount("medicine");
        s = dao.getDailyCount("sleep");
        f = dao.getDailyCount("feed");
        d = dao.getDailyCount("diaper");
        Log.i("test", "from server: " + m+","+ s + ",");

        m_t = dao.getRecentTime("medicine");
        s_t = dao.getRecentTime("sleep");
        d_t = dao.getRecentTime("diaper");
        f_t = dao.getRecentTime("feed");


        rv.setTextViewText(R.id.medicine_count, m+"");
        rv.setTextViewText(R.id.medicine_time, m_t);
        rv.setTextViewText(R.id.sleep_count, s + "");
        rv.setTextViewText(R.id.sleep_time, s_t);
        rv.setTextViewText(R.id.diaper_count, d + "");
        rv.setTextViewText(R.id.diaper_time, d_t);
        rv.setTextViewText(R.id.milk_count, f + "");
        rv.setTextViewText(R.id.milk_time, f_t);


        if(action.equals(ACTION_A)){

            try{
                m_t = timestamp();
                m += 1;
                dao.eventInsert("medicine", m, m_t);

                rv.setTextViewText(R.id.medicine_count, m+"");
                rv.setTextViewText(R.id.medicine_time, m_t);
            }catch (Exception e) {

            }

        }else if(action.equals(ACTION_B)){
            Log.e("widget","bbb");
            try{
                s_t = timestamp();
                s += 1;

                dao.eventInsert("sleep", s, s_t);
                rv.setTextViewText(R.id.sleep_count, s + "");
                rv.setTextViewText(R.id.sleep_time, s_t);
            }catch (Exception e) {

            }

        }else if(action.equals(ACTION_C)){

            try{
                d_t = timestamp();
                d += 1;

                dao.eventInsert("diaper", d, d_t);
                rv.setTextViewText(R.id.diaper_count, d + "");
                rv.setTextViewText(R.id.diaper_time, d_t);
            }catch (Exception e) {

            }
        }else if(action.equals(ACTION_D)){

            try{
                f_t = timestamp();
                f += 1;

                dao.eventInsert("feed", f, f_t);
                rv.setTextViewText(R.id.milk_count, f + "");
                rv.setTextViewText(R.id.milk_time, f_t);
            }catch (Exception e) {

            }
        }

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName cpName = new ComponentName(context, Widget.class);
        appWidgetManager.updateAppWidget(cpName, rv);


//        // Default Recevier
//        if(AppWidgetManager.ACTION_APPWIDGET_ENABLED.equals(action)){
//
//        }
//        else if(AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(action)){
//            AppWidgetManager manager = AppWidgetManager.getInstance(context);
//            initUI(context, manager, manager.getAppWidgetIds(new ComponentName(context, getClass())));
//        }
//        else if(AppWidgetManager.ACTION_APPWIDGET_DELETED.equals(action)){
//
//        }
//        else if(AppWidgetManager.ACTION_APPWIDGET_DISABLED.equals(action)){
//
//        }

        // Custom Recevier
//        else if(Const.ACTION_EVENT.equals(action)){
//            Toast.makeText(context, "Receiver 수신 완료!!.", Toast.LENGTH_SHORT).show();
//        }
//        else if(Const.ACTION_CALL_ACTIVITY.equals(action)){
//            callActivity(context);
//        }
//        else if(Const.ACTION_DIALOG.equals(action)){
//            createDialog(context);
//        }
    }




    private String timestamp(){

        long currentTimeMillis = System.currentTimeMillis();
        Date date = new Date(currentTimeMillis);
        SimpleDateFormat curTime = new SimpleDateFormat("a hh:mm");
        return time_changed = curTime.format(date);

    }

}
