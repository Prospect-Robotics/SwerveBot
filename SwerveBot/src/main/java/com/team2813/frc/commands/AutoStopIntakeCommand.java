package com.team2813.frc.commands;

import com.team2813.frc.subsystems.Intake;
import com.team2813.frc.subsystems.Magazine;

import com.team2813.frc.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoStopIntakeCommand extends SequentialCommandGroup {

    public AutoStopIntakeCommand(Intake intakeSubsystem, Magazine magSubsystem, Shooter shooterSubsystem) {
        super(
                new ParallelCommandGroup(
                        // Stops the intake
                        new InstantCommand(intakeSubsystem::stopIntake, intakeSubsystem),

                        // Stops the mag
                        new InstantCommand(magSubsystem::disable, magSubsystem),

                        // Stops the shooter
                        new InstantCommand(shooterSubsystem::disable, shooterSubsystem)),

                new InstantCommand(intakeSubsystem::retractIntake, intakeSubsystem));
    }
}
