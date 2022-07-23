package com.team2813.frc.commands;

import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.commands.PPSwerveControllerCommand;
import com.team2813.frc.subsystems.Drive;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.trajectory.TrapezoidProfile;

import java.util.function.Consumer;

import static com.team2813.frc.Constants.*;

/**
 * Command to follow a given trajectory
 */
public class FollowCommand extends PPSwerveControllerCommand {

    private static final PIDController xController = new PIDController(2.0, 0.01, 0);
    private static final PIDController yController = new PIDController(2.0, 0.01, 0);
    private static final ProfiledPIDController thetaController = new ProfiledPIDController(
            4.5,
            0,
            0,
            new TrapezoidProfile.Constraints(Drive.MAX_ANGULAR_VELOCITY, Drive.MAX_ANGULAR_ACCELERATION)
    );

    public FollowCommand(String trajectoryName, Consumer<SwerveModuleState[]> outputModuleStates, Drive driveSubsystem) {
        super(
                PathPlanner.loadPath(trajectoryName, AUTO_MAX_VEL, AUTO_MAX_ACCEL),
                driveSubsystem::getPose,
                driveSubsystem.getKinematics(),
                xController,
                yController,
                thetaController,
                outputModuleStates,
                driveSubsystem
        );
    }

    public FollowCommand(String trajectoryName, boolean reversed, Consumer<SwerveModuleState[]> outputModuleStates, Drive driveSubsystem) {
        super(
                PathPlanner.loadPath(trajectoryName, AUTO_MAX_VEL, AUTO_MAX_ACCEL, reversed),
                driveSubsystem::getPose,
                driveSubsystem.getKinematics(),
                xController,
                yController,
                thetaController,
                outputModuleStates,
                driveSubsystem
        );
    }
}
