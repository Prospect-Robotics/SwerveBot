package com.team2813.frc.commands;

import com.team2813.frc.subsystems.Drive;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoRoutineCommand extends SequentialCommandGroup {

    private final Drive driveSubsystem;
    private final Command[] commands;

    private Pose2d initialPose;

    public AutoRoutineCommand(Drive driveSubsystem, Command... commands) {
        super(commands);

        this.driveSubsystem = driveSubsystem;
        this.commands = commands;
    }

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
            FollowCommand firstFollowCommand = null;
            for (Command command : commands) {
                if (command instanceof FollowCommand) {
                    firstFollowCommand = (FollowCommand) command;
                    break;
                }
            }

            if (firstFollowCommand != null) {
                driveSubsystem.initAutonomous(firstFollowCommand.getTrajectory().getInitialPose());
            }
            else {
                System.out.println("No follow commands");
            }
        }
    }
}
