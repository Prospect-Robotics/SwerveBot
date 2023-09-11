package com.team2813.frc.commands;

import com.team2813.frc.subsystems.Intake;
import com.team2813.frc.subsystems.Magazine;
import com.team2813.frc.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AutoIntakeCommand extends SequentialCommandGroup {

    public AutoIntakeCommand(Intake intakeSubsystem, Magazine magSubsystem, Shooter shooterSubsystem) {
        super(
                new InstantCommand(intakeSubsystem::deployIntake, intakeSubsystem),
                new WaitCommand(0.4),
                new ParallelCommandGroup(
                        new InstantCommand(intakeSubsystem::intake, intakeSubsystem),
                        new InstantCommand(magSubsystem::intake, magSubsystem),
                        new InstantCommand(shooterSubsystem::kick, shooterSubsystem)
                )
        );
    }
}
