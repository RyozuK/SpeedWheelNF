package net.ryozu.speedwheelmod.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.ryozu.speedwheelmod.SpeedWheelMod;
import org.jetbrains.annotations.NotNull;

public record SpeedPayload(boolean up, int step) implements CustomPacketPayload {
    public static final Type<SpeedPayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(SpeedWheelMod.MODID, "speedpayload"));

    public static final StreamCodec<ByteBuf, SpeedPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            SpeedPayload::up,
            ByteBufCodecs.INT,
            SpeedPayload::step,
            SpeedPayload::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}

