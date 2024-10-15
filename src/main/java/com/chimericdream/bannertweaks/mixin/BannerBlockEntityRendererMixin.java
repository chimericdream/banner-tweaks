package com.chimericdream.bannertweaks.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.WallBannerBlock;
import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BannerBlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BannerBlockEntityRenderer.class)
abstract public class BannerBlockEntityRendererMixin {
    private BlockEntityRenderDispatcher dispatcher;
    private TextRenderer textRenderer;

    @Inject(method = "<init>(Lnet/minecraft/client/render/block/entity/BlockEntityRendererFactory$Context;)V", at = @At("TAIL"))
    private void bt$init(BlockEntityRendererFactory.Context ctx, CallbackInfo ci) {
        this.dispatcher = ctx.getRenderDispatcher();
        this.textRenderer = ctx.getTextRenderer();
    }

    @Inject(method = "render(Lnet/minecraft/block/entity/BannerBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V", at = @At("TAIL"))
    private void bt$renderBannerName(BannerBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, CallbackInfo ci) {
        renderLabelIfPresent(entity, matrices, vertexConsumers, light);
    }

    protected double getSquaredDistanceToCamera(BannerBlockEntity entity) {
        return entity.getPos().getSquaredDistance(dispatcher.camera.getPos());
    }

    public TextRenderer getTextRenderer() {
        return this.textRenderer;
    }

    protected void renderLabelIfPresent(BannerBlockEntity entity, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (getSquaredDistanceToCamera(entity) > 1024.0) {
            return;
        }

        Text text = entity.getCustomName();

        if (!MinecraftClient.isHudEnabled() || text == null) {
            return;
        }

        float verticalOffset = 0f;
        BlockState state = entity.getCachedState();
        if (state.getBlock() instanceof WallBannerBlock) {
            verticalOffset = -1f;
        }

        matrices.push();

        matrices.translate(0.5f, 2.125f + verticalOffset, 0.5f);
        matrices.multiply(this.dispatcher.camera.getRotation());
        matrices.scale(0.025f, -0.025f, 0.025f);

        Matrix4f matrix4f = matrices.peek().getPositionMatrix();

        float f = MinecraftClient.getInstance().options.getTextBackgroundOpacity(0.25f);
        int j = (int) (f * 255.0f) << 24;

        TextRenderer textRenderer = this.getTextRenderer();
        float g = (float) (-textRenderer.getWidth(text) / 2);

        textRenderer.draw(text, g, 0f, 553648127, false, matrix4f, vertexConsumers, TextRenderer.TextLayerType.SEE_THROUGH, j, light);
        textRenderer.draw(text, g, 0f, -1, false, matrix4f, vertexConsumers, TextRenderer.TextLayerType.NORMAL, 0, light);

        matrices.pop();
    }
}
