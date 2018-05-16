package comaliwo.gitgu.byeongmu.datetime;

/**
 * Created by aliwo on 2017-07-24.
 */

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;
import java.util.Random;

import comaliwo.gitgu.byeongmu.http.HttpModule;
import comaliwo.gitgu.byeongmu.http.RestClient;

/**
 * Created by aliwo on 2017-07-24.
 */

public class AlarmFactory
{
    private Context context;
    private RestClient client;
    private BroadCaster broadCaster;

    public AlarmFactory(Context context)
    {
        this.context = context;
        broadCaster = new BroadCaster(context);
    }

    public void Alarm(int hour, int minute)
    {
        android.app.AlarmManager am = (android.app.AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, BroadCaster.class);
        //인텐트 생성
        //알람이 발생했을 경우, BroadcastD클래스에게 알려줍니다.

        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), hour, minute, new Random().nextInt(59));

        am.set(android.app.AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
    }
}

class BroadCaster extends BroadcastReceiver
{
    private RestClient client;
    private HttpModule module;
    public BroadCaster(Context context)
    {
        client = new RestClient(context);
        module = new HttpModule(client, context);
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
           module.checkNotification();
    }
}

