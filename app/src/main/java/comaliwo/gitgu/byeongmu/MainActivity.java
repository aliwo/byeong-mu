package comaliwo.gitgu.byeongmu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import comaliwo.gitgu.byeongmu.datetime.AlarmFactory;
import comaliwo.gitgu.byeongmu.http.HttpModule;
import comaliwo.gitgu.byeongmu.http.RestClient;

public class MainActivity extends AppCompatActivity
{
    HttpModule module;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new AlarmFactory(MainActivity.this).Alarm(10, 20);
//        RestClient client = new RestClient(MainActivity.this);
//        this.module = new HttpModule(client, client.getContext());
    }





}
