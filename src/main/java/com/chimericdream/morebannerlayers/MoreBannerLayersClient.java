package com.chimericdream.morebannerlayers;

import com.chimericdream.morebannerlayers.networking.ModPacketsS2C;
import net.fabricmc.api.ClientModInitializer;

public class MoreBannerLayersClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModPacketsS2C.register();
    }
}
