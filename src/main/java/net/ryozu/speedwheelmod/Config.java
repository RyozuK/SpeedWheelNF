package net.ryozu.speedwheelmod;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = SpeedWheelMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.IntValue SPEED_MAX = BUILDER
            .comment("Maximum speed multiplier for player. 100 is normal speed, 200 is double speed")
            .defineInRange("speedMax", 110, 100, 1000);
    private static final ModConfigSpec.IntValue SPEED_MIN = BUILDER
            .comment("Minimum speed multiplier, 0 means not moving")
            .defineInRange("speedMin", 25, 0, 100);
    private static final ModConfigSpec.IntValue SPEED_STEP = BUILDER
            .comment("Amount to change the speed multiplier each time.")
            .defineInRange("speedStep", 5, 1,100);
    private static final ModConfigSpec.BooleanValue MOD_KEY = BUILDER
            .comment("Is mod key needed for keybinds? (Always needed for mouse wheel)")
            .define("needsModKey", false);
    private static final ModConfigSpec.IntValue CONFIG_VERSION = BUILDER
            .comment("Used to invalidate old configs, changing this value will cause a config reset")
            .defineInRange("configVer", 0, 0, 1000);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static int speedMax;
    public static int speedMin;
    public static int speedStep;
    public static boolean needsModKey;
    public static int configVer;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        int targetVersion = 1;
        configVer = CONFIG_VERSION.get();

        if (configVer != targetVersion) {
            CONFIG_VERSION.set(targetVersion);
            SPEED_MAX.set(SPEED_MAX.getDefault());
            SPEED_MIN.set(SPEED_MIN.getDefault());
            SPEED_STEP.set(SPEED_STEP.getDefault());
            CONFIG_VERSION.save();
            SPEED_MAX.save();
            SPEED_MIN.save();
            SPEED_STEP.save();
        }

        speedMax = SPEED_MAX.get();
        speedMin = SPEED_MIN.get();
        speedStep = SPEED_STEP.get();
        needsModKey = MOD_KEY.get();
    }
}
