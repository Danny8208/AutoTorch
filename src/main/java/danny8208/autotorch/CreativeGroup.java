package danny8208.autotorch;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CreativeGroup extends ItemGroup {
    public CreativeGroup() {
        super(AutoTorch.MODID);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(null);
    }
}
