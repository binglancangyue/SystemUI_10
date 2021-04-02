package com.android.systemui.customization.listener;

/**
 * @author Altair
 * @date :2021.03.17 下午 04:57
 * @description:
 */
public interface OnSettingsStatusListener {
//    void openStatus(int type);
//
//    void closeStatus(int type);

    void openOrClose(int type, boolean state);
}
