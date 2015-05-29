package test.chart.hyes.imfine_04;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by hyes on 2015. 5. 12..
 */
public class Header implements Item {


    private String separator;

    public Header(String separator) {
        this.separator = separator;
    }

    public String getSeparator() {
        return separator;
    }

    @Override
    public int getViewType() {
        return EventAdapter.RowType.HEADER_ITEM.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView) {
        View view;
        if (convertView == null) {
            view = (View) inflater.inflate(R.layout.list_header, null);

        } else {
            view = convertView;
        }

        TextView text = (TextView) view.findViewById(R.id.header_separator);

        String yy = separator.substring(0,4);
        String mm = separator.substring(4,6);
        String dd = separator.substring(6,8);
        String yymmdd = yy + "-" + mm + "-" + dd;

        try {
            text.setText(yymmdd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }
}
