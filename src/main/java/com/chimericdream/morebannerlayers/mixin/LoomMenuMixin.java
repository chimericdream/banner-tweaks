package com.chimericdream.morebannerlayers.mixin;

import com.chimericdream.morebannerlayers.MoreBannerLayers;
import net.minecraft.client.gui.screen.ingame.LoomScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(LoomScreen.class)
public class LoomMenuMixin {
    @ModifyConstant(method = "onInventoryChanged", constant = @Constant(intValue = 6))
    public int getLimit(int constant) {
        return MoreBannerLayers.getLimit();
    }
}
