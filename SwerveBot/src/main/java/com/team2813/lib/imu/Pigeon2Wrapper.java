package com.team2813.lib.imu;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.sensors.Pigeon2;
import com.ctre.phoenix.sensors.Pigeon2Configuration;
import com.ctre.phoenix.sensors.PigeonIMU_StatusFrame;

public class Pigeon2Wrapper extends Pigeon2 {
    private AxisDirection forward;
    private AxisDirection up;

    private double initYaw = 0;
    private double initPitch = 0;
    private double initRoll = 0;

    private double currentHeading = 0;

    /**
     * Constructor
     * @param deviceNumber [0,62]
     * @param canbus Name of the CANbus; can be a SocketCAN interface (on Linux),
     *               or a CANivore device name or serial number
     */
    public Pigeon2Wrapper(int deviceNumber, String canbus) {
        super(deviceNumber, canbus);

        configAllSettings(new Pigeon2Configuration());
        setStatusFramePeriod(PigeonIMU_StatusFrame.CondStatus_9_SixDeg_YPR, 10);
    }

    /**
     * Constructor
     * @param deviceNumber [0,62]
     */
    public Pigeon2Wrapper(int deviceNumber) {
        super(deviceNumber);

        configAllSettings(new Pigeon2Configuration());
        setStatusFramePeriod(PigeonIMU_StatusFrame.CondStatus_9_SixDeg_YPR, 10);
    }

    public double getHeading() {
        return getYaw();
    }

    public void setHeading(double angle) {
        setYaw(angle);
        setAccumZAngle(0);

        currentHeading = angle;
    }

    @Override
    public ErrorCode configMountPoseYaw(double yaw) {
        initYaw = yaw;
        return super.configMountPoseYaw(yaw);
    }

    @Override
    public ErrorCode configMountPosePitch(double pitch) {
        initPitch = pitch;
        return super.configMountPosePitch(pitch);
    }

    @Override
    public ErrorCode configMountPoseRoll(double roll) {
        initRoll = roll;
        return super.configMountPoseRoll(roll);
    }

    @Override
    public ErrorCode configMountPose(double yaw, double pitch, double roll) {
        initYaw = yaw;
        initPitch = pitch;
        initRoll = roll;

        return super.configMountPose(yaw, pitch, roll);
    }

    @Override
    public ErrorCode configMountPose(AxisDirection forward, AxisDirection up) {
        this.forward = forward;
        this.up = up;

        return super.configMountPose(forward, up);
    }

    public void periodicResetCheck() {
        if (!hasResetOccurred()) {
            currentHeading = getHeading();
        }
        else {
            Pigeon2Configuration config = new Pigeon2Configuration();

            if (initYaw != 0) {
                config.MountPoseYaw = initYaw;
            }
            if (initPitch != 0) {
                config.MountPosePitch = initPitch;
            }
            if (initRoll != 0) {
                config.MountPoseRoll = initRoll;
            }

            configAllSettings(config);

            if ((forward != null) && (up != null)) {
                super.configMountPose(forward, up);
            }

            setHeading(currentHeading);
        }
    }
}
