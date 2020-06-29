package danny8208.autotorch.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class TorchUtil {
    public static void addTorches(ItemStack stack, int torches) {
        stack.getTag().putInt("StoredTorches", getTorches(stack) + torches);
    }

    public static void subtractTorches(ItemStack stack, int torches) {
        stack.getTag().putInt("StoredTorches", getTorches(stack) - torches);
    }

    public static void addTorchTag(ItemStack stack) {
        if (!stack.hasTag()) {
            stack.setTag(new CompoundNBT());
        }
    }

    public static int getTorches(ItemStack stack) {
        return stack.getTag().getInt("StoredTorches");
    }
}
