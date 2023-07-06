package net.pl3x.keyboard.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Minecraft.class)
public interface MinecraftAccessor {
    @Accessor
    @Mutable
    void setOptions(@Nullable Options options);
}
