package com.chimericdream.bannertweaks;

import com.chimericdream.bannertweaks.config.ConfigManager;
import com.chimericdream.bannertweaks.networking.ModPacketsS2C;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BannerTweaksMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger(ModInfo.MOD_ID);

	static {
		ConfigManager.registerAutoConfig();
	}

	@Override
	public void onInitialize() {
		PayloadTypeRegistry.playS2C().register(ModPacketsS2C.BannerLayerLimitPayload.ID, ModPacketsS2C.BannerLayerLimitPayload.CODEC);
	}
}
