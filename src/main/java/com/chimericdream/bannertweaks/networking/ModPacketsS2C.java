package com.chimericdream.bannertweaks.networking;

import com.chimericdream.bannertweaks.config.ConfigManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public class ModPacketsS2C {
    @Environment(EnvType.CLIENT)
    public static void register() {
        ClientPlayNetworking.registerGlobalReceiver(BannerLayerLimitPayload.ID, (payload, context) -> ConfigManager.getConfig().maxBannerLayers = payload.limit);
    }

    public record BannerLayerLimitPayload(int limit) implements CustomPayload {
        public static final CustomPayload.Id<BannerLayerLimitPayload> ID = new CustomPayload.Id<>(ModPackets.BANNER_LAYER_LIMIT);
        public static final PacketCodec<RegistryByteBuf, BannerLayerLimitPayload> CODEC = PacketCodec.tuple(PacketCodecs.INTEGER, BannerLayerLimitPayload::limit, BannerLayerLimitPayload::new);

        @Override
        public CustomPayload.Id<BannerLayerLimitPayload> getId() {
            return ID;
        }
    }
}
