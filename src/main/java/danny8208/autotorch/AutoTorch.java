package danny8208.autotorch;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.theillusivec4.curios.api.SlotTypeMessage;

@Mod("autotorch")
public class AutoTorch {
    public static final String MODID = "autotorch";
    public static final CreativeGroup GROUP = new CreativeGroup();
    public static final Logger logger = LogManager.getLogger();

    public AutoTorch() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModRegistries.init(bus);
        bus.addListener(this::enqueueIMC);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        if (ModList.get().isLoaded("curios")) {
            InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("ring").size(1).build());
            logger.info("CuriosAPI has been detected. Registering rings to be able to be worn.");
        }
    }
}
