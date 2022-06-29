package com.team2813.lib.swerve;

import com.revrobotics.*;
import com.swervedrivespecialties.swervelib.Mk4ModuleConfiguration;
import com.swervedrivespecialties.swervelib.ModuleConfiguration;
import com.swervedrivespecialties.swervelib.rev.RevUtils;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

public class NeoDriveController implements DriveController {
    private final CANSparkMax motor;
    private final RelativeEncoder encoder;
    private final SparkMaxPIDController pidController;
    private final double velocityConversionFactor;

    private SimpleMotorFeedforward feedforward;

    public NeoDriveController(int id, ModuleConfiguration moduleConfiguration, Mk4ModuleConfiguration mk4Configuration) {
        motor = new CANSparkMax(id, CANSparkMaxLowLevel.MotorType.kBrushless);
        motor.setInverted(moduleConfiguration.isDriveInverted());

        RevUtils.checkNeoError(motor.enableVoltageCompensation(mk4Configuration.getNominalVoltage()), "Failed to enable voltage compensation");

        RevUtils.checkNeoError(motor.setSmartCurrentLimit((int) mk4Configuration.getDriveCurrentLimit()), "Failed to set current limit for NEO");

        RevUtils.checkNeoError(motor.setPeriodicFramePeriod(CANSparkMaxLowLevel.PeriodicFrame.kStatus0, 100), "Failed to set periodic status frame 0 rate");
        RevUtils.checkNeoError(motor.setPeriodicFramePeriod(CANSparkMaxLowLevel.PeriodicFrame.kStatus1, 20), "Failed to set periodic status frame 1 rate");
        RevUtils.checkNeoError(motor.setPeriodicFramePeriod(CANSparkMaxLowLevel.PeriodicFrame.kStatus2, 20), "Failed to set periodic status frame 2 rate");

        motor.setIdleMode(CANSparkMax.IdleMode.kBrake);

        encoder = motor.getEncoder();
        double positionConversionFactor = Math.PI * moduleConfiguration.getWheelDiameter() * moduleConfiguration.getDriveReduction();
        velocityConversionFactor = positionConversionFactor / 60;
        encoder.setPositionConversionFactor(positionConversionFactor);
        encoder.setVelocityConversionFactor(velocityConversionFactor);

        pidController = motor.getPIDController();
    }

    @Override
    public DriveController withPidConstants(double proportional, double integral, double derivative) {
        pidController.setP(proportional);
        pidController.setI(integral);
        pidController.setD(derivative);

        return this;
    }

    @Override
    public DriveController withFeedforward(SimpleMotorFeedforward feedforward) {
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
        double velocityRPM = velocity / velocityConversionFactor; // convert from m/s to rpm

        if (hasFeedForward()) {
            pidController.setReference(velocityRPM, CANSparkMax.ControlType.kVelocity, 0, feedforward.calculate(velocity));
        }
        else {
            pidController.setReference(velocityRPM, CANSparkMax.ControlType.kVelocity);
        }
    }

    @Override
    public double getStateVelocity() {
        return encoder.getVelocity();
    }
}
