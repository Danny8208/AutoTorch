package danny8208.autotorch.items;

import danny8208.autotorch.util.EnabledUtil;
import danny8208.autotorch.util.TorchUtil;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.state.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static danny8208.autotorch.AutoTorch.GROUP;

public class AutoTorchItem extends Item {
    public AutoTorchItem() {
        super(new Properties().group(GROUP).maxStackSize(1));
        setRegistryName("auto_torch");
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        TorchUtil.addTorchTag(stack);
        String state = EnabledUtil.isEnabled(stack) ? "item.autotorch.current_state1" : "item.autotorch.current_state2";
        tooltip.add(new TranslationTextComponent(state));
        tooltip.add(new StringTextComponent("Stored Torches: " + TorchUtil.getTorches(stack)));
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
        if (!worldIn.isRemote && EnabledUtil.isEnabled(stack)) {
            if (!entityIn.isSpectator() &&
                    worldIn.isAirBlock(entityIn.getPosition()) &&
                    Block.hasSolidSideOnTop(worldIn, entityIn.getPosition().down()) &&
                    TorchUtil.getTorches(stack) > 0 &&
                    worldIn.getLight(entityIn.getPosition()) <= 7) {
                worldIn.setBlockState(entityIn.getPosition(), Blocks.TORCH.getDefaultState());
                TorchUtil.subtractTorches(stack, 1);
            }
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote) {
            ItemStack stack = playerIn.getHeldItem(handIn);
            if (playerIn.isShiftKeyDown()) {
                EnabledUtil.changeEnabled(stack);
                if (EnabledUtil.isEnabled(stack))
                    playerIn.sendStatusMessage(new TranslationTextComponent("item.autotorch.enabled"), true);
                else playerIn.sendStatusMessage(new TranslationTextComponent("item.autotorch.disabled"), true);
            }
            if (!playerIn.isShiftKeyDown()) {
                for (ItemStack playerStack : playerIn.inventory.mainInventory) {
                    int torches = 0;
                    if (playerStack.getItem() == Items.TORCH) {
                        torches += playerStack.getCount();
                        playerStack.setCount(0);
                    }
                    TorchUtil.addTorches(stack, torches);
                }
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        ItemStack stack = context.getItem();
        BlockPos pos = context.getPos();
        DirectionProperty facing = WallTorchBlock.HORIZONTAL_FACING;

        if (!world.isRemote && TorchUtil.getTorches(stack) > 0) {
            switch (context.getFace()) {
                case UP:
                    if (world.isAirBlock(pos.up()) && Block.hasSolidSideOnTop(world, pos.up())) {
                        world.setBlockState(pos.up(), Blocks.TORCH.getDefaultState(), 1);
                        TorchUtil.subtractTorches(stack, 1);
                    }
                    break;
                case NORTH:
                    if (world.isAirBlock(pos.north())) {
                        world.setBlockState(pos.north(), Blocks.WALL_TORCH.getDefaultState().with(facing, Direction.NORTH), 1);
                        TorchUtil.subtractTorches(stack, 1);
                    }
                    break;
                case SOUTH:
                    if (world.isAirBlock(pos.south())) {
                        world.setBlockState(pos.south(), Blocks.WALL_TORCH.getDefaultState().with(facing, Direction.SOUTH), 1);
                        TorchUtil.subtractTorches(stack, 1);
                    }
                    break;
                case EAST:
                    if (world.isAirBlock(pos.east())) {
                        world.setBlockState(pos.east(), Blocks.WALL_TORCH.getDefaultState().with(facing, Direction.EAST), 1);
                        TorchUtil.subtractTorches(stack, 1);
                    }
                    break;
                case WEST:
                    if (world.isAirBlock(pos.west())) {
                        world.setBlockState(pos.west(), Blocks.WALL_TORCH.getDefaultState().with(facing, Direction.WEST), 1);
                        TorchUtil.subtractTorches(stack, 1);
                    }
                    break;
            }
        }
        return super.onItemUse(context);
    }
}
