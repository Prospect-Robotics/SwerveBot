package com.team2813.frc.commands;

import com.team2813.frc.subsystems.Intake;
import com.team2813.frc.subsystems.Magazine;

import com.team2813.frc.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.*;

public class AutoStopIntakeCommand extends SequentialCommandGroup {

    public AutoStopIntakeCommand(Intake intakeSubsystem, Magazine magSubsystem, Shooter shooterSubsystem, boolean shuffle) {
        super(
                new ParallelCommandGroup(
                        new InstantCommand(intakeSubsystem::stopIntake, intakeSubsystem),
                        new InstantCommand(magSubsystem::disable, magSubsystem),
                        new InstantCommand(shooterSubsystem::disable, shooterSubsystem)
                ),
                new InstantCommand(intakeSubsystem::retractIntake, intakeSubsystem),
                new InstantCommand(() -> {
                    if (shuffle) {
                        Command shuffleCommand = new SequentialCommandGroup(
                                new WaitCommand(0.4),
                                new InstantCommand(magSubsystem::shuffle, magSubsystem),
                                new WaitCommand(0.5),
                                new InstantCommand(magSubsystem::disable, magSubsystem)
                        );
                        shuffleCommand.schedule();
                    }
                })
        );
    }
}
