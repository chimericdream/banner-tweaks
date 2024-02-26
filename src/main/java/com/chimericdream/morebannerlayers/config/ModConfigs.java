package com.chimericdream.morebannerlayers.config;

import com.chimericdream.morebannerlayers.MoreBannerLayersMod;
import com.mojang.datafixers.util.Pair;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    public static int BANNER_LAYERS;

    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(MoreBannerLayersMod.MOD_ID + "_config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("morebannerlayers.layers", 12), "int");
    }

    private static void assignConfigs() {
        BANNER_LAYERS = CONFIG.getOrDefault("morebannerlayers.layers", 12);

        MoreBannerLayersMod.LOGGER.info("All " + configs.getConfigsList().size() + " have been assigned properly");
    }
}
