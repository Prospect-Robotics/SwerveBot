package com.team2813.lib.swerve;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.swervedrivespecialties.swervelib.Mk4ModuleConfiguration;
import com.swervedrivespecialties.swervelib.ModuleConfiguration;
import com.swervedrivespecialties.swervelib.ctre.CtreUtils;
import com.team2813.frc.util.Units2813;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

public class Falcon500DriveController implements DriveController {
    private final TalonFX motor;
    private final double sensorVelocityCoefficient;
    private final ModuleConfiguration moduleConfiguration;

    private SimpleMotorFeedforward feedforward;


    public Falcon500DriveController(int id, ModuleConfiguration moduleConfiguration, Mk4ModuleConfiguration mk4Configuration) {
        this.moduleConfiguration = moduleConfiguration;

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

    /**
     * Constructor
     * @param canbus Name of the CANbus; can be a SocketCAN interface (on Linux),
     *               or a CANivore device name or serial number
     */
    public Falcon500DriveController(int id, String canbus, ModuleConfiguration moduleConfiguration, Mk4ModuleConfiguration mk4Configuration) {
        this.moduleConfiguration = moduleConfiguration;

        TalonFXConfiguration motorConfiguration = new TalonFXConfiguration();

        double sensorPositionCoefficient = Math.PI * moduleConfiguration.getWheelDiameter() * moduleConfiguration.getDriveReduction() / 2048;
        sensorVelocityCoefficient = sensorPositionCoefficient * 10;

        motorConfiguration.voltageCompSaturation = mk4Configuration.getNominalVoltage();

        motorConfiguration.supplyCurrLimit.currentLimit = mk4Configuration.getDriveCurrentLimit();
        motorConfiguration.supplyCurrLimit.enable = true;

        motor = new TalonFX(id, canbus);
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
        final double WHEEL_CIRCUMFERENCE = moduleConfiguration.getWheelDiameter() * Math.PI;

        double velocityRawUnits = Units2813.wheelSpeedToMotorRpm(velocity, WHEEL_CIRCUMFERENCE, moduleConfiguration.getDriveReduction()); // convert from wheel speed (m/s) to motor velocity (rpm)
        velocityRawUnits = Units2813.motorRevsToTicks(velocityRawUnits / 60 / 10, 2048); // convert from rpm to ticks/100ms

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
