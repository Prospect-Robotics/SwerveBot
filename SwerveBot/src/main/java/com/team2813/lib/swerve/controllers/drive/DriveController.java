package com.team2813.lib.swerve.controllers.drive;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;

public interface DriveController {
    public DriveController withPidConstants(double proportional, double integral, double derivative);
    public DriveController withFeedforward(SimpleMotorFeedforward feedforward);
    public boolean hasFeedForward();

    public void setReferenceVelocity(double velocity);
    public double getStateVelocity();

    public void resetEncoder();
}
