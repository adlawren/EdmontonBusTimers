package firsttimesthecharm.edmontonbustimers;

import android.os.AsyncTask;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by robin on 28/01/17.
 */

public class QueryTask extends AsyncTask<Object, Integer, ArrayList<Result>> {

    private ListView lv;
    public QueryTask(ListView _lv) {
        lv = _lv;
    }

    @Override
    protected ArrayList<Result> doInBackground(Object... obj) {
        return new ArrayList<Result>();
    }
}
