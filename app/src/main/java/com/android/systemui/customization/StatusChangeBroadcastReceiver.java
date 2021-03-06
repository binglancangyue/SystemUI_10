package com.android.systemui.customization;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.util.Log;

import com.android.systemui.SystemUIApplication;
import com.android.systemui.customization.bean.StatusBean;

import java.util.Calendar;

/**
 * @author Altair
 * @date :2021.03.24 下午 02:33
 * @description:
 */
public class StatusChangeBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "StatusChange";
    private WifiTool wifiTool;
    private SettingsFunctionTool settingsFunctionTool;
    private Context mContext;
    private static final String EXTRA_VOLUME_STREAM_TYPE = "android.media.EXTRA_VOLUME_STREAM_TYPE";
    private SharedPreferencesTool mSharedPreferencesTool;

    public StatusChangeBroadcastReceiver() {
        wifiTool = new WifiTool();
        settingsFunctionTool = new SettingsFunctionTool();
        mSharedPreferencesTool = new SharedPreferencesTool();
    }

    public SettingsFunctionTool getSettingsFunctionTool() {
        return this.settingsFunctionTool;
    }

    public WifiTool getWifiTool() {
        return this.wifiTool;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        this.mContext = context;
        Log.d(TAG, "onReceive:action " + action);
        if (action == null) {
            return;
        }
        switch (action) {

            case WifiManager.WIFI_STATE_CHANGED_ACTION:
                int mWifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
                if (mWifiState == WifiManager.WIFI_STATE_ENABLED) {//已打开
                    Log.d(TAG, "onReceive: wifi open");
                    sendToActivity(new StatusBean(Customer.TYPE_WIFI_STATE, true));
                }
                if (mWifiState == WifiManager.WIFI_STATE_DISABLED) {//已关闭
                    Log.d(TAG, "onReceive: wifi close");
                    sendToActivity(new StatusBean(Customer.TYPE_WIFI_STATE, false));
                }
                break;

            case WifiManager.RSSI_CHANGED_ACTION:
                int wifiLevel = wifiTool.getWifiSignalLevel();
                sendToActivity(new StatusBean(Customer.TYPE_WIFI_SIGNAL, wifiLevel));
                break;

            case WifiManager.WIFI_AP_STATE_CHANGED_ACTION:
                int isWiFiApState = intent.getIntExtra(
                        WifiManager.EXTRA_WIFI_AP_STATE, WifiManager.WIFI_AP_STATE_FAILED);
                if (isWiFiApState == WifiManager.WIFI_AP_STATE_ENABLED) {
                    Log.d(TAG, "onReceive:WIFI_AP_STATE_ENABLED ");
                    sendToActivity(new StatusBean(Customer.TYPE_WIFI_AP, true));
                }
                if (isWiFiApState == WifiManager.WIFI_AP_STATE_DISABLED) {
                    Log.d(TAG, "onReceive:WIFI_AP_STATE_DISABLED ");
                    sendToActivity(new StatusBean(Customer.TYPE_WIFI_AP, false));
                }
                if (isWiFiApState == WifiManager.WIFI_AP_STATE_FAILED) {
                    Log.d(TAG, "onReceive: WIFI_AP_STATE_FAILED");
                }
                break;

            case BluetoothAdapter.ACTION_STATE_CHANGED:
                int blueState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0);
                if (BluetoothAdapter.STATE_ON == blueState) {
                    Log.d(TAG, "onReceive: bt open");
                    sendToActivity(new StatusBean(Customer.TYPE_BT, true));
                }
                if (BluetoothAdapter.STATE_OFF == blueState) {
                    Log.d(TAG, "onReceive: bt close");
                    sendToActivity(new StatusBean(Customer.TYPE_BT, false));
                }
                break;

            case Customer.ACTION_FM_STATE_CHANGED:
                int mFmState = intent.getIntExtra("state", 0);
                if (mFmState == 0) { // off
                    sendToActivity(new StatusBean(Customer.TYPE_FM, false));
                }
                if (mFmState == 1) { // on
                    sendToActivity(new StatusBean(Customer.TYPE_FM, true));
                }
                Log.d(TAG, "onReceive: fm " + mFmState);
                break;

            case ConnectivityManager.CONNECTIVITY_ACTION:
                NetworkInfo info =
                        intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
                if (info == null) {
                    ConnectivityManager connectivityManager =
                            (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                    info = connectivityManager.getActiveNetworkInfo();
                    Log.d(TAG, "onReceive: info==null");
                }

                if (info == null) {
                    Log.d(TAG, "onReceive: info2==null");
                    return;
                }

                if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                    Log.d(TAG,
                            "onReceive:getState " + info.getState() + " isConnected " + info.isConnected());
                    if (NetworkInfo.State.CONNECTED == info.getState() && info.isConnected()) {
                        sendToActivity(new StatusBean(Customer.TYPE_MOBILE, true));
                    } else {
                        sendToActivity(new StatusBean(Customer.TYPE_MOBILE, false));
                    }
                }
                break;

            case Customer.ACTION_VOLUME_CHANGED:
                if ((intent.getIntExtra(EXTRA_VOLUME_STREAM_TYPE, -1)
                        == AudioManager.STREAM_MUSIC)) {
                    int volume = settingsFunctionTool.getCurrentVolume();
                    if (volume <= 0) {
                        sendToActivity(new StatusBean(Customer.TYPE_VOLUME, true));
                    } else {
                        sendToActivity(new StatusBean(Customer.TYPE_VOLUME, false));
                    }
                }
                break;

            case "android.location.PROVIDERS_CHANGED":
                Log.d(TAG, "onReceive: PROVIDERS_CHANGED");
                break;

            case Intent.ACTION_BATTERY_CHANGED:
                int level = intent.getIntExtra("level", 0);    ///电池剩余电量
                int scale = intent.getIntExtra("scale", 0);  ///获取电池满电量数值
                int state = intent.getIntExtra("status", BatteryManager.BATTERY_STATUS_UNKNOWN);
                ///获取电池状态
                sendToActivity(new StatusBean(Customer.TYPE_BATTERY, level, state));
                Log.d(TAG, "onReceive:level " + level + " state " + state);
                break;
            case Customer.ACTION_SHOW_SETTING_WINDOW:
                boolean isHideNavigationBar = intent.getBooleanExtra("isHideNavigationBar", false);
                SystemUIApplication.getInstance().setHideNavigationBar(isHideNavigationBar);
                NotifyMessageManager.getInstance().showSettingsWindow();
                break;
            case Customer.ACTION_UPDATE_BRIGHTNESS_BY_TIME:
                updateBrightnessByTime();
                break;
            case Customer.ACTION_UPDATE_WEATHER:
                String weather = intent.getStringExtra("weatherInfo");
                NotifyMessageManager.getInstance().updateWeather(weather);
                break;
            case Intent.ACTION_SCREEN_OFF:
                Log.d(TAG, "onReceive:ACTION_SCREEN_OFF ");
                break;
            case Customer.ACTION_DISMISS_SETTINGS_DIALOG:
                NotifyMessageManager.getInstance().openOrClose(101, true);
                break;
        }

    }

    private void updateBrightnessByTime() {
        if (!mSharedPreferencesTool.getAutoBrightness()) {
            return;
        }
        Calendar calendar = Calendar.getInstance();
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        if (hours >= 6 && hours < 19) {
            settingsFunctionTool.progressChangeToBrightness(100);
        } else {
            settingsFunctionTool.progressChangeToBrightness(40);
        }
    }

    private void sendToActivity(StatusBean statusBean) {
        // setting Window update
        int type = statusBean.getType();
        boolean isOpen = statusBean.isOpen();
        if (type != 5 || type != 8 || type != 9) {
            NotifyMessageManager.getInstance().openOrClose(type, isOpen);
        }
    }

}
