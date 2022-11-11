package com.team2813.frc.commands.util;

import com.team2813.frc.subsystems.Magazine;
import com.team2813.frc.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AutoHighShootCommand extends SequentialCommandGroup {

    public AutoHighShootCommand(Shooter shooterSubsystem, Magazine magazineSubsystem) {
        super(
                new InstantCommand(shooterSubsystem::highShoot, shooterSubsystem),
                new WaitCommand(0.25),
                new InstantCommand(magazineSubsystem::shoot, magazineSubsystem)
        );
    }
}
