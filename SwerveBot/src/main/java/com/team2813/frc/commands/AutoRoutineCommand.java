package com.team2813.frc.commands;

import com.team2813.frc.subsystems.Drive;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoRoutineCommand extends SequentialCommandGroup {

    private final Drive driveSubsystem;
    private final Command[] commands;

    private Pose2d initialPose;

    // Use if first Command that makes robot move is a FollowCommand
    public AutoRoutineCommand(Drive driveSubsystem, Command... commands) {
        super(commands);

        this.driveSubsystem = driveSubsystem;
        this.commands = commands;
    }

    // If the first Command that makes the robot move is not a FollowCommand or if there are no FollowCommands, use this
    public AutoRoutineCommand(Drive driveSubsystem, Pose2d initialPose, Command... commands) {
        super(commands);

        this.driveSubsystem = driveSubsystem;
        this.commands = commands;
        this.initialPose = initialPose;
    }

    @Override
    public void initialize() {
        super.initialize();

        if (initialPose != null) {
            driveSubsystem.initAutonomous(initialPose);
        }
        else {
            for (Command command : commands) {
                if (command instanceof FollowCommand) {
                    FollowCommand firstFollowCommand = (FollowCommand) command;
                    driveSubsystem.initAutonomous(firstFollowCommand.getTrajectory().getInitialPose());
                    return;
                }
            }
        }
    }
}
