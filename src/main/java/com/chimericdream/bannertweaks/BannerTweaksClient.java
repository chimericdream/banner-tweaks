package com.chimericdream.bannertweaks;

import com.chimericdream.bannertweaks.networking.ModPacketsS2C;
import net.fabricmc.api.ClientModInitializer;

public class BannerTweaksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModPacketsS2C.register();
    }
}
