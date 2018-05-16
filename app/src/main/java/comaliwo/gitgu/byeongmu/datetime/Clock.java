package comaliwo.gitgu.byeongmu.datetime;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.SystemClock;

/**
 * Created by aliwo on 2017-07-17.
 */

//하루가 지날 때 마다 알려줍니다.

public class Clock
{
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;
    private Context mContext;

    public Clock(Context context)
    {
        alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        mContext = context;
    }

    public void setAlarm() //Wake up the device to fire a one-time (non-repeating) alarm in one minute:
    {
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_HALF_HOUR,
                AlarmManager.INTERVAL_HALF_HOUR, alarmIntent);
    }

}

