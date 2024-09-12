package net.ryozu.speedwheelmod.packets;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.ryozu.speedwheelmod.SpeedWheelMod;
import net.ryozu.speedwheelmod.speedmod.PlayerSpeedMod;
import org.jetbrains.annotations.NotNull;

import static net.ryozu.speedwheelmod.SpeedWheelMod.SPEED_MOD;

public class ServerPayloadHandler<T extends CustomPacketPayload> implements IPayloadHandler<T> {

    @Override
    public void handle(@NotNull T t, @NotNull IPayloadContext iPayloadContext) {
        SpeedPayload data = (SpeedPayload) t;
        ServerPlayer player = (ServerPlayer) iPayloadContext.player();

        //Get capabilities(data)
        int currentSpeed = player.getData(SPEED_MOD);
        //Calculate new speed
        int newSpeed;
        if (data.up()) {
            newSpeed = PlayerSpeedMod.speedUp(currentSpeed, data.step());
        } else {
            newSpeed = PlayerSpeedMod.speedDown(currentSpeed, data.step());
        }
        if (newSpeed != currentSpeed) {

            //Remove old AbilityModifier
            ResourceLocation modResource = ResourceLocation.fromNamespaceAndPath(
                    SpeedWheelMod.MODID, SpeedWheelMod.SPEED_MOD_UUID.toString());
            AttributeInstance moveSpeed = player.getAttribute(Attributes.MOVEMENT_SPEED);

            assert moveSpeed != null;
            moveSpeed.removeModifier(modResource);
            //Add new AbilityModifier
            moveSpeed.addTransientModifier(new AttributeModifier(modResource,
                    PlayerSpeedMod.getRealSpeed(newSpeed),
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            );
            player.setData(SPEED_MOD, newSpeed);

        }
    }
}
