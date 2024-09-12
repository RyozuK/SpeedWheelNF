package net.ryozu.speedwheelmod;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

//@EventBusSubscriber(modid = SpeedWheelMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config
{
    public static final ModConfigSpec CLIENT_SPEC;
    public static final ClientConfig CLIENT;
    public static final ModConfigSpec SERVER_SPEC;
    public static final ServerConfig SERVER;

    static {
        final Pair<ClientConfig, ModConfigSpec> clientPair = new ModConfigSpec.Builder().configure(ClientConfig::new);
        CLIENT = clientPair.getLeft();
        CLIENT_SPEC = clientPair.getRight();
        final Pair<ServerConfig, ModConfigSpec> serverPair = new ModConfigSpec.Builder().configure(ServerConfig::new);
        SERVER = serverPair.getLeft();
        SERVER_SPEC = serverPair.getRight();
    }

    public static class ClientConfig {
        private final ModConfigSpec.IntValue speedStep;
        private final ModConfigSpec.BooleanValue needsModKey;
        public ClientConfig(ModConfigSpec.Builder builder) {
            speedStep = builder.comment("Amount to change the speed multiplier each time.")
                    .defineInRange("speedStep", 5, 1,100);
            needsModKey  = builder.comment("Is mod key needed for keybinds? (Always needed for mouse wheel)")
                    .define("needsModKey", false);
        }
        public boolean needsModKey() {
            return needsModKey.getAsBoolean();
        }
        public int speedStep() {
            return speedStep.getAsInt();
        }
    }

    public static class ServerConfig {
        private final ModConfigSpec.IntValue speedMax;
        private final ModConfigSpec.IntValue speedMin;
        public ServerConfig(ModConfigSpec.Builder builder) {
            speedMax = builder.comment("Maximum speed multiplier for player. 100 is normal speed, 200 is double speed")
                    .defineInRange("speedMax", 110, 100, 1000);
            speedMin = builder.comment("Minimum speed multiplier, 0 means not moving")
                    .defineInRange("speedMin", 25, 0, 100);
        }
        public int speedMax() {
            return speedMax.getAsInt();
        }
        public int speedMin() {
            return speedMin.getAsInt();
        }
    }



//
//
//    public static int speedMax;
//    public static int speedMin;
//    public static int speedStep;
//    public static boolean needsModKey;
//    public static int configVer;
//
//    @SubscribeEvent
//    static void onLoad(final ModConfigEvent event)
//    {
//        int targetVersion = 1;
//        configVer = CONFIG_VERSION.get();
//
//        if (configVer != targetVersion) {
//            CONFIG_VERSION.set(targetVersion);
//            SPEED_MAX.set(SPEED_MAX.getDefault());
//            SPEED_MIN.set(SPEED_MIN.getDefault());
//            SPEED_STEP.set(SPEED_STEP.getDefault());
//            CONFIG_VERSION.save();
//            SPEED_MAX.save();
//            SPEED_MIN.save();
//            SPEED_STEP.save();
//        }
//        //Server variables
//        speedMax = SPEED_MAX.get();
//        speedMin = SPEED_MIN.get();
//        //Client variables
//        speedStep = SPEED_STEP.get();
//        needsModKey = MOD_KEY.get();
//    }
}
