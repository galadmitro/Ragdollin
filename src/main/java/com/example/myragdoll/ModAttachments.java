package com.example.myragdoll;

import com.mojang.serialization.Codec;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = 
        DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, MyRagdoll.MODID);

    public static final Supplier<AttachmentType<Boolean>> IS_RAGDOLL = ATTACHMENT_TYPES.register(
        "is_ragdoll",
        () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).build()
    );
}