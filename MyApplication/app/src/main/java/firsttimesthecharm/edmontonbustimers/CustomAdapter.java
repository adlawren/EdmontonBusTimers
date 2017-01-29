package firsttimesthecharm.edmontonbustimers;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by robin on 29/01/17.
 */

public class CustomAdapter extends ArrayAdapter<Result> {


    public CustomAdapter(Context context, ArrayList<Result> results) {
        super(context, 0, results);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Result result = getItem(position);

        if (convertView == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            convertView = vi.inflate(R.layout.result_row, parent, false);
        }

        TextView l = (TextView)convertView.findViewById(R.id.textView_leftTV);
        TextView m = (TextView)convertView.findViewById(R.id.textView_midTV);
        TextView r = (TextView)convertView.findViewById(R.id.textView_rightTV);

        SimpleDateFormat hhmmss = new SimpleDateFormat("HH:mm:ss");

        l.setText(result.get_BusNumRouteName());
        m.setText(result.get_BusStop());
        r.setText(hhmmss.format(result.get_time()));

        return convertView;
    }

}