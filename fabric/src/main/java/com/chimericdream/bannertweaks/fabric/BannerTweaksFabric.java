package com.chimericdream.bannertweaks.fabric;

import com.chimericdream.bannertweaks.BannerTweaksMod;
import com.chimericdream.bannertweaks.fabric.network.FabricServerNetworking;
import net.fabricmc.api.ModInitializer;

public final class BannerTweaksFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        BannerTweaksMod.init();

        BannerTweaksMod.LOGGER.info("Initializing Fabric server networking");
        FabricServerNetworking.init();
    }
}
