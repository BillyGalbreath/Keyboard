package net.pl3x.keyboard.mixin;

import java.util.List;
import net.fabricmc.fabric.impl.client.keybinding.KeyBindingRegistryImpl;
import net.minecraft.client.KeyMapping;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@SuppressWarnings("UnstableApiUsage")
@Mixin(value = KeyBindingRegistryImpl.class, remap = false)
public interface KeyBindingRegistryImplAccessor {
    @Accessor("MODDED_KEY_BINDINGS")
    static List<KeyMapping> getModdedKeyBindings() {
        throw new AssertionError();
    }

    @Accessor("MODDED_KEY_BINDINGS")
    static void setModdedKeyBindings(List<KeyMapping> moddedKeyBindings) {
        throw new AssertionError();
    }
}
