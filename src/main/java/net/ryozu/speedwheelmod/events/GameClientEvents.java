package net.ryozu.speedwheelmod.events;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.ryozu.speedwheelmod.Config;
import net.ryozu.speedwheelmod.SpeedWheelMod;
import net.ryozu.speedwheelmod.keybinds.Keybinds;
import net.ryozu.speedwheelmod.packets.SpeedPayload;

@EventBusSubscriber(modid = SpeedWheelMod.MODID, value = Dist.CLIENT, bus= EventBusSubscriber.Bus.GAME)
public class GameClientEvents {
    public static int id = 0;

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (Keybinds.getInstance().speed_up_key.consumeClick()
                && (Keybinds.getInstance().speed_mod_key.isDown() || !Config.needsModKey)) {
            PacketDistributor.sendToServer(new SpeedPayload(true));
        }
        if (Keybinds.getInstance().speed_down_key.consumeClick()
                && (Keybinds.getInstance().speed_mod_key.isDown() || !Config.needsModKey)) {
            PacketDistributor.sendToServer(new SpeedPayload(false));
        }
    }

    @SubscribeEvent
    public static void onMouseScrolled(InputEvent.MouseScrollingEvent event) {
        if(Keybinds.getInstance().speed_mod_key.isDown()) {
            if (event.getScrollDeltaY() > 0) {
                PacketDistributor.sendToServer(new SpeedPayload(true));
                event.setCanceled(true);
            }
            if (event.getScrollDeltaY() < 0) {
                PacketDistributor.sendToServer(new SpeedPayload(false));
                event.setCanceled(true);
            }
        }
    }
}