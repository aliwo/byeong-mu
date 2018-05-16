package comaliwo.gitgu.byeongmu.http;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import comaliwo.gitgu.byeongmu.Message.MessageManager;
import cz.msebera.android.httpclient.Header;

/**
 * Created by aliwo on 2017-03-26.
 */

public class HttpModule
{
    private RestClient client;
    private Context mContext;
    private RequestParams params;
    private NotificationResponseHandler notificationResponseHandler;

    public HttpModule(RestClient client, Context context)
    {
        this.client = client;
        mContext = context;
        notificationResponseHandler = new NotificationResponseHandler(mContext);
    }

    public void checkNotification()
    {
        //TODO: 현재 사용자가 어떤 지방병무청을 구독하고 있는지 찾아야 함.
        client.get("/new_notice", null, notificationResponseHandler);
    }


}

class NotificationResponseHandler extends JsonHttpResponseHandler
{
    private Context mContext;

    public NotificationResponseHandler(Context context)
    {
        mContext = context;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response)
    {
        super.onSuccess(statusCode, headers, response);
        Log.d("onSuccess", "JSON received");
        try {
            processMessage(response);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, String responseString)  //false라는 단순 문자열이 오면 이게 호출되나?
    {
        super.onSuccess(statusCode, headers, responseString);
        Log.d("onSuccess", "String received");

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse)
    {
        super.onFailure(statusCode, headers, throwable, errorResponse);
        Log.d("onFailure", "failed");

    }

    private void processMessage(JSONObject response) throws JSONException
    {
        String id_num;
        String title;

        id_num=response.getString("id_num");
        title=response.getString("title");
        //TODO: db에 쿼리해서 같은 id_num과 title을 가진 글이 있는지 찾는다.


        //TODO: 만약 db에서 못찾았다면 message를 만든다.
        MessageManager messageMgr = new MessageManager();
        messageMgr.flyMessage(id_num, title);
        //TODO: 그리고 db에 저장한다.

    }


}