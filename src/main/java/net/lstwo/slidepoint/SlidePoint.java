package net.lstwo.slidepoint;

import com.mrcrayfish.device.api.ApplicationManager;
import net.lstwo.slidepoint.apps.TextEditPlus;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = SlidePoint.MODID, name = SlidePoint.NAME, version = SlidePoint.VERSION)
public class SlidePoint
{
    public static final String MODID = "slidepoint";
    public static final String NAME = "Slide Point";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();

        MinecraftForge.EVENT_BUS.register(new Sounds());
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());

        ApplicationManager.registerApplication(new ResourceLocation(SlidePoint.MODID, "texteditplus"), TextEditPlus.class);
        ApplicationManager.registerApplication(new ResourceLocation(SlidePoint.MODID, "slidepoint"), net.lstwo.slidepoint.apps.SlidePoint.class);
    }
}
