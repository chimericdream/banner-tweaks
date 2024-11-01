package com.chimericdream.bannertweaks.neoforge.mixin;

import com.chimericdream.bannertweaks.config.BannerTweaksConfig;
import com.chimericdream.bannertweaks.network.ServerNetworking;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ConnectedClientData;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PlayerManager.class)
public class LoginMixin {
    @Inject(at = @At("TAIL"), method = "onPlayerConnect(Lnet/minecraft/network/ClientConnection;Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/server/network/ConnectedClientData;)V")
    private void syncBannerLayerLimit(ClientConnection connection, ServerPlayerEntity player, ConnectedClientData clientData, CallbackInfo info) {
        player.networkHandler.send(new ServerNetworking.BannerLayerLimitPayload(BannerTweaksConfig.HANDLER.instance().maxBannerLayers));
    }
}
