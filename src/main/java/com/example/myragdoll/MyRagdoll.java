package com.example.myragdoll;

import com.example.myragdoll.network.ToggleRagdollPayload;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@Mod(MyRagdoll.MODID)
public class MyRagdoll {
    public static final String MODID = "myragdoll";

    public MyRagdoll(IEventBus modEventBus) {
        ModAttachments.ATTACHMENT_TYPES.register(modEventBus);
        modEventBus.addListener(this::registerPayloads);
    }

    private void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playToServer(
            ToggleRagdollPayload.TYPE,
            ToggleRagdollPayload.STREAM_CODEC,
            ToggleRagdollPayload::handle
        );
    }
}