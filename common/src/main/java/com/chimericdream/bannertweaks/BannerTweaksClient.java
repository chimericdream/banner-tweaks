package com.chimericdream.bannertweaks;

import com.chimericdream.bannertweaks.network.ServerNetworking;
import net.fabricmc.api.ClientModInitializer;

public class BannerTweaksClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ServerNetworking.init();
    }
}
