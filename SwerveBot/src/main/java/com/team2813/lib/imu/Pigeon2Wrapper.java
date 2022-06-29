package com.team2813.lib.imu;

import com.ctre.phoenix.sensors.Pigeon2;
import com.ctre.phoenix.sensors.Pigeon2Configuration;
import com.ctre.phoenix.sensors.PigeonIMU_StatusFrame;

public class Pigeon2Wrapper extends Pigeon2 {

    /**
     * Constructor
     * @param deviceNumber [0,62]
     * @param canbus Name of the CANbus; can be a SocketCAN interface (on Linux),
     *               or a CANivore device name or serial number
     */
    public Pigeon2Wrapper(int deviceNumber, String canbus) {
        super(deviceNumber, canbus);

        configAllSettings(new Pigeon2Configuration());
        setStatusFramePeriod(PigeonIMU_StatusFrame.CondStatus_9_SixDeg_YPR, 20);
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
    }
}
