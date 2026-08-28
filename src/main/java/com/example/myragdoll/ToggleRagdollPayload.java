package com.example.myragdoll;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record ToggleRagdollPayload() implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<ToggleRagdollPayload> TYPE = 
        new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(MyRagdoll.MODID, "toggle_ragdoll"));

    public static final StreamCodec<ByteBuf, ToggleRagdollPayload> STREAM_CODEC = 
        StreamCodec.unit(new ToggleRagdollPayload());

    @Override
    public CustomPacketPayload.Type<ToggleRagdollPayload> type() {
        return TYPE;
    }

    public static void handle(ToggleRagdollPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer serverPlayer) {
                boolean currentState = serverPlayer.getData(ModAttachments.IS_RAGDOLL);
                serverPlayer.setData(ModAttachments.IS_RAGDOLL, !currentState);
            }
        });
    }
}