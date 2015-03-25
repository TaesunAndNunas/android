package backend.android.hyes.imfine_v01;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hyes on 2015. 3. 25..
 */
public class CustomAdapter extends ArrayAdapter<Item> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<Item> data;

    public CustomAdapter(Context context, int layoutResourceId, ArrayList<Item> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
        }
        ImageView color_index = (ImageView) row.findViewById(R.id.color_index);
        TextView time = (TextView) row.findViewById(R.id.time);
        TextView info = (TextView) row.findViewById(R.id.info);

        color_index.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
        //확인해볼 것!

        time.setText(data.get(position).getTime());
        info.setText(data.get(position).getDetails());



        return row;
    }
}
    //4가지 이모티콘, 시간입력, 기타 항목들......을 통일시켜야겠다...덜덜덜


