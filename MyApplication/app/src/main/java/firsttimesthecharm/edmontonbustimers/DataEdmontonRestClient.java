package firsttimesthecharm.edmontonbustimers;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by adlawren on 28/01/17.
 */

public class DataEdmontonRestClient {
    private static final String BASE_URL = "https://data.edmonton.ca/";

    private static AsyncHttpClient client = new AsyncHttpClient(true, 80, 443);

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}

// Sample use:

//import com.loopj.android.http.*;
//import cz.msebera.android.httpclient.Header;

//    RequestParams requestParams = new RequestParams();
//requestParams.put("$limit","5000");
//        requestParams.put("$$app_token", "4C0rE9eFHXRU5cQdcR15gI1PJ");
//
//        // RestClient.get("get", new RequestParams(), new AsyncHttpResponseHandler() {
//        DataEdmontonRestClient.get("resource/xeux-ngrz.json", requestParams, new AsyncHttpResponseHandler() {
//@Override
//public void onStart() {
//        // called before request is started
//        }
//
//@Override
//public void onSuccess(int statusCode, Header[] headers, byte[] response) {
//        // called when response HTTP status is "200 OK"
//        System.err.println("[EBT Tag]: Response OK");
//        System.err.println("[EBT Tag]: Response length: " + response.length);
//        System.err.println("[EBT Tag]: Response: " + new String(response));
//        }
//
//@Override
//public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
//        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
//        }
//
//@Override
//public void onRetry(int retryNo) {
//        // called when request is retried
//        }
//        });