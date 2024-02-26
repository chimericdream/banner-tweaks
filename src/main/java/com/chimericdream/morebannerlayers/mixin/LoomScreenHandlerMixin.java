package com.chimericdream.morebannerlayers.mixin;

import com.chimericdream.morebannerlayers.MoreBannerLayers;
import net.minecraft.screen.LoomScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(LoomScreenHandler.class)
public class LoomScreenHandlerMixin {
    @ModifyConstant(method = "onContentChanged", constant = @Constant(intValue = 6))
    public int getLimit(int constant) {
        return MoreBannerLayers.getLimit();
    }
}
