package net.pl3x.keyboard;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.fabricmc.fabric.impl.client.keybinding.KeyBindingRegistryImpl;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.pl3x.keyboard.mixin.KeyBindingRegistryImplAccessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("UnstableApiUsage")
public class KeyBindRegistry {
    private final Map<String, Key> keys = new HashMap<>();

    public @NotNull List<Key> getKeys() {
        return this.keys.values().stream().toList();
    }

    public @Nullable Key getKey(@NotNull String name) {
        return this.keys.get(name);
    }

    public @NotNull Key register(@NotNull Minecraft client, @NotNull Key key) {
        // get fabric's list of keybinds and ensure we're not adding twice
        List<KeyMapping> moddedKeyBindings = KeyBindingRegistryImplAccessor.getModdedKeyBindings();
        for (KeyMapping mapping : moddedKeyBindings) {
            if (mapping == key) {
                throw new IllegalArgumentException("Attempted to register a key binding twice: " + key.getName());
            } else if (mapping.getName().equals(key.getName())) {
                throw new IllegalArgumentException("Attempted to register two key bindings with equal ID: " + key.getName());
            }
        }

        // add the category, if it doesn't exist
        KeyBindingRegistryImpl.addCategory(key.getCategory());

        // add the key to fabric's lists
        Options options = client.options;
        client.options = null;
        KeyBindingRegistryImpl.registerKeyBinding(key);
        client.options = options;

        // store the key for ourselves to make lookups easier and stuff
        this.keys.put(key.getName(), key);

        // rebuild vanilla's internal bindings list
        if (client.options != null) {
            client.options.keyMappings = KeyBindingRegistryImpl.process(client.options.keyMappings);
        }

        // give the key back
        return key;
    }

    public void unregister(@NotNull Minecraft client, @NotNull Key key) {
        // try to remove the key from ours and fabric's lists
        @SuppressWarnings("ConstantValue") // client.options could be null here
        List<KeyMapping> allKeys = client.options == null ? new ArrayList<>() : Lists.newArrayList(client.options.keyMappings);
        if (this.keys.remove(key.getName()) != null | KeyBindingRegistryImplAccessor.getModdedKeyBindings().remove(key) | allKeys.remove(key)) {
            // rebuild vanilla's internal bindings list if we removed the key from either
            client.options.keyMappings = allKeys.toArray(new KeyMapping[0]);
        }
    }
}
