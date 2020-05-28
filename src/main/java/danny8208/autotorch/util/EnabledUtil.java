package danny8208.autotorch.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class EnabledUtil {
    public static boolean isEnabled(ItemStack stack) {
        return stack.hasTag() && stack.getTag().getBoolean("IsEnabled");
    }

    public static void changeEnabled(ItemStack stack) {
        if (!stack.hasTag()) {
            stack.setTag(new CompoundNBT());
        }
        boolean isEnabled = isEnabled(stack);
        stack.getTag().putBoolean("IsEnabled", !isEnabled);
    }
}
