package net.pl3x.keyboard.mixin;

import java.util.Map;
import net.minecraft.client.KeyMapping;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(KeyMapping.class)
public interface KeyMappingAccessor {
    @Accessor("CATEGORY_SORT_ORDER")
    static @NotNull Map<String, Integer> getCategorySortOrder() {
        throw new AssertionError();
    }
}
