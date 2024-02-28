package com.chimericdream.morebannerlayers.config;

import com.chimericdream.morebannerlayers.ModInfo;
import com.chimericdream.morebannerlayers.MoreBannerLayersMod;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = ModInfo.MOD_ID)
@Config.Gui.Background("minecraft:textures/block/composter_side.png")
public class MoreBannerLayersConfig implements ConfigData {
    public String reset = "";

    public int maxBannerLayers = Defaults.MAX_BANNER_LAYERS;

    public void validatePostLoad() {
        if (this.maxBannerLayers < 0 || this.maxBannerLayers > 16) {
            MoreBannerLayersMod.LOGGER.info("[config] Invalid value found for 'maxBannerLayers'! Resetting to default.");
            this.maxBannerLayers = Defaults.MAX_BANNER_LAYERS;
        }
    }

    public static class Defaults {
        public static int MAX_BANNER_LAYERS = 12;
    }
}
