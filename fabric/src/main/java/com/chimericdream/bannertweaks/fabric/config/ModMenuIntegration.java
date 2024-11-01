package com.chimericdream.bannertweaks.fabric.config;

import com.chimericdream.bannertweaks.config.BannerTweaksConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@SuppressWarnings({"unused", "overrides", "deprecated"})
@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return BannerTweaksConfig::configScreen;
    }
}
