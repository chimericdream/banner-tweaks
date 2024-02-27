package com.chimericdream.morebannerlayers;

import com.chimericdream.morebannerlayers.config.ConfigManager;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoreBannerLayersMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger(ModInfo.MOD_ID);

	static {
		ConfigManager.registerAutoConfig();
	}

	@Override
	public void onInitialize() {
	}
}
