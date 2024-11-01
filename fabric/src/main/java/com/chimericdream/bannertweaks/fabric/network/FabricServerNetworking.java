package com.chimericdream.bannertweaks.fabric.network;

import com.chimericdream.bannertweaks.config.BannerTweaksConfig;
import com.chimericdream.bannertweaks.network.ServerNetworking;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class FabricServerNetworking {
    @Environment(EnvType.CLIENT)
    public static void init() {
        PayloadTypeRegistry.playS2C().register(ServerNetworking.BannerLayerLimitPayload.ID, ServerNetworking.BannerLayerLimitPayload.CODEC);

        ClientPlayNetworking.registerGlobalReceiver(
            ServerNetworking.BannerLayerLimitPayload.ID,
            (payload, context) -> BannerTweaksConfig.HANDLER.instance().maxBannerLayers = payload.getLimit()
        );
    }
}
