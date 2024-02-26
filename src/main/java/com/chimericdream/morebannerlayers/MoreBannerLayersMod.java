package com.chimericdream.morebannerlayers;

import net.fabricmc.api.ModInitializer;
import com.chimericdream.morebannerlayers.config.ModConfigs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoreBannerLayersMod implements ModInitializer {
	public static final String MOD_ID = "morebannerlayers-reborn";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModConfigs.registerConfigs();
	}
}
