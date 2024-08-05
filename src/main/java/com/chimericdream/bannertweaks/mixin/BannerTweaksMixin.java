package com.chimericdream.bannertweaks.mixin;

import com.chimericdream.bannertweaks.config.ConfigManager;
import net.minecraft.item.BannerItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BannerItem.class)
public class BannerTweaksMixin {
	@ModifyConstant(method = "appendBannerTooltip", constant = @Constant(intValue = 6))
	private static int getLimit(int curr) {
		return ConfigManager.getConfig().maxBannerLayers;
	}
}
