package com.team2813.frc.commands;

import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.commands.PPSwerveControllerCommand;
import com.team2813.frc.subsystems.Drive;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.trajectory.TrapezoidProfile;

import java.util.function.Consumer;

public class FollowCommand extends PPSwerveControllerCommand {

    private static PIDController xController = new PIDController(0, 0, 0);
    private static PIDController yController = new PIDController(0, 0, 0);
    private static ProfiledPIDController thetaController = new ProfiledPIDController(
            0,
            0,
            0,
            new TrapezoidProfile.Constraints(0, 0)
    );

    public FollowCommand(PathPlannerTrajectory trajectory, Consumer<SwerveModuleState[]> outputModuleStates, Drive driveSubsystem) {
        super(
                trajectory,
                driveSubsystem::getPose,
                driveSubsystem.kinematics,
                xController,
                yController,
                thetaController,
                outputModuleStates,
                driveSubsystem
        );
    }
}
