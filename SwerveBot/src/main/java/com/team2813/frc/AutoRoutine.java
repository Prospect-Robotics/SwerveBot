package com.team2813.frc;

import com.team2813.frc.commands.AutoRoutineCommand;
import com.team2813.frc.commands.FollowCommand;
import com.team2813.frc.subsystems.Drive;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.Command;

import java.util.function.Consumer;

public enum AutoRoutine {
    STRAIGHT_TEST("Straight Test", new AutoRoutineCommand(
            Autonomous.getDriveSubsystem(), new FollowCommand("Straight_Test", Autonomous.outputModuleStates,
            Autonomous.getDriveSubsystem())));

    // Use in FollowCommands and RotateCommands
    private static final Consumer<SwerveModuleState[]> outputModuleStates = new Consumer<SwerveModuleState[]>() {
        @Override
        public void accept(SwerveModuleState[] swerveModuleStates) {
            Autonomous.getDriveSubsystem().drive(swerveModuleStates);
        }
    };

    static Drive driveSubsystem = Autonomous.getDriveSubsystem();

    private final String name;
    private final Command command;

    AutoRoutine(String name, Command command) {
        this.name = name;
        this.command = command;
    }

    public String getName() {
        return name;
    }

    Command getCommand() {
        return command;
    }
}
