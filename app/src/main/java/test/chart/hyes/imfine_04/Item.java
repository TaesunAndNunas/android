package test.chart.hyes.imfine_04;

import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by hyes on 2015. 5. 12..
 */
public interface Item {

    public int getViewType();

    public View getView(LayoutInflater inflater, View convertView);

}
