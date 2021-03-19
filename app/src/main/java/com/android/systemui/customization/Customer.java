package com.android.systemui.customization;

import com.android.systemui.R;

/**
 * @author: Administrator
 * @date: 2021/3/5
 * @description:
 */
public class Customer {
    public static final int[] HOME_ICON = {R.drawable.icon_home_7in};
    public static final int[] BACK_ICON = {R.drawable.icon_back_7in};
    public static final int[] VOICE_ICON = {R.drawable.icon_voice_7in};
    public static final int[] CAMERA_ICON = {R.drawable.icon_camera_7in};
    public static final int[] SETTINGS_ICON = {R.drawable.icon_setttings_7in};
    public static final int NAVIGATION_BAR_ICON_TYPE = 0;

    public static final int HANDLE_POP_WIFI_BTN = 1;
    public static final int HANDLE_POP_UPDATE_BTN = 2;
    public static final int HANDLE_POP_UPDATE_SEEK_BAR = 3;
    public static final int HANDLE_POP_UPDATE_BRIGHTNESS = 4;
    public static final int HANDLE_POP_UPDATE_DATA = 5;
    public static final int HANDLE_POP_UPDATE_BTN_TXZ = 6;
    public static final int HANDLE_POP_UPDATE_BTN_BY_LISTENER = 7;
//    public static final int HANDLE_POP_UPDATE_MOBILE_NETWORK = 8;

    public static final int RADIO_BTN_TYPE_SCREEN_OFF_TIMEOUT = 0;
    public static final int RADIO_BTN_TYPE_ADAS = 1;
    public static final int RADIO_BTN_TYPE_COLLISION = 2;
    public static final int RADIO_BTN_TYPE_RECORD_TIME = 3;
    public static final int RADIO_BTN_TYPE_SCREEN_CONTROL = 4;


    public static final int TYPE_WIFI_STATE = 1;
    public static final int TYPE_BT = 2;
    public static final int TYPE_FM = 3;
    public static final int TYPE_MOBILE = 4;
    public static final int TYPE_SDCARD = 5;
    public static final int TYPE_VOLUME = 6;
    public static final int TYPE_WIFI_AP = 7;
    public static final int TYPE_BATTERY = 8;
    public static final int TYPE_WIFI_SIGNAL = 9;
    public static final int TYPE_MOBILE_DATA_STATE = 10;
    public static final int TYPE_GPS_SIGNAL = 11;

    public static final String ACTION_VOLUME_CHANGED = "android.media.VOLUME_CHANGED_ACTION";
    public static final String ACTION_SHOW_SETTING_WINDOW = "com.android.systemui.show_setting_window";
    public static final String ACTION_SET_DVR_RECORD_TIME = "com.android.systemui.SET_DVR_RECORD_TIME";
    public static final String ACTION_SET_G_SENSOR_LEVEL = "com.android.systemui.SET_G_SENSOR_LEVEL";
    public static final String ACTION_SET_ADAS_LEVEL = "com.android.systemui.SET_ADAS_LEVEL";
    public static final String ACTION_FORMAT_SD_CARD = "com.android.systemui.FORMAT_SD_CARD";
    public static final String ACTION_OPEN_DVR_CAMERA = "com.android.systemui.OPEN_CAMERA";
    public static final String ACTION_UPDATE_BRIGHTNESS_BY_TIME = "com.android.systemui.UPDATE_BRIGHTNESS_BY_TIME";
    public static final String ACTION_DVR_STATE = "com.bx.carDVR.action_dvr_state";
    public static final String ACTION_SHOW_STOP_RECORDING_DIALOG = "com.bx.carDVR.action.show_dialog";
    public final static String ACTION_GET_WEATHER = "com.bixin.speechrecognitiontool.action_get_weather";
    public final static String ACTION_UPDATE_WEATHER = "com.bixin.speechrecognitiontool.action_update_weather";
    public static final String ACTION_GO_TO_SLEEP = "com.android.systemui.action_go_to_sleep";
    public static final String ACTION_DISMISS_SETTINGS_DIALOG = "com.android.deskclock.action.dismiss_settings_dialog";
    public static final String ACTION_FM_STATE_CHANGED = "android.car.action.FM_STATE_CHANGED";
    public final static String SP_NAME = "settings";

    /***特殊需求***/
    public static final boolean IS_T507 = true;
    public static final boolean ENGLISH_VERSION = false;//英文版本 不需要语音识别
    public static final boolean NOT_MOBILE_NETWORK = false;//7in 英文版 无移动网络
    public static final boolean NOT_MOBILE_WIFI = false;//7in 英文版 无wifi
    public static final boolean NOT_DVR = false;//7in 英文版 无DVR

}
