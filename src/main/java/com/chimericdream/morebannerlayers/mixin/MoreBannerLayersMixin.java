package com.chimericdream.morebannerlayers.mixin;

import com.chimericdream.morebannerlayers.config.ConfigManager;
import net.minecraft.item.BannerItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BannerItem.class)
public class MoreBannerLayersMixin {
	@ModifyConstant(method = "appendBannerTooltip", constant = @Constant(intValue = 6))
	private static int getLimit(int curr) {
		return ConfigManager.getConfig().maxBannerLayers;
	}
}
