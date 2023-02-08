package net.lstwo.slidepoint;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;

public class Sounds {

    public static final SoundEvent VINE_BOOM = new SoundEvent(new ResourceLocation("deviceapp:vine_boom")).setRegistryName("vine_boom");

    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        event.getRegistry().register(VINE_BOOM);
    }

}
