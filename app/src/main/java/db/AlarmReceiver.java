package db;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //当系统到我们设定的时间点的时候会发送广播，执行这里
        Log.d("111111111111111111111", "onReceive: 11111111111111111");
        NotificationHelper notificationHelper = new NotificationHelper();
        notificationHelper.show(context);
        Log.d("222222222222222222222", "onReceive: 2222222222222");
    }
}
