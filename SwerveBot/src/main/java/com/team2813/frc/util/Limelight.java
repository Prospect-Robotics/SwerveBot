package com.team2813.frc.util;

import com.team2813.lib.util.LimelightValues;

public class Limelight {
    private LimelightValues values = new LimelightValues();
    private static final double MOUNT_ANGLE = 0; // degrees
    private static final double MOUNT_HEIGHT = 0; // inches
    private static final double TARGET_HEIGHT = 0; // inches

    private Limelight() {
        setStream(0);
    }

    private static Limelight instance = new Limelight();

    public static Limelight getInstance() {
        return instance;
    }

    public LimelightValues getValues() {
        return values;
    }

    public void setLights(boolean enable) {
        values.getLedMode().setNumber(enable ? 0 : 1);
    }

    public void setStream(int stream) {
        values.getStream().setNumber(stream);
    }
}
