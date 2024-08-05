package com.chimericdream.bannertweaks.config;

import com.chimericdream.bannertweaks.config.BannerTweaksConfig.Defaults;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

import java.util.Objects;
import java.util.function.Consumer;

public class ConfigManager {
    private static ConfigHolder<BannerTweaksConfig> holder;

    public static final Consumer<BannerTweaksConfig> DEFAULT = (config) -> {
        config.reset = "Erase to reset";
        config.maxBannerLayers = Defaults.MAX_BANNER_LAYERS;
    };

    public static void registerAutoConfig() {
        if (holder != null) {
            throw new IllegalStateException("Configuration already registered");
        }

        holder = AutoConfig.register(BannerTweaksConfig.class, JanksonConfigSerializer::new);

        if (holder.getConfig().reset == null || Objects.equals(holder.getConfig().reset, "")) {
            DEFAULT.accept(holder.getConfig());
        }

        holder.save();
    }

    public static BannerTweaksConfig getConfig() {
        if (holder == null) {
            return new BannerTweaksConfig();
        }

        return holder.getConfig();
    }

    public static void load() {
        if (holder == null) {
            registerAutoConfig();
        }

        holder.load();
    }

    public static void save() {
        if (holder == null) {
            registerAutoConfig();
        }

        holder.save();
    }
}
