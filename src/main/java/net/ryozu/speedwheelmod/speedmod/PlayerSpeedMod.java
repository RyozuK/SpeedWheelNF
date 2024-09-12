package net.ryozu.speedwheelmod.speedmod;

import net.minecraft.nbt.CompoundTag;
import net.ryozu.speedwheelmod.Config;

public class PlayerSpeedMod {

    public static double bound(double value, double min, double max) {
        return Math.min(Math.max(min, value), max);
    }

    public static double getRealSpeed(int percent) {
        //Speed modifier multiplier treats 0.0 as normal speed, -1.0 as not moving and 1.0 as double speed
        //Therefore 100 as 100% means we do perc / 100 - 1.0
        //100% -> 0.0
        //50% -> -0.5
        //200% -> 1.0
        return ((double) percent / 100) - 1.0;
    }

    public static int speedUp(int speed, int step) {
        speed = (int)bound(speed + step, Config.SERVER.speedMin(), Config.SERVER.speedMax());
        return speed;
    }

    public static int speedDown(int speed, int step) {
        speed = (int)bound(speed - step, Config.SERVER.speedMin(), Config.SERVER.speedMax());
        return speed;
    }
}
