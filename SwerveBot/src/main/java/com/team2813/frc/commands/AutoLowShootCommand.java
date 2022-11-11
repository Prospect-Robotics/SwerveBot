package com.team2813.frc.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import com.team2813.frc.subsystems.Shooter;
import com.team2813.frc.subsystems.Magazine;

public class AutoLowShootCommand extends SequentialCommandGroup {
    public AutoLowShootCommand(Shooter shooter, Magazine mag) {
        super(
                new InstantCommand(shooter::lowShoot, shooter),
                new WaitCommand(0.25),
                new InstantCommand(mag::shoot, mag),
                new WaitCommand(2),
                new ParallelCommandGroup(
                        new InstantCommand(shooter::disable, shooter),
                        new InstantCommand(mag::disable, shooter)
                )
        );
    }
}
