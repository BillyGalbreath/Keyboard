package net.pl3x.keyboard.mixin;

import java.util.List;
import net.fabricmc.fabric.impl.client.keybinding.KeyBindingRegistryImpl;
import net.minecraft.client.KeyMapping;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@SuppressWarnings("UnstableApiUsage") // we know...
@Mixin(value = KeyBindingRegistryImpl.class, remap = false)
public interface KeyBindingRegistryImplAccessor {
    @Accessor("MODDED_KEY_BINDINGS")
    static @NotNull List<KeyMapping> getModdedKeyBindings() {
        throw new AssertionError();
    }

    @Mutable
    @Accessor("MODDED_KEY_BINDINGS")
    static void setModdedKeyBindings(@NotNull List<KeyMapping> moddedKeyBindings) {
        throw new AssertionError();
    }
}
