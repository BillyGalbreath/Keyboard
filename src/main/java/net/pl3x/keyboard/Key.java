package net.pl3x.keyboard;

import com.google.common.base.Preconditions;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class Key extends KeyMapping {
    private boolean alt;
    private boolean ctrl;
    private boolean meta;
    private boolean shift;

    private Action action;

    public Key(@NotNull String category, @NotNull String name, int keycode) {
        this(category, name, keycode, Action.BLANK);
    }

    public Key(@NotNull String category, @NotNull String name, int keycode, @NotNull Action action) {
        this(category, name, keycode, false, false, false, false, action);
    }

    public Key(@NotNull String category, @NotNull String name, int keycode, boolean alt, boolean ctrl, boolean meta, boolean shift) {
        this(category, name, keycode, alt, ctrl, meta, shift, Action.BLANK);
    }

    public Key(@NotNull String category, @NotNull String name, int keycode, boolean alt, boolean ctrl, boolean meta, boolean shift, @NotNull Action action) {
        super(name, keycode, category);
        setAlt(alt);
        setCtrl(ctrl);
        setMeta(meta);
        setShift(shift);
        setAction(action);
    }

    public boolean hasAlt() {
        return this.alt;
    }

    public void setAlt(boolean alt) {
        this.alt = alt;
    }

    public boolean hasCtrl() {
        return this.ctrl;
    }

    public void setCtrl(boolean ctrl) {
        this.ctrl = ctrl;
    }

    public boolean hasMeta() {
        return this.meta;
    }

    public void setMeta(boolean meta) {
        this.meta = meta;
    }

    public boolean hasShift() {
        return this.shift;
    }

    public void setShift(boolean shift) {
        this.shift = shift;
    }

    public @NotNull Action getAction() {
        return this.action;
    }

    public void setAction(@NotNull Action action) {
        Preconditions.checkNotNull(action, "Action cannot be null");
        this.action = action;
    }

    public void execute(@NotNull Minecraft client) {
        getAction().execute(client);
    }

    @Override
    public @NotNull Component getTranslatedKeyMessage() {
        return super.getTranslatedKeyMessage();
    }

    @FunctionalInterface
    public interface Action {
        Action BLANK = client -> {
        };

        void execute(@NotNull Minecraft client);
    }
}
