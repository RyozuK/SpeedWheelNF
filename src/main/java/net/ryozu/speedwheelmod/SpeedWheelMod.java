package net.ryozu.speedwheelmod;

import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;


import java.util.UUID;
import java.util.function.Supplier;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SpeedWheelMod.MODID)
public class SpeedWheelMod {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "speedwheelmod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final UUID SPEED_MOD_UUID = UUID.fromString("e00f7b4b-660b-4e43-9db7-0b519a75a271"); //Random

    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MODID);

    public static final Supplier<AttachmentType<Integer>> SPEED_MOD = ATTACHMENT_TYPES.register(
            "speed_mod", () -> AttachmentType.builder(() -> 100).serialize(Codec.INT).build()
    );

    public SpeedWheelMod(IEventBus modEventBus, ModContainer modContainer) {

        // Register ourselves for server and other game events we are interested in
        ATTACHMENT_TYPES.register(modEventBus);

        // Register the mod's ForgeConfigSpec so that Forge can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

}
