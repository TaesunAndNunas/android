package test.chart.hyes.imfine_04;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hyes on 2015. 5. 10..
 */
public class EventAdapter extends ArrayAdapter<Item> {

    private LayoutInflater mInflater;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;


    public enum RowType {
        LIST_ITEM, HEADER_ITEM
    }

    public EventAdapter(Context context, List<Item> items) {
        super(context, 0, items);
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getViewType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        EventViewHolder holder = null;
        HeaderViewHolder holder_h = null;


        int rowType = getItemViewType(position);
        View View;
        // Log.i("test", "adapter getview..." + ((convertView==null)? "null":convertView));
//
        if (convertView == null) {


            switch (rowType) {
                case TYPE_ITEM:

                    holder = new EventViewHolder();
                    convertView = mInflater.inflate(R.layout.event_row, null);

                    holder.type = (TextView) convertView.findViewById(R.id.type_tv);
                    holder.count = (TextView) convertView.findViewById(R.id.count_tv);
                    holder.time = (TextView) convertView.findViewById(R.id.date_tv);
                    holder.detail = (TextView) convertView.findViewById(R.id.detail_tv);

                    holder.type.setText(((Event) getItem(position)).getType());
                    holder.count.setText(((Event) getItem(position)).getCount() + "");
                    holder.time.setText(((Event) getItem(position)).getTime());

                    if (((Event) getItem(position)).getDetail() == null) {
                        holder.detail.setText("");
                    } else {
                        holder.detail.setText(((Event) getItem(position)).getDetail());

                    }

                    holder.image = (ImageView) convertView.findViewById(R.id.type_iv);
                    Bitmap bScaled = null;

                    if (((Event) getItem(position)).getType().equals("medicine")) {

                        Bitmap bMap = BitmapFactory.decodeResource(convertView.getResources(), R.drawable.pill);
                        bScaled = Bitmap.createScaledBitmap(bMap, 40, 40, true);
                        holder.image.setImageBitmap(bScaled);
                        holder.View = getItem(position).getView(mInflater, convertView);

                    } else if (((Event) getItem(position)).getType().equals("sleep")) {

                        Bitmap bitmap = BitmapFactory.decodeResource(convertView.getResources(), R.drawable.sleep);
                        bScaled = Bitmap.createScaledBitmap(bitmap, 60, 60, true);
                        holder.image.setImageBitmap(bScaled);
                        holder.View = getItem(position).getView(mInflater, convertView);

                    } else if (((Event) getItem(position)).getType().equals("diaper")) {

                        Bitmap bitmap = BitmapFactory.decodeResource(convertView.getResources(), R.drawable.diaper);
                        bScaled = Bitmap.createScaledBitmap(bitmap, 60, 60, true);
                        holder.image.setImageBitmap(bScaled);
                        holder.View = getItem(position).getView(mInflater, convertView);

                    } else if (((Event) getItem(position)).getType().equals("feed")) {
                        Bitmap bitmap = BitmapFactory.decodeResource(convertView.getResources(), R.drawable.milk);
                        bScaled = Bitmap.createScaledBitmap(bitmap, 60, 60, true);
                        holder.image.setImageBitmap(bScaled);
                        holder.View = getItem(position).getView(mInflater, convertView);
                    }

                    convertView.setTag(holder);

                    break;

                case TYPE_SEPARATOR:
                    holder_h = new HeaderViewHolder();
                    convertView = mInflater.inflate(R.layout.list_header, null);

                    holder_h.header = (TextView) convertView.findViewById(R.id.header_separator);


                    String separator = ((Header) getItem(position)).getSeparator();
                    Log.i("test", "adapter arrived..." + separator);
                    String yy = separator.substring(0, 4);
                    String mm = separator.substring(4, 6);
                    String dd = separator.substring(6, 8);
                    String yymmdd = yy + "-" + mm + "-" + dd;

                    try {
                        holder_h.header.setText(yymmdd);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    holder_h.View = getItem(position).getView(mInflater, convertView);

                    //holder.View
                    convertView.setTag(holder_h);
                    break;
            }


        } else {
//            if (getItemViewType(position) == EventAdapter.RowType.LIST_ITEM.ordinal()) {
//                holder = (EventViewHolder) convertView.getTag();
//            } else if(getItemViewType(position) == EventAdapter.RowType.HEADER_ITEM.ordinal()) {
//                holder_h = (HeaderViewHolder) convertView.getTag();
//            }

            try {
                holder = (EventViewHolder) convertView.getTag();
            } catch (Exception e) {
                Log.i("test", "holder error");
                e.printStackTrace();
            }
            try {
                holder_h = (HeaderViewHolder) convertView.getTag();
            } catch (Exception e) {
                Log.i("test", "holder_h error");
                e.printStackTrace();
            }

        }


             if(getItemViewType(position) == EventAdapter.RowType.LIST_ITEM.ordinal()){
                 Log.i("test", "getItemViewType 0");
                 Bitmap bScaled = null;

                 if (((Event) getItem(position)).getType().equals("medicine")) {

                     Bitmap bMap = BitmapFactory.decodeResource(convertView.getResources(), R.drawable.pill);
                     bScaled = Bitmap.createScaledBitmap(bMap, 40, 40, true);
                     holder.image.setImageBitmap(bScaled);

                 } else if (((Event) getItem(position)).getType().equals("sleep")) {

                     Bitmap bitmap = BitmapFactory.decodeResource(convertView.getResources(), R.drawable.sleep);
                     bScaled = Bitmap.createScaledBitmap(bitmap, 60, 60, true);
                     holder.image.setImageBitmap(bScaled);

                 } else if (((Event) getItem(position)).getType().equals("diaper")) {

                     Bitmap bitmap = BitmapFactory.decodeResource(convertView.getResources(), R.drawable.diaper);
                     bScaled = Bitmap.createScaledBitmap(bitmap, 60, 60, true);
                     holder.image.setImageBitmap(bScaled);

                 } else if (((Event) getItem(position)).getType().equals("feed")) {
                     Bitmap bitmap = BitmapFactory.decodeResource(convertView.getResources(), R.drawable.milk);
                     bScaled = Bitmap.createScaledBitmap(bitmap, 60, 60, true);
                     try {
                         holder.image.setImageBitmap(bScaled);
                     } catch (Exception e) {
                         e.printStackTrace();
                     }
                 }

                 holder.type.setText(((Event) getItem(position)).getType());
                 holder.count.setText(((Event) getItem(position)).getCount() + "");
                 holder.time.setText(((Event) getItem(position)).getTime());

                 if (((Event) getItem(position)).getDetail() == null) {
                     holder.detail.setText("");
                 } else {
                     holder.detail.setText(((Event) getItem(position)).getDetail());

                 }


             }else if (getItemViewType(position) == EventAdapter.RowType.HEADER_ITEM.ordinal()) {
                 String separator = ((Header) getItem(position)).getSeparator();
                 Log.i("test", "getItemViewType 1");
                 String yy = separator.substring(0, 4);
                 String mm = separator.substring(4, 6);
                 String dd = separator.substring(6, 8);
                 String yymmdd = yy + "-" + mm + "-" + dd;

                 try {
                     holder_h.header.setText(yymmdd);
                 } catch (Exception e) {
                     e.printStackTrace();
                 }


             }


                    return convertView;

        }

        public static class EventViewHolder {
            public View View;

            TextView type, count, time, detail;
            ImageView image;

        }


        public static class HeaderViewHolder {
            public View View;

            TextView header;

        }
    }


