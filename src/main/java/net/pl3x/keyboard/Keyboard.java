package net.pl3x.keyboard;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

public class Keyboard {
    private static final Keyboard instance = new Keyboard();

    private final KeyBindRegistry registry = new KeyBindRegistry();

    private Keyboard() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) {
                return;
            }
            if (client.screen != null) {
                return;
            }
            this.registry.getKeys().forEach(key -> {
                while (key.consumeClick()) {
                    if (key.hasAlt() == hasAltDown() &&
                            key.hasCtrl() == hasCtrlDown() &&
                            key.hasMeta() == hasMetaDown() &&
                            key.hasShift() == hasShiftDown()) {
                        key.execute(client);
                    }
                }
            });
        });
    }

    public static @Nullable Key getKey(@NotNull String name) {
        return instance.registry.getKey(name);
    }

    public static @NotNull Key bindKey(@NotNull Key key) {
        return instance.registry.register(Minecraft.getInstance(), key);
    }

    public static void unbindKey(@NotNull Key key) {
        instance.registry.unregister(Minecraft.getInstance(), key);
    }

    public static boolean hasAltDown() {
        return hasKeyDown(GLFW.GLFW_MOD_ALT); //hasKeyDown(GLFW.GLFW_KEY_LEFT_ALT) || hasKeyDown(GLFW.GLFW_KEY_RIGHT_ALT);
    }

    public static boolean hasCtrlDown() {
        if (Minecraft.ON_OSX) {
            return hasKeyDown(GLFW.GLFW_MOD_SUPER); //hasKeyDown(GLFW.GLFW_KEY_LEFT_SUPER) || hasKeyDown(GLFW.GLFW_KEY_RIGHT_SUPER);
        } else {
            return hasKeyDown(GLFW.GLFW_MOD_CONTROL); //hasKeyDown(GLFW.GLFW_KEY_LEFT_CONTROL) || hasKeyDown(GLFW.GLFW_KEY_RIGHT_CONTROL);
        }
    }

    public static boolean hasMetaDown() {
        if (Minecraft.ON_OSX) {
            return hasKeyDown(GLFW.GLFW_MOD_CONTROL); //hasKeyDown(GLFW.GLFW_KEY_LEFT_CONTROL) || hasKeyDown(GLFW.GLFW_KEY_RIGHT_CONTROL);
        } else {
            return hasKeyDown(GLFW.GLFW_MOD_SUPER); //hasKeyDown(GLFW.GLFW_KEY_LEFT_SUPER) || hasKeyDown(GLFW.GLFW_KEY_RIGHT_SUPER);
        }
    }

    public static boolean hasShiftDown() {
        return hasKeyDown(GLFW.GLFW_MOD_SHIFT); //hasKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT) || hasKeyDown(GLFW.GLFW_KEY_RIGHT_SHIFT);
    }

    public static boolean hasKeyDown(int keycode) {
        return InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), keycode);
    }
}
