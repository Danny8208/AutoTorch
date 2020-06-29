package danny8208.autotorch;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("autotorch")
public class AutoTorch {
    public static final String MODID = "autotorch";
    public static final CreativeGroup GROUP = new CreativeGroup();

    public AutoTorch() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModRegistries.init(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}
