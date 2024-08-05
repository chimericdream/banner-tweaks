package com.chimericdream.bannertweaks.config;

import com.chimericdream.bannertweaks.ModInfo;
import com.chimericdream.bannertweaks.BannerTweaksMod;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = ModInfo.MOD_ID)
@Config.Gui.Background("minecraft:textures/block/composter_side.png")
public class BannerTweaksConfig implements ConfigData {
    public String reset = "";

    public int maxBannerLayers = Defaults.MAX_BANNER_LAYERS;

    public void validatePostLoad() {
        if (this.maxBannerLayers < 0 || this.maxBannerLayers > 16) {
            BannerTweaksMod.LOGGER.info("[config] Invalid value found for 'maxBannerLayers'! Resetting to default.");
            this.maxBannerLayers = Defaults.MAX_BANNER_LAYERS;
        }
    }

    public static class Defaults {
        public static int MAX_BANNER_LAYERS = 12;
    }
}
