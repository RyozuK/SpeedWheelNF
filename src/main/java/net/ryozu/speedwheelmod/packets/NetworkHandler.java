package net.ryozu.speedwheelmod.packets;


import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(bus= EventBusSubscriber.Bus.MOD)
public class NetworkHandler {
    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        // Sets the current network version
        final PayloadRegistrar registrar = event.registrar("1");
        registrar.playToServer(
                SpeedPayload.TYPE,
                SpeedPayload.STREAM_CODEC,
                new ServerPayloadHandler<SpeedPayload>());

    }
}

