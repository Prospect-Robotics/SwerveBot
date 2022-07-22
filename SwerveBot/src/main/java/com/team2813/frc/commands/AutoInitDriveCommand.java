package com.team2813.frc.commands;

import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.team2813.frc.Autonomous;
import com.team2813.frc.subsystems.Drive;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Use before the first FollowCommand in an AutoRoutine.
 * If there are no FollowCommands, put this as the first
 * command in the AutoRoutine, using the last constructor.
 */
public class AutoInitDriveCommand extends InstantCommand {

    public AutoInitDriveCommand(String trajectoryName, Drive driveSubsystem) {
        super(() -> {
                    PathPlannerTrajectory trajectory = PathPlanner.loadPath(trajectoryName, Autonomous.MAX_VEL, Autonomous.MAX_ACCEL);
                    driveSubsystem.initAutonomous(trajectory.getInitialPose());
                }, driveSubsystem);
    }

    public AutoInitDriveCommand(String trajectoryName, boolean reversed, Drive driveSubsystem) {
        super(() -> {
                    PathPlannerTrajectory trajectory = PathPlanner.loadPath(trajectoryName, Autonomous.MAX_VEL, Autonomous.MAX_ACCEL, reversed);
                    driveSubsystem.initAutonomous(trajectory.getInitialPose());
                }, driveSubsystem);
    }

    public AutoInitDriveCommand(Rotation2d initialRotation, Drive driveSubsystem) {
        super(() -> driveSubsystem.initAutonomous(initialRotation), driveSubsystem);
    }
}
