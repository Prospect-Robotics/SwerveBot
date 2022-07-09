package com.team2813.frc.commands;

import com.team2813.frc.subsystems.Drive;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.Consumer;

/**
 * Command to rotate by a given number of degrees.
 * Works like a unit circle:
 *      Positive # of degrees makes the robot turn counter-clockwise
 *      Negative # of degrees makes the robot turn clockwise
 */
public class RotateCommand extends CommandBase {

    private final Drive driveSubsystem;
    private final Consumer<SwerveModuleState[]> outputModuleStates;
    private final ProfiledPIDController thetaController;

    private final double degreesToRotateBy;
    private final double setpoint;

    public RotateCommand(double degreesToRotateBy, Consumer<SwerveModuleState[]> outputModuleStates, Drive driveSubsystem) {
        this.degreesToRotateBy = degreesToRotateBy;
        this.outputModuleStates = outputModuleStates;
        this.driveSubsystem = driveSubsystem;

        setpoint = driveSubsystem.getRotation().getRadians() + Math.toRadians(degreesToRotateBy);

        // parameters taken from the thetaController in FollowCommand.java
        thetaController = new ProfiledPIDController(
                0,
                0,
                0,
                new TrapezoidProfile.Constraints(0, 0)
        );

        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        thetaController.reset(driveSubsystem.getRotation().getRadians());
    }

    @Override
    public void execute() {
        double angularVelocity = thetaController.calculate(driveSubsystem.getRotation().getRadians(), setpoint);
        ChassisSpeeds targetChassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(
                0, 0, angularVelocity, driveSubsystem.getRotation());
        SwerveModuleState[] targetModuleStates = driveSubsystem.getKinematics().toSwerveModuleStates(targetChassisSpeeds);

        outputModuleStates.accept(targetModuleStates);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(setpoint - driveSubsystem.getRotation().getRadians()) <= Math.toRadians(5);
    }

    @Override
    public void end(boolean interrupted) {
        ChassisSpeeds targetChassisSpeeds = new ChassisSpeeds(0, 0, 0);
        SwerveModuleState[] targetModuleStates = driveSubsystem.getKinematics().toSwerveModuleStates(targetChassisSpeeds);

        outputModuleStates.accept(targetModuleStates);
    }
}
