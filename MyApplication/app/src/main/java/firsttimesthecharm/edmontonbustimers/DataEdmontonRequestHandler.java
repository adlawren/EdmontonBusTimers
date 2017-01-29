package firsttimesthecharm.edmontonbustimers;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.impl.client.SystemDefaultCredentialsProvider;

/**
 * Created by adlawren on 28/01/17.
 */

interface IObserver<T> {
    void callback(T arg);
}

abstract class CustomObserver<T> implements IObserver<T> {
}

class DataEdmontonModel {
    public String trip_id;
    public String arrival_time_2;
    public String departure_time;
    public int stop_id;
    public int stop_sequence;
    public String stop_headsign;
    public int pickup_type;
    public int drop_off_type;
}

public class DataEdmontonRequestHandler {
    private String buildRelativeUrl(ArrayList<Route> routeList) {
        String relativeUrl = new String(
                "resource/xeux-ngrz.json?$query=SELECT%20departure_time,%20stop_headsign,%20stop_id%20WHERE%20");

        for (int i = 0; i < routeList.size(); i++) {
            if (i == 0) {
                relativeUrl += "(stop_headsign%20like%20%27" + routeList.get(i).get_busNum()
                        + "%20%25%27%20AND%20stop_id=" + routeList.get(i).get_busStop() + ")";
            }
            else {
                relativeUrl += "%20OR%20(stop_headsign%20like%20%27" + routeList.get(i).get_busNum()
                        + "%20%25%27%20AND%20stop_id=" + routeList.get(i).get_busStop() + ")";
            }
        }

        return relativeUrl;
    }

    void GetDataModels(final IObserver<ArrayList<Result>> observer, ArrayList<Route> routeList) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("$$app_token", "4C0rE9eFHXRU5cQdcR15gI1PJ");

        final ArrayList<DataEdmontonModel> dataEdmontonModels = new ArrayList<>();

        String relativeUrl = buildRelativeUrl(routeList);
        System.err.println("[EBT test] " + relativeUrl);

        DataEdmontonRestClient.get(relativeUrl, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK" (for debugging)
//                System.err.println("[EBT Tag]: Response OK");
//                System.err.println("[EBT Tag]: Response length: " + response.length);
//                System.err.println("[EBT Tag]: Response: " + new String(response));

                String json = new String(response);

                JSONArray jsonarray = null;
                try {
                    jsonarray = new JSONArray(json);
                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject jsonobject = jsonarray.getJSONObject(i);

                        Moshi moshi = new Moshi.Builder().build();
                        JsonAdapter<DataEdmontonModel> jsonAdapter = moshi.adapter(DataEdmontonModel.class);

                        DataEdmontonModel dataEdmontonModel = null;
                        try {
                            dataEdmontonModel = jsonAdapter.fromJson(jsonobject.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        System.err.println("[EBT Tag]: Next list item: " + dataEdmontonModel.departure_time);

                        dataEdmontonModels.add(dataEdmontonModel);
                        //make the result object
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                ArrayList<Result> results = new ArrayList<Result>();
                for (DataEdmontonModel model : dataEdmontonModels) {
                    results.add(new Result(String.valueOf(model.stop_id), model.stop_headsign,
                            model.departure_time));
                }

                observer.callback(results);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                System.err.println(statusCode + new String(errorResponse));
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }
}

// Use:
// import java.util.ArrayList;
// ...
//    DataEdmontonRequestHandler handler = new DataEdmontonRequestHandler();
//handler.GetDataModels(new IObserver<ArrayList<DataEdmontonModel>>() {
//@Override
//public void callback(ArrayList<DataEdmontonModel> list) {
//        System.err.println("[EBT Tag]: List size:    " + list.size());
//        }
//        });

// At end of MainActivity.onCreate for testing:
//    ArrayList<Route> testList = new ArrayList<Route>();
//testList.add(new Route(2869, 52));
//        testList.add(new Route(5050, 3));
//        DataEdmontonRequestHandler handler = new DataEdmontonRequestHandler();
//        handler.GetDataModels(new IObserver<ArrayList<Result>>() {
//@Override
//public void callback(ArrayList<Result> list) {
//        System.err.println("[EBT Tag]: List size:    " + list.size());
//        }
//        }, testList);