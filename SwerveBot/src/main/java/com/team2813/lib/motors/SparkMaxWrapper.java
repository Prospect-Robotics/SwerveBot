package com.team2813.lib.motors;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import java.util.ArrayList;
import java.util.List;

public class SparkMaxWrapper extends CANSparkMax implements Motor {
    List<CANSparkMax> followers = new ArrayList<>();
    private RelativeEncoder encoder;
    private SparkMaxPIDController pidController;

    /**
     * Create a new object to control a SPARK MAX motor Controller
     *
     * @param deviceId The device ID.
     * @param type     The motor type connected to the controller. Brushless motor wires must be connected
     *                 to their matching colors and the hall sensor must be plugged in. Brushed motors must be
     *                 connected to the Red and Black terminals only.
     * @param inverted Whether the motor is inverted
     */
    public SparkMaxWrapper(int deviceId, MotorType type, boolean inverted) {
        super(deviceId, type);
        encoder = getEncoder();
        pidController = getPIDController();

        restoreFactoryDefaults();

        enableVoltageCompensation(12);
        setClosedLoopRampRate(0);
        setPeriodicFramePeriod(PeriodicFrame.kStatus2, 20);
        setSmartCurrentLimit(40);

        setInverted(inverted);
    }

    @Override
    public void set(ControlMode controlMode, double demand) {
        pidController.setReference(demand, controlMode.getSparkMode());
    }

    @Override
    public void set(ControlMode controlMode, double demand, double feedForward) {
        pidController.setReference(demand, controlMode.getSparkMode(), 0, feedForward);
    }

    @Override
    public double getEncoderPosition() {
        return encoder.getPosition();
    }

    @Override
    public void setEncoderPosition(double position) {
        encoder.setPosition(position);
    }

    @Override
    public double getVelocity() {
        return encoder.getVelocity();
    }

    @Override
    public void configPIDF(int slot, double p, double i, double d, double f) {
        pidController.setP(p, slot);
        pidController.setI(i, slot);
        pidController.setD(d, slot);
        pidController.setFF(f, slot);
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

    public void configMotionMagic(int slot, double minVelocity, double maxVelocity, double maxAcceleration) {
        pidController.setSmartMotionMinOutputVelocity(minVelocity, slot);
        pidController.setSmartMotionMaxVelocity(maxVelocity, slot);
        pidController.setSmartMotionMaxAccel(maxAcceleration, slot);
    }

    public void configMotionMagic(double minVelocity, double maxVelocity, double maxAcceleration) {
        configMotionMagic(0, minVelocity, maxVelocity, maxAcceleration);
    }

    public void configMotionMagic(int slot, double maxVelocity, double maxAcceleration) {
        configMotionMagic(slot, 0, maxVelocity, maxAcceleration);
    }

    @Override
    public void configMotionMagic(double maxVelocity, double maxAcceleration) {
        configMotionMagic(0, 0, maxVelocity, maxAcceleration);
    }

    public void addFollower(int deviceId, MotorType type, boolean inverted) {
        CANSparkMax follower = new CANSparkMax(deviceId, type);
        follower.follow(this, inverted);
        followers.add(follower); // add to follower list so CANSparkMax follower object is preserved
    }
}

