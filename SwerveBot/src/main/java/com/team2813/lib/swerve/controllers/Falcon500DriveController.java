package com.team2813.lib.swerve.controllers;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.swervedrivespecialties.swervelib.Mk4ModuleConfiguration;
import com.swervedrivespecialties.swervelib.ModuleConfiguration;
import com.swervedrivespecialties.swervelib.ctre.CtreUtils;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

public class Falcon500DriveController implements DriveController {
    private final TalonFX motor;
    private final double sensorVelocityCoefficient;

    private SimpleMotorFeedforward feedforward;


    public Falcon500DriveController(int id, ModuleConfiguration moduleConfiguration, Mk4ModuleConfiguration mk4Configuration) {
        TalonFXConfiguration motorConfiguration = new TalonFXConfiguration();

        double sensorPositionCoefficient = Math.PI * moduleConfiguration.getWheelDiameter() * moduleConfiguration.getDriveReduction() / 2048;
        sensorVelocityCoefficient = sensorPositionCoefficient * 10;

        motorConfiguration.voltageCompSaturation = mk4Configuration.getNominalVoltage();

        motorConfiguration.supplyCurrLimit.currentLimit = mk4Configuration.getDriveCurrentLimit();
        motorConfiguration.supplyCurrLimit.enable = true;

        motor = new TalonFX(id);
        CtreUtils.checkCtreError(motor.configAllSettings(motorConfiguration), "Failed to configure Falcon 500");

        motor.enableVoltageCompensation(true);

        motor.setNeutralMode(NeutralMode.Brake);

        motor.setInverted(moduleConfiguration.isDriveInverted() ? TalonFXInvertType.Clockwise : TalonFXInvertType.CounterClockwise);
        motor.setSensorPhase(true);

        CtreUtils.checkCtreError(
                motor.setStatusFramePeriod(StatusFrameEnhanced.Status_1_General, 250, 250),
                "Failed to configure Falcon status frame period"
        );
    }

    @Override
    public Falcon500DriveController withPidConstants(double proportional, double integral, double derivative) {
        motor.config_kP(0, proportional);
        motor.config_kI(0, integral);
        motor.config_kD(0, derivative);

        return this;
    }

    @Override
    public Falcon500DriveController withFeedforward(SimpleMotorFeedforward feedforward) {
        this.feedforward = feedforward;
        return this;
    }

    @Override
    public boolean hasFeedForward() {
        return feedforward != null;
    }

    // velocity in m/s
    @Override
    public void setReferenceVelocity(double velocity) {
        double velocityRawUnits = velocity / sensorVelocityCoefficient; // convert from m/s to ticks/100ms

        if (hasFeedForward()) {
            motor.set(TalonFXControlMode.Velocity, velocityRawUnits, DemandType.ArbitraryFeedForward, feedforward.calculate(velocity));
        }
        else {
            motor.set(TalonFXControlMode.Velocity, velocityRawUnits);
        }
    }

    @Override
    public double getStateVelocity() {
        return motor.getSelectedSensorVelocity() * sensorVelocityCoefficient;
    }
}
