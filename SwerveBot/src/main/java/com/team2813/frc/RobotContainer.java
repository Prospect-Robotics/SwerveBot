// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team2813.frc;

import com.team2813.frc.commands.DefaultDriveCommand;
import com.team2813.frc.util.ShuffleboardData;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.InstantCommand;

import com.team2813.frc.subsystems.Magazine;
import com.team2813.frc.subsystems.Intake;
import com.team2813.frc.subsystems.Drive;

import static com.team2813.frc.Controls.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final Drive drive = new Drive();
    private final Magazine mag = new Magazine();
    private final Intake intake = new Intake();

    private final XboxController controller = new XboxController(0);

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        drive.setDefaultCommand(new DefaultDriveCommand(
                drive,
                () -> -modifyAxis(controller.getLeftY()) * Drive.MAX_VELOCITY,
                () -> -modifyAxis(controller.getLeftX()) * Drive.MAX_VELOCITY,
                () -> -modifyAxis(controller.getRightX()) * Drive.MAX_ANGULAR_VELOCITY));

        // Configure the button bindings
        configureButtonBindings();
    }

    // Package-private subsystem getters
    Drive getDrive() {
        return drive;
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
     * it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        // Add button to command mappings here.
        // See
        // https://docs.wpilib.org/en/stable/docs/software/commandbased/binding-commands-to-triggers.html
        INTAKE_BUTTON.whenHeld(new ParallelCommandGroup(
                new InstantCommand(mag::intake, mag),
                new InstantCommand(intake::deployIntake, intake)));
        INTAKE_BUTTON.whenReleased(new ParallelCommandGroup(
                new InstantCommand(mag::disable, mag),
                new InstantCommand(intake::retractIntake, intake)));
        OUTTAKE_BUTTON.whenHeld(new InstantCommand(mag::outtake, mag));
        OUTTAKE_BUTTON.whenReleased(new InstantCommand(mag::disable, mag));
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        AutoRoutine selectedRoutine = ShuffleboardData.routineChooser.getSelected();
        return selectedRoutine.getCommand();
    }

    public void addAutoRoutines() {
        for (AutoRoutine routine : AutoRoutine.values()) {
            ShuffleboardData.routineChooser.addOption(routine.getName(), routine);
        }
    }

    private static double deadband(double value, double deadband) {
        if (Math.abs(value) > deadband) {
            if (value > 0) {
                return (value - deadband) / (1 - deadband);
            } else {
                return (value + deadband) / (1 - deadband);
            }
        } else {
            return 0;
        }
    }

    private static double modifyAxis(double value) {
        value = deadband(value, 0.1);
        value = Math.copySign(value * value, value);
        return value;
    }
}
