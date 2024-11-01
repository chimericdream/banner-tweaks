package com.chimericdream.bannertweaks.neoforge.network;

import com.chimericdream.bannertweaks.ModInfo;
import com.chimericdream.bannertweaks.config.BannerTweaksConfig;
import com.chimericdream.bannertweaks.network.ServerNetworking;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = ModInfo.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NeoForgeServerNetworking {
    public static void init() {
    }

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");
        registrar.commonToClient(
            ServerNetworking.BannerLayerLimitPayload.ID,
            ServerNetworking.BannerLayerLimitPayload.CODEC,
            NeoForgeServerNetworking::receiveBannerLimitConfigPacket
        );
    }

    public static void receiveBannerLimitConfigPacket(final ServerNetworking.BannerLayerLimitPayload payload, final IPayloadContext context) {
        BannerTweaksConfig.HANDLER.instance().maxBannerLayers = payload.getLimit();
    }
}
