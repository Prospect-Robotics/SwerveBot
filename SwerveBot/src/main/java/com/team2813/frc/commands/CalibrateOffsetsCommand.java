package com.team2813.frc.commands;

import com.team2813.frc.subsystems.Drive;
import edu.wpi.first.wpilibj2.command.StartEndCommand;

import static com.team2813.frc.Constants.*;
import static com.team2813.frc.Controls.*;

public class CalibrateOffsetsCommand extends StartEndCommand {

    public CalibrateOffsetsCommand(Drive driveSubsystem) {
        super(
                () -> {
                    FRONT_LEFT_STEER_OFFSET = 0;
                    FRONT_RIGHT_STEER_OFFSET = 0;
                    BACK_LEFT_STEER_OFFSET = 0;
                    BACK_RIGHT_STEER_OFFSET = 0;

                    driveSubsystem.resetModules();
                },
                () -> {
                    driveSubsystem.setCalibratedSteerOffsets();
                    driveSubsystem.resetModules();
                },
                driveSubsystem
        );
    }

    @Override
    public boolean isFinished() {
        return STOP_MODULE_CAL.getAsBoolean();
    }
}
