package com.team2813.lib.motors;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.team2813.frc.util.Units2813;
import com.team2813.lib.util.ConfigUtils;

import java.util.ArrayList;
import java.util.List;

public class TalonFXWrapper extends TalonFX implements Motor {
    private final List<TalonFX> followers = new ArrayList<>();

    /**
     * Constructor
     * @param deviceNumber [0,62]
     * @param canbus Name of the CANbus; can be a SocketCAN interface (on Linux),
     *               or a CANivore device name or serial number
     * @param invertType Invert state of the motor
     */
    public TalonFXWrapper(int deviceNumber, String canbus, TalonFXInvertType invertType) {
        super(deviceNumber, canbus);

        TalonFXConfiguration motorConfiguration = new TalonFXConfiguration();
        motorConfiguration.voltageCompSaturation = 12;
        motorConfiguration.supplyCurrLimit = new SupplyCurrentLimitConfiguration(true, 40, 40, 0.25);
        ConfigUtils.ctreConfig(() -> configAllSettings(motorConfiguration));

        enableVoltageCompensation(true);
        setInverted(invertType);
    }

    /**
     * Constructor
     * @param deviceNumber [0,62]
     * @param invertType Invert state of the motor
     */
    public TalonFXWrapper(int deviceNumber, TalonFXInvertType invertType) {
        super(deviceNumber);

        TalonFXConfiguration motorConfiguration = new TalonFXConfiguration();
        motorConfiguration.voltageCompSaturation = 12;
        motorConfiguration.supplyCurrLimit = new SupplyCurrentLimitConfiguration(true, 40, 40, 0.25);
        ConfigUtils.ctreConfig(() -> configAllSettings(motorConfiguration));

        enableVoltageCompensation(true);
        setInverted(invertType);
    }

    @Override
    public void set(ControlMode controlMode, double demand) {
        set(controlMode, demand, 0);
    }

    @Override
    public void set(ControlMode controlMode, double demand, double feedForward) {
        switch (controlMode){
            case VELOCITY:
                demand = Units2813.motorRevsToTicks(demand / 60 / 10, 2048);
                break;
            case MOTION_MAGIC:
                demand = Units2813.motorRevsToTicks(demand, 2048);
                break;
        }
        set(controlMode.getTalonMode(), demand, DemandType.ArbitraryFeedForward, feedForward);
    }

    @Override
    public double getEncoderPosition() {
        return Units2813.ticksToMotorRevs(getSelectedSensorPosition(), 2048);
    }

    @Override
    public void setEncoderPosition(double position) {
        setSelectedSensorPosition(position);
    }

    @Override
    public double getVelocity() { // returns in RPM
        return Units2813.ticksToMotorRevs(getSelectedSensorVelocity(), 2048) * 10 * 60; // from ticks/100ms to rpm
    }

    @Override
    public void configPIDF(int slot, double p, double i, double d, double f) {
        ConfigUtils.ctreConfig(() -> config_kP(slot, p));
        ConfigUtils.ctreConfig(() -> config_kI(slot, i));
        ConfigUtils.ctreConfig(() -> config_kD(slot, d));
        ConfigUtils.ctreConfig(() -> config_kF(slot, f));
    }

    @Override
    public void configPIDF(double p, double i, double d, double f) {
        configPIDF(0, p, i, d, f);
    }

    @Override
    public void configPID(int slot, double p, double i, double d) {
        configPIDF(slot, p, i, d, 0);
    }

    @Override
    public void configPID(double p, double i, double d) {
        configPIDF(0, p, i, d, 0);
    }

    @Override
    public void configMotionMagic(double maxVelocity, double maxAcceleration) {
        ConfigUtils.ctreConfig(() -> configMotionCruiseVelocity(maxVelocity));
        ConfigUtils.ctreConfig(() -> configMotionAcceleration(maxAcceleration));
    }

    public void configStatusFramePeriod(StatusFrameEnhanced frame, int periodMs) {
        ConfigUtils.ctreConfig(() -> super.setStatusFramePeriod(frame, periodMs));
    }

    public void addFollower(int deviceNumber, String canbus, TalonFXInvertType invertType) {
        TalonFX follower = new TalonFX(deviceNumber, canbus);
        follower.follow(this);
        follower.setInverted(invertType);
        followers.add(follower); // add to follower list so TalonFX follower object is preserved
    }

    public void addFollower(int deviceNumber, String canbus, FollowerType followerType) {
        TalonFX follower = new TalonFX(deviceNumber, canbus);
        follower.follow(this, followerType);
        followers.add(follower); // add to follower list so TalonFX follower object is preserved
    }

    public void addFollower(int deviceNumber, TalonFXInvertType invertType) {
        TalonFX follower = new TalonFX(deviceNumber);
        follower.follow(this);
        follower.setInverted(invertType);
        followers.add(follower); // add to follower list so TalonFX follower object is preserved
    }

    public void addFollower(int deviceNumber, FollowerType followerType) {
        TalonFX follower = new TalonFX(deviceNumber);
        follower.follow(this, followerType);
        followers.add(follower); // add to follower list so TalonFX follower object is preserved
    }
}

