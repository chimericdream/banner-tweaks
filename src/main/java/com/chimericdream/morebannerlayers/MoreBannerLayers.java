package com.chimericdream.morebannerlayers;

import com.chimericdream.morebannerlayers.config.ConfigManager;

public class MoreBannerLayers {
    public static int getLimit() {
        return ConfigManager.getConfig().maxBannerLayers;
    }
}
