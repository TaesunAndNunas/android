package test.chart.hyes.imfine_04;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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


        ViewHolder holder = null;
        TextView text1 = null;
        TextView text2 = null;
        TextView text3 = null;
        TextView text4 = null;


        int rowType = getItemViewType(position);
        View View;
        // Log.i("test", "adapter getview..." + ((convertView==null)? "null":convertView));
//
//        if (convertView == null) {
        holder = new ViewHolder();

        switch (rowType) {
            case TYPE_ITEM:
                convertView = mInflater.inflate(R.layout.event_row, null);
                ImageView imageView = (ImageView) convertView.findViewById(R.id.type_iv);
                Bitmap bScaled = null;

                if (((Event) getItem(position)).getType().equals("medicine")) {

                    Bitmap bMap = BitmapFactory.decodeResource(convertView.getResources(), R.drawable.pill);
                    bScaled = Bitmap.createScaledBitmap(bMap, 40, 40, true);
                    imageView.setImageBitmap(bScaled);
                    holder.View = getItem(position).getView(mInflater, convertView);

                } else if (((Event) getItem(position)).getType().equals("sleep")) {

                    Bitmap bitmap = BitmapFactory.decodeResource(convertView.getResources(), R.drawable.sleep);
                    bScaled = Bitmap.createScaledBitmap(bitmap, 60, 60, true);
                    imageView.setImageBitmap(bScaled);
                    holder.View = getItem(position).getView(mInflater, convertView);

                } else if (((Event) getItem(position)).getType().equals("diaper")) {

                    Bitmap bitmap = BitmapFactory.decodeResource(convertView.getResources(), R.drawable.diaper);
                    bScaled = Bitmap.createScaledBitmap(bitmap, 60, 60, true);
                    imageView.setImageBitmap(bScaled);
                    holder.View = getItem(position).getView(mInflater, convertView);

                } else if (((Event) getItem(position)).getType().equals("feed")) {
                    Bitmap bitmap = BitmapFactory.decodeResource(convertView.getResources(), R.drawable.milk);
                    bScaled = Bitmap.createScaledBitmap(bitmap, 60, 60, true);
                    imageView.setImageBitmap(bScaled);
                    holder.View = getItem(position).getView(mInflater, convertView);
                }

                text1 = (TextView) convertView.findViewById(R.id.type_tv);
                text2 = (TextView) convertView.findViewById(R.id.count_tv);
                text3 = (TextView) convertView.findViewById(R.id.date_tv);
                text4 = (TextView) convertView.findViewById(R.id.detail_tv);

                text1.setText(((Event) getItem(position)).getType());
                text2.setText(((Event) getItem(position)).getCount() + "");
                text3.setText(((Event) getItem(position)).getTime());

                if (((Event) getItem(position)).getDetail() == null) {
                    text4.setText("");
                } else {
                    text4.setText(((Event) getItem(position)).getDetail());

                }


                break;

            case TYPE_SEPARATOR:
                convertView = mInflater.inflate(R.layout.list_header, null);

                TextView text = (TextView) convertView.findViewById(R.id.header_separator);


                String separator = ((Header) getItem(position)).getSeparator();

                String yy = separator.substring(0, 4);
                String mm = separator.substring(4, 6);
                String dd = separator.substring(6, 8);
                String yymmdd = yy + "-" + mm + "-" + dd;

                try {
                    text.setText(yymmdd);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                holder.View = getItem(position).getView(mInflater, convertView);
                break;
        }

//        convertView.setTag(holder);
//    }

//            else
//            {
//                holder = (ViewHolder) convertView.getTag();
//            }
//
//             if(getViewTypeCount() == 0){
//                 Item item = getItem(position);
//
//
//             }



        return convertView;

    }

    public static class ViewHolder {
        public View View;
    }
}


