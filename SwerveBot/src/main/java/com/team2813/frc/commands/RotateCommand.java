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
    private final Consumer<SwerveModuleState[]> swerveModuleStatesConsumer;

    // parameters taken from the thetaController in FollowCommand.java
    private static final ProfiledPIDController thetaController = new ProfiledPIDController(
            1,
            0,
            0,
            new TrapezoidProfile.Constraints(Drive.MAX_ANGULAR_VELOCITY, Drive.MAX_ANGULAR_ACCELERATION)
    );

    private final double setpoint;

    public RotateCommand(double degreesToRotateBy, Drive driveSubsystem) {
        this.driveSubsystem = driveSubsystem;

        setpoint = driveSubsystem.getRotation().getRadians() + Math.toRadians(degreesToRotateBy);
        swerveModuleStatesConsumer = getSwerveModuleStatesConsumer(driveSubsystem);
        addRequirements(driveSubsystem);
    }

    private static Consumer<SwerveModuleState[]> getSwerveModuleStatesConsumer(Drive driveSubsystem) {
        return new Consumer<SwerveModuleState[]>() {
            @Override
            public void accept(SwerveModuleState[] swerveModuleStates) {
                driveSubsystem.drive(swerveModuleStates);
            }
        };
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

        swerveModuleStatesConsumer.accept(targetModuleStates);
    }

    @Override
    public boolean isFinished() {
        //return Math.abs(setpoint - driveSubsystem.getRotation().getRadians()) <= Math.toRadians(5);
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        ChassisSpeeds targetChassisSpeeds = new ChassisSpeeds(0, 0, 0);
        SwerveModuleState[] targetModuleStates = driveSubsystem.getKinematics().toSwerveModuleStates(targetChassisSpeeds);

        swerveModuleStatesConsumer.accept(targetModuleStates);
    }
}
