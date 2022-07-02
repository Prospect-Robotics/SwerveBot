package com.team2813.lib.swerve.controllers;

import com.swervedrivespecialties.swervelib.SteerController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwerveModule {
    private final DriveController driveController;
    private final SteerController steerController;

    public SwerveModule(DriveController driveController, SteerController steerController) {
        this.driveController = driveController;
        this.steerController = steerController;
    }

    public double getDriveVelocity() {
        return driveController.getStateVelocity();
    }

    public double getSteerAngle() {
        return steerController.getStateAngle();
    }

    public void set(double driveVelocity, double steerAngle) {
        steerAngle %= (2.0 * Math.PI);
        if (steerAngle < 0.0) {
            steerAngle += 2.0 * Math.PI;
        }

        double difference = steerAngle - getSteerAngle();
        // Change the target angle so the difference is in the range [-pi, pi) instead of [0, 2pi)
        if (difference >= Math.PI) {
            steerAngle -= 2.0 * Math.PI;
        } else if (difference < -Math.PI) {
            steerAngle += 2.0 * Math.PI;
        }
        difference = steerAngle - getSteerAngle(); // Recalculate difference

        // If the difference is greater than 90 deg or less than -90 deg the drive can be inverted so the total
        // movement of the module is less than 90 deg
        if (difference > Math.PI / 2.0 || difference < -Math.PI / 2.0) {
            // Only need to add 180 deg here because the target angle will be put back into the range [0, 2pi)
            steerAngle += Math.PI;
            driveVelocity *= -1.0;
        }

        // Put the target angle back into the range [0, 2pi)
        steerAngle %= (2.0 * Math.PI);
        if (steerAngle < 0.0) {
            steerAngle += 2.0 * Math.PI;
        }

        driveController.setReferenceVelocity(driveVelocity);
        steerController.setReferenceAngle(steerAngle);

        SmartDashboard.putNumber("Target Velocity", driveVelocity);

        double currentVelocity = getDriveVelocity();
        int targetVelocitySign = (int) (Math.abs(driveVelocity) / driveVelocity);
        int currentVelocitySign = (int) (Math.abs(currentVelocity) / currentVelocity);

        if (targetVelocitySign != currentVelocitySign) {
            currentVelocity *= -1;
        }
        SmartDashboard.putNumber("Current Velocity", currentVelocity);
    }
}
