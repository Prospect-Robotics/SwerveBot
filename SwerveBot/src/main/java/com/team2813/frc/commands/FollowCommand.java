package com.team2813.frc.commands;

import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.commands.PPSwerveControllerCommand;
import com.team2813.frc.subsystems.Drive;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.function.Consumer;

import static com.team2813.frc.Constants.*;
import static com.team2813.frc.Robot.ROBOT_CONTAINER;

/**
 * Command to follow a given trajectory
 */
public class FollowCommand extends PPSwerveControllerCommand {

    private static final PIDController xController = new PIDController(2.5, 0, 0);
    private static final PIDController yController = new PIDController(2.5, 0, 0);
    private static final ProfiledPIDController thetaController = new ProfiledPIDController(
            0,
            0,
            0,
            new TrapezoidProfile.Constraints(Drive.MAX_ANGULAR_VELOCITY, Drive.MAX_ANGULAR_ACCELERATION)
    );
    // P: 4.5

    private final Drive driveSubsystem;
    private final Consumer<SwerveModuleState[]> swerveModuleStatesConsumer;
    private final PathPlannerTrajectory trajectory;

    public FollowCommand(String trajectoryName, Drive driveSubsystem) {
        super(
                PathPlanner.loadPath(trajectoryName, AUTO_MAX_VEL, AUTO_MAX_ACCEL),
                driveSubsystem::getPose,
                driveSubsystem.getKinematics(),
                xController,
                yController,
                thetaController,
                getSwerveModuleStatesConsumer(driveSubsystem),
                driveSubsystem
        );

        this.driveSubsystem = driveSubsystem;
        swerveModuleStatesConsumer = getSwerveModuleStatesConsumer(driveSubsystem);

        trajectory = PathPlanner.loadPath(trajectoryName, AUTO_MAX_VEL, AUTO_MAX_ACCEL);
    }

    public FollowCommand(String trajectoryName, boolean reversed, Drive driveSubsystem) {
        super(
                PathPlanner.loadPath(trajectoryName, AUTO_MAX_VEL, AUTO_MAX_ACCEL, reversed),
                driveSubsystem::getPose,
                driveSubsystem.getKinematics(),
                xController,
                yController,
                thetaController,
                getSwerveModuleStatesConsumer(driveSubsystem),
                driveSubsystem
        );

        this.driveSubsystem = driveSubsystem;
        swerveModuleStatesConsumer = getSwerveModuleStatesConsumer(driveSubsystem);

        trajectory = PathPlanner.loadPath(trajectoryName, AUTO_MAX_VEL, AUTO_MAX_ACCEL, reversed);
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
        super.initialize();
        SmartDashboard.putString("Goal Pose", trajectory.getEndState().poseMeters.toString());
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        ChassisSpeeds targetChassisSpeeds = new ChassisSpeeds(0, 0, 0);
        SwerveModuleState[] targetModuleStates = driveSubsystem.getKinematics().toSwerveModuleStates(targetChassisSpeeds);

        swerveModuleStatesConsumer.accept(targetModuleStates);
    }
}
