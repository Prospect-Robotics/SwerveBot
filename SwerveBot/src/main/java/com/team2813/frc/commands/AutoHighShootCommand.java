package com.team2813.frc.commands;

import com.team2813.frc.subsystems.Magazine;
import com.team2813.frc.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AutoHighShootCommand extends SequentialCommandGroup {

    public AutoHighShootCommand(Shooter shooterSubsystem, Magazine magazineSubsystem) {
        super(
                new InstantCommand(shooterSubsystem::highShoot, shooterSubsystem),
                new WaitCommand(0.25),
                new InstantCommand(magazineSubsystem::shoot, magazineSubsystem),
                new WaitCommand(2),
                new ParallelCommandGroup(
                        new InstantCommand(shooterSubsystem::disable, shooterSubsystem),
                        new InstantCommand(magazineSubsystem::disable, magazineSubsystem)
                )
        );
    }
}
