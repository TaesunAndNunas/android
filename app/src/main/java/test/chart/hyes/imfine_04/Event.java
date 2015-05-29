package test.chart.hyes.imfine_04;

import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by hyes on 2015. 5. 10..
 */
public class Event implements Item{

    private String type;
    private int count;
    private String date;
    private String time;
    private String detail;

    public Event(String type, int count, String time) {
        this.type = type;
        this.count = count;
        this.time = time;
        this.detail = "";

    }

    public Event(String type, int count, String time, String date, String detail) {
        this.type = type;
        this.count = count;
        this.time = time;
        this.detail = detail;
        this.date = date;
    }

    public String getDetail() {
        return detail;
    }

    public String getType() {
        return type;
    }

    public int getCount() {
        return count;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    @Override
    public int getViewType() {
        return EventAdapter.RowType.LIST_ITEM.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView) {
        View view;
        if (convertView == null) {
            view = (View) inflater.inflate(R.layout.event_row, null);
            // Do some initialization


        } else {
            view = convertView;
        }

//        TextView text1 = (TextView) view.findViewById(R.id.type_tv);
//        TextView text2 = (TextView) view.findViewById(R.id.count_tv);
//        TextView text3 = (TextView) view.findViewById(R.id.date_tv);
//        TextView text4 = (TextView) view.findViewById(R.id.detail_tv);
//        text1.setText(type);
//        text2.setText(count+"");
//        text3.setText(time);
//        text4.setText(detail);
//
//        ImageView imageView = (ImageView) view.findViewById(R.id.type_iv);
//
//
//        if(type == "medicine"){
//            Bitmap bitmap = BitmapFactory.decodeResource(convertView.getResources(), R.drawable.pill);
//            Bitmap bScaled = Bitmap.createScaledBitmap(bitmap, 60, 60, true);
//            imageView.setImageBitmap(bScaled);
//        }else if(type == "sleep"){
//            Bitmap bitmap = BitmapFactory.decodeResource(convertView.getResources(), R.drawable.sleep);
//            Bitmap bScaled = Bitmap.createScaledBitmap(bitmap, 60, 60, true);
//            imageView.setImageBitmap(bScaled);
//        }
//        else if(type == "diaper"){
//            Bitmap bitmap = BitmapFactory.decodeResource(convertView.getResources(), R.drawable.diaper);
//            Bitmap bScaled = Bitmap.createScaledBitmap(bitmap, 60, 60, true);
//            imageView.setImageBitmap(bScaled);
//        }
//        else if(type == "feed"){
//            Bitmap bitmap = BitmapFactory.decodeResource(convertView.getResources(), R.drawable.milk);
//            Bitmap bScaled = Bitmap.createScaledBitmap(bitmap, 60, 60, true);
//            imageView.setImageBitmap(bScaled);
//        }


        return view;
    }
}
