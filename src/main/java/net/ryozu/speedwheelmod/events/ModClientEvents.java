package net.ryozu.speedwheelmod.events;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.ryozu.speedwheelmod.SpeedWheelMod;
import net.ryozu.speedwheelmod.keybinds.Keybinds;

@EventBusSubscriber(modid = SpeedWheelMod.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ModClientEvents {

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(Keybinds.getInstance().speed_mod_key);
        event.register(Keybinds.getInstance().speed_up_key);
        event.register(Keybinds.getInstance().speed_down_key);
    }
}