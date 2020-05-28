package danny8208.autotorch;

import danny8208.autotorch.items.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CreativeGroup extends ItemGroup {
    public CreativeGroup() {
        super(AutoTorch.MODID);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ModItems.AUTO_TORCH);
    }
}
