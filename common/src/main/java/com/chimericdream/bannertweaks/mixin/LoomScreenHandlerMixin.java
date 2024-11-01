package com.chimericdream.bannertweaks.mixin;

import com.chimericdream.bannertweaks.config.BannerTweaksConfig;
import net.minecraft.screen.LoomScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(LoomScreenHandler.class)
public class LoomScreenHandlerMixin {
    @ModifyConstant(method = "onContentChanged", constant = @Constant(intValue = 6))
    public int getLimit(int constant) {
        return BannerTweaksConfig.HANDLER.instance().maxBannerLayers;
    }
}
