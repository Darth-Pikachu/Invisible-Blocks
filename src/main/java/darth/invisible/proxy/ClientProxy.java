package darth.invisible.proxy;

import darth.invisible.render.ClientEventHandler;
import darth.invisible.InvisibleBlocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=InvisibleBlocks.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientProxy extends CommonProxy
{
    @Override
    public void init()
    {
        super.init();
        ClientEventHandler handle = new ClientEventHandler();
        MinecraftForge.EVENT_BUS.register(handle);
    }
}
