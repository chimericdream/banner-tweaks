package com.chimericdream.morebannerlayers.mixin;

import com.chimericdream.morebannerlayers.MoreBannerLayers;
import net.minecraft.item.BannerItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BannerItem.class)
public class MoreBannerLayersMixin {
	//@ModifyConstant(method = "appendBannerTooltip", constant = @Constant(intValue = 6))
	private static int setlimit(int constant) {
		return 1;
	}

	@ModifyConstant(method = "appendBannerTooltip", constant = @Constant(intValue = 6))
	private static int getLimit(int curr) {
		return MoreBannerLayers.getLimit();
	}
}
