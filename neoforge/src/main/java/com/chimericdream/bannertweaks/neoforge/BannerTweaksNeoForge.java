package com.chimericdream.bannertweaks.neoforge;

import com.chimericdream.bannertweaks.BannerTweaksMod;
import com.chimericdream.bannertweaks.ModInfo;
import com.chimericdream.bannertweaks.config.BannerTweaksConfig;
import com.chimericdream.bannertweaks.neoforge.network.NeoForgeServerNetworking;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(ModInfo.MOD_ID)
public final class BannerTweaksNeoForge {
    public BannerTweaksNeoForge() {
        BannerTweaksMod.init();
        NeoForgeServerNetworking.init();

        ModLoadingContext.get().registerExtensionPoint(
            IConfigScreenFactory.class,
            () -> (client, parent) -> BannerTweaksConfig.configScreen(parent)
        );
    }
}
