package com.team2813.frc.commands;

import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.commands.PPSwerveControllerCommand;
import com.team2813.frc.subsystems.Drive;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.trajectory.TrapezoidProfile;

import java.util.function.Consumer;

/**
 * Command to follow a given trajectory
 */
public class FollowCommand extends PPSwerveControllerCommand {

    private static final PIDController xController = new PIDController(0, 0, 0);
    private static final PIDController yController = new PIDController(0, 0, 0);
    private static final ProfiledPIDController thetaController = new ProfiledPIDController(
            0,
            0,
            0,
            new TrapezoidProfile.Constraints(0, 0)
    );

    private final PathPlannerTrajectory trajectory;

    public FollowCommand(String trajectoryName, Consumer<SwerveModuleState[]> outputModuleStates, Drive driveSubsystem) {
        super(
                PathPlanner.loadPath(trajectoryName, 0.75, 2),
                driveSubsystem::getPose,
                driveSubsystem.getKinematics(),
                xController,
                yController,
                thetaController,
                outputModuleStates,
                driveSubsystem
        );

        trajectory = PathPlanner.loadPath(trajectoryName, 0.75, 2);
    }

    public FollowCommand(String trajectoryName, boolean reversed, Consumer<SwerveModuleState[]> outputModuleStates, Drive driveSubsystem) {
        super(
                PathPlanner.loadPath(trajectoryName, 0.75, 2, reversed),
                driveSubsystem::getPose,
                driveSubsystem.getKinematics(),
                xController,
                yController,
                thetaController,
                outputModuleStates,
                driveSubsystem
        );

        trajectory = PathPlanner.loadPath(trajectoryName, 0.75, 2, reversed);
    }

    public PathPlannerTrajectory getTrajectory() {
        return trajectory;
    }
}
