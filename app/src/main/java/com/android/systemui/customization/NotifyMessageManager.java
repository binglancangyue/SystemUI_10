package com.android.systemui.customization;

import android.util.Log;

import com.android.systemui.customization.listener.OnSettingsStatusListener;
import com.android.systemui.customization.listener.OnShowSettingsWindowListener;
import com.android.systemui.customization.listener.OnUpdateWeatherListener;


/**
 * @author Altair
 * @date :2020.03.26 下午 04:28
 * @description: 回调管理类
 */
public class NotifyMessageManager {
    private OnSettingsStatusListener mOnSettingsStatusListener;
    private OnShowSettingsWindowListener mSettingsWindowListener;
    private OnUpdateWeatherListener mOnUpdateWeather;

    public static NotifyMessageManager getInstance() {
        return SingletonHolder.sInstance;
    }

    private static class SingletonHolder {
        private static final NotifyMessageManager sInstance = new NotifyMessageManager();
    }
    
    public void setOnUpdateWeatherListener(OnUpdateWeatherListener listener) {
        this.mOnUpdateWeather = listener;
    }

    public void setOnShowSettingsWindowListener(OnShowSettingsWindowListener listener) {
        this.mSettingsWindowListener = listener;
    }

    public void setListener(OnSettingsStatusListener onSettingsStatusListener) {
        setOnSettingsStatusListener(onSettingsStatusListener);
    }

    public void setOnSettingsStatusListener(OnSettingsStatusListener listener) {
        this.mOnSettingsStatusListener = listener;
    }


    public void openOrClose(int type, boolean state) {
        if (mOnSettingsStatusListener == null) {
            Log.e("NotifyMessageManager", "OnSettingsStatusListener = null ");
            return;
        }
        Log.d("NotifyMessageManager", "openOrClose: " + type + " " + state);
        mOnSettingsStatusListener.openOrClose(type, state);
    }

    public void showSettingsWindow() {
        if (mSettingsWindowListener != null) {
            mSettingsWindowListener.ShowSettingsWindow();
        }
    }

    /**
     * hide 4 button
     *
     * @param isHide
     */
    public void hideNavigationBar(boolean isHide) {
        if (mSettingsWindowListener != null) {
            mSettingsWindowListener.hideNavigationBar(isHide);
        }
    }

    public void updateWeather(String weatherInfo) {
        if (mOnUpdateWeather != null) {
            mOnUpdateWeather.updateWeather(weatherInfo);
        }
    }

    public void releaseListener() {
        if (mOnSettingsStatusListener != null) {
            mOnSettingsStatusListener = null;
        }
        if (mSettingsWindowListener != null) {
            mSettingsWindowListener = null;
        }
    }

}
