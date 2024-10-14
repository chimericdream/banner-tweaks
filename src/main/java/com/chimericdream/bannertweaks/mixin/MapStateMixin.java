package com.chimericdream.bannertweaks.mixin;

import net.minecraft.item.map.MapBannerMarker;
import net.minecraft.item.map.MapDecoration;
import net.minecraft.item.map.MapDecorationType;
import net.minecraft.item.map.MapDecorationTypes;
import net.minecraft.item.map.MapState;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;
import java.util.Optional;

@Mixin(MapState.class)
abstract public class MapStateMixin {
    @Shadow public RegistryKey<World> dimension;

    @Shadow abstract public void markDecorationsDirty();
    @Shadow abstract public void removeDecoration(String id);
    @Shadow abstract public boolean decorationCountNotLessThan(int iconCount);

    @Shadow private int centerX;
    @Shadow private int centerZ;
    @Shadow private byte scale;
    @Shadow private boolean unlimitedTracking;
    @Shadow private int decorationCount;

    @Shadow @Final Map<String, MapDecoration> decorations;
    @Shadow private @Final Map<String, MapBannerMarker> banners;

    @Overwrite()
    private void addDecoration(RegistryEntry<MapDecorationType> type, @Nullable WorldAccess world, String key, double x, double z, double rotation, @Nullable Text text) {
        int i = 1 << this.scale;
        float f = (float) (x - (double) this.centerX) / (float) i;
        float g = (float) (z - (double) this.centerZ) / (float) i;
        byte b = (byte) ((int) ((double) (f * 2.0F) + 0.5));
        byte c = (byte) ((int) ((double) (g * 2.0F) + 0.5));
        byte d;

        if (f >= -64.0F && g >= -64.0F && f <= 64.0F && g <= 64.0F) {
            rotation += rotation < 0.0 ? -8.0 : 8.0;
            d = (byte) ((int) (rotation * 16.0 / 360.0));
            if (this.dimension == World.NETHER && world != null) {
                int k = (int) (world.getLevelProperties().getTimeOfDay() / 10L);
                d = (byte) (k * k * 34187121 + k * 121 >> 15 & 15);
            }
        } else {
            if (!type.matches(MapDecorationTypes.PLAYER)) {
                this.removeDecoration(key);
                return;
            }

            if (Math.abs(f) < 320.0F && Math.abs(g) < 320.0F) {
                type = MapDecorationTypes.PLAYER_OFF_MAP;
            } else {
                if (!this.unlimitedTracking) {
                    this.removeDecoration(key);
                    return;
                }

                type = MapDecorationTypes.PLAYER_OFF_LIMITS;
            }

            d = 0;
            if (f <= -64.0F) {
                b = -128;
            }

            if (g <= -64.0F) {
                c = -128;
            }

            if (f >= 64.0F) {
                b = 127;
            }

            if (g >= 64.0F) {
                c = 127;
            }
        }

        MapDecoration mapDecoration = new MapDecoration(type, b, c, d, Optional.ofNullable(text));
        MapDecoration mapDecoration2 = this.decorations.put(key, mapDecoration);
        if (!mapDecoration.equals(mapDecoration2)) {
            if (mapDecoration2 != null && mapDecoration2.type().value().trackCount()) {
                --this.decorationCount;
            }

            if (type.value().trackCount()) {
                ++this.decorationCount;
            }

            this.markDecorationsDirty();
        }

    }

    @Overwrite()
    public boolean addBanner(WorldAccess world, BlockPos pos) {
        double d = (double) pos.getX() + 0.5;
        double e = (double) pos.getZ() + 0.5;
        int i = 1 << this.scale;
        double f = (d - (double) this.centerX) / (double) i;
        double g = (e - (double) this.centerZ) / (double) i;

        if (f >= -64.0 && g >= -64.0 && f <= 64.0 && g <= 64.0) {
            MapBannerMarker mapBannerMarker = MapBannerMarker.fromWorldBlock(world, pos);
            if (mapBannerMarker == null) {
                return false;
            }

            if (this.banners.remove(mapBannerMarker.getKey(), mapBannerMarker)) {
                this.removeDecoration(mapBannerMarker.getKey());
                return true;
            }

            if (!this.decorationCountNotLessThan(256)) {
                this.banners.put(mapBannerMarker.getKey(), mapBannerMarker);
                this.addDecoration(mapBannerMarker.getDecorationType(), world, mapBannerMarker.getKey(), d, e, 180.0, mapBannerMarker.name().orElse(null));
                return true;
            }
        }

        return false;
    }
}