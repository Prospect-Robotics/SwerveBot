package com.team2813.lib.swerve.controllers.steer;

import com.swervedrivespecialties.swervelib.ctre.CanCoderAbsoluteConfiguration;

public class Falcon500SteerConfiguration {
    private final int motorPort;
    private final String canbus;
    private final CanCoderAbsoluteConfiguration encoderConfiguration;

    public Falcon500SteerConfiguration(int motorPort, String canbus, CanCoderAbsoluteConfiguration encoderConfiguration) {
        this.motorPort = motorPort;
        this.canbus = canbus;
        this.encoderConfiguration = encoderConfiguration;
    }

    public int getMotorPort() {
        return motorPort;
    }

    public String getCanbus() {
        return canbus;
    }

    public CanCoderAbsoluteConfiguration getEncoderConfiguration() {
        return encoderConfiguration;
    }
}
