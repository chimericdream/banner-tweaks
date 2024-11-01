package com.chimericdream.bannertweaks.network;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public class ServerNetworking {
    public static void init() {
    }

    public record BannerLayerLimitPayload(int limit) implements CustomPayload {
        public static final Id<BannerLayerLimitPayload> ID = new Id<>(ModPackets.BANNER_LAYER_LIMIT);
        public static final PacketCodec<PacketByteBuf, BannerLayerLimitPayload> CODEC = PacketCodec.tuple(PacketCodecs.INTEGER, BannerLayerLimitPayload::limit, BannerLayerLimitPayload::new);

        @Override
        public Id<BannerLayerLimitPayload> getId() {
            return ID;
        }

        public int getLimit() {
            return limit;
        }
    }
}
