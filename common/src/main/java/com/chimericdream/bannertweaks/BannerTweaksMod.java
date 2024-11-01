package com.chimericdream.bannertweaks;

import com.chimericdream.bannertweaks.config.BannerTweaksConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BannerTweaksMod {
    public static final Logger LOGGER = LoggerFactory.getLogger(ModInfo.MOD_ID);

    public static void init() {
        BannerTweaksConfig.HANDLER.load();
    }
}
