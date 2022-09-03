package com.example.dailycost;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import db.AlarmReceiver;
import db.DBManager;
import db.NotificationHelper;

import java.util.Calendar;
import java.util.TimeZone;

public class SettingActivity extends AppCompatActivity {

    TextView setting_tv_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setting_tv_user = findViewById(R.id.setting_tv_user);
        Intent intent = getIntent();
        int flag = intent.getExtras().getInt("flag");
        if (flag == 1){
            setting_tv_user.setText(intent.getExtras().getString("name")+"");
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_iv_back:
                finish();
                break;
            case R.id.setting_bt_user:
                user();
                break;
            case R.id.setting_bt_time:
                time();
                break;
            case R.id.setting_bt_cancelTime:
                cancelTime();
                break;
            case R.id.setting_tv_clear:
                showDeleteDialog();
                break;
        }
    }

    private void user() {
        Log.d("TAG", "user: 11111");
        Intent intent = new Intent();
        intent.setClass(SettingActivity.this,RegisterActivity.class);
        startActivityForResult(intent,1);
        finish();
    }

    private void time() {
        Calendar calendar = Calendar.getInstance();
        int hourOfDay  = calendar.get(Calendar.HOUR_OF_DAY);    //得到小时
        int minute  = calendar.get(Calendar.MINUTE);            //得到分钟
        new TimePickerDialog(SettingActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //  这个方法是得到选择后的 小时、分钟，分别对应着三个参数 —   hourOfDay、minute
                String time = hourOfDay+":"+minute;
                setTime(hourOfDay,minute);
                Toast.makeText(SettingActivity.this,"你设置了每天"+time+"的提醒",Toast.LENGTH_SHORT).show();
            }
        }, hourOfDay, minute, true).show();
    }

    private void setTime(int hourOfDay, int minute) {
//        得到日历实例，主要是为了下面的获取时间
        Calendar mCalendar  = Calendar.getInstance();
        mCalendar.setTimeInMillis(System.currentTimeMillis());

//        获取当前毫秒值
        long systemTime = System.currentTimeMillis();

//        是设置日历的时间，主要是让日历的年月日和当前同步
        mCalendar.setTimeInMillis(System.currentTimeMillis());
//         这里时区需要设置一下，不然可能个别手机会有8个小时的时间差
        mCalendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
//         设置在几点提醒
        mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        mCalendar.set(Calendar.MINUTE, minute);
//        设置在几分提醒
        mCalendar.set(Calendar.SECOND, 0);
        mCalendar.set(Calendar.MILLISECOND, 0);

//         获取上面设置的毫秒值
        long selectTime = mCalendar.getTimeInMillis();
//         如果当前时间大于设置的时间，那么就从第二天的设定时间开始
        if(systemTime > selectTime) {
            mCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        Intent intent = new Intent(SettingActivity.this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(SettingActivity.this, 0, intent, 0);
        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);


/*        重复提醒
    第一个参数是警报类型；下面有介绍
    第二个参数网上说法不一，很多都是说的是延迟多少毫秒执行这个闹钟，但是我用的刷了MIUI的三星手机的实际效果是与单次提醒的参数一样，即设置的13点25分的时间点毫秒值
    第三个参数是重复周期，也就是下次提醒的间隔 毫秒值 我这里是一天后提醒

    AlarmManager.RTC，硬件闹钟，不唤醒手机（也可能是其它设备）休眠；当手机休眠时不发射闹钟。
    AlarmManager.RTC_WAKEUP，硬件闹钟，当闹钟发躰时唤醒手机休眠；
    AlarmManager.ELAPSED_REALTIME，真实时间流逝闹钟，不唤醒手机休眠；当手机休眠时不发射闹钟。
    AlarmManager.ELAPSED_REALTIME_WAKEUP，真实时间流逝闹钟，当闹钟发躰时唤醒手机休眠；
    RTC闹钟和ELAPSED_REALTIME最大的差别就是前者可以通过修改手机时间触发闹钟事件，后者要通过真实时间的流逝，即使在休眠状态，时间也会被计算。
 */
        am.setRepeating(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), (1000 * 60 * 60 * 24), pi);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void cancelTime() {
        Intent intent = new Intent(SettingActivity.this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(SettingActivity.this, 0,
                intent, 0);
        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        //取消警报
        am.cancel(pi);
        Toast.makeText(SettingActivity.this, "关闭了提醒", Toast.LENGTH_SHORT).show();
    }

    private void showDeleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("删除提示")
                .setMessage("您确定要删除所有记录么？\n注意：删除后无法恢复，请慎重选择！")
                .setPositiveButton("取消",null)
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBManager.deleteAllAccount();
                        Toast.makeText(SettingActivity.this,"删除成功！",Toast.LENGTH_SHORT).show();
                    }
                });
        builder.create().show();
    }
}
