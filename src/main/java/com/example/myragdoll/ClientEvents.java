package com.example.myragdoll.client;

import com.example.myragdoll.ModAttachments;
import com.example.myragdoll.MyRagdoll;
import com.example.myragdoll.network.ToggleRagdollPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = MyRagdoll.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        while (ModKeybindings.TOGGLE_RAGDOLL_KEY.consumeClick()) {
            PacketDistributor.sendToServer(new ToggleRagdollPayload());
        }
    }

    @SubscribeEvent
    public static void onComputeCameraAngles(ViewportEvent.ComputeCameraAngles event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        if (player == null || !player.getData(ModAttachments.IS_RAGDOLL)) return;

        // Allows normal mouse rotation yaw & pitch without forcing target entity rotation
        event.setYaw(mc.player.getYRot());
        event.setPitch(mc.player.getXRot());
    }
}