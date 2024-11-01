package com.chimericdream.bannertweaks.config;

import com.chimericdream.bannertweaks.ModInfo;
import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.platform.YACLPlatform;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BannerTweaksConfig {
    @SerialEntry
    public int maxBannerLayers = Defaults.MAX_BANNER_LAYERS;

    public static ConfigClassHandler<BannerTweaksConfig> HANDLER = ConfigClassHandler.createBuilder(BannerTweaksConfig.class)
        .id(Identifier.of(ModInfo.MOD_ID, "config"))
        .serializer(config -> GsonConfigSerializerBuilder.create(config)
            .setPath(YACLPlatform.getConfigDir().resolve("bannertweaks.json5"))
            .appendGsonBuilder(GsonBuilder::setPrettyPrinting)
            .setJson5(true)
            .build())
        .build();

    public static Screen configScreen(Screen parent) {
        return YetAnotherConfigLib.create(HANDLER, ((defaults, config, builder) -> builder
            .title(Text.literal("Banner Tweaks Config"))
            .category(ConfigCategory.createBuilder()
                .name(Text.literal("Banner Tweaks Config"))
                .option(Option.<Integer>createBuilder()
                    .name(Text.literal("Max. banner layers"))
                    .description(OptionDescription.of(Text.literal("The maximum number of layers a banner can have. Default: 12")))
                    .binding(12, () -> config.maxBannerLayers, newVal -> config.maxBannerLayers = newVal)
                    .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                        .range(1, 32)
                        .step(1))
                    .build())
                .build())
        )).generateScreen(parent);
    }

    public static class Defaults {
        public static int MAX_BANNER_LAYERS = 12;
    }
}
