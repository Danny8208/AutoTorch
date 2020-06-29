package danny8208.autotorch;

import danny8208.autotorch.items.AutoTorchItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRegistries {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AutoTorch.MODID);
    public static final RegistryObject<Item> AUTO_TORCH = ITEMS.register("auto_torch", AutoTorchItem::new);

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                AUTO_TORCH.get()
        );
    }

    protected static void init(IEventBus bus) {
        ITEMS.register(bus);
    }
}
