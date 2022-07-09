// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team2813.frc;

import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.team2813.frc.commands.DefaultDriveCommand;
import com.team2813.frc.commands.FollowCommand;
import com.team2813.frc.subsystems.Drive;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

import java.util.function.Consumer;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer
{
    // The robot's subsystems and commands are defined here...
    private final Drive drive = new Drive();

    private final XboxController controller = new XboxController(0);
    
    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer()
    {
        drive.setDefaultCommand(new DefaultDriveCommand(
                drive,
                () -> -modifyAxis(controller.getLeftY()) * Drive.MAX_VELOCITY,
                () -> -modifyAxis(controller.getLeftX()) * Drive.MAX_VELOCITY,
                () -> -modifyAxis(controller.getRightX()) * Drive.MAX_ANGULAR_VELOCITY
        ));

        // Configure the button bindings
        configureButtonBindings();
    }
    
    
    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings()
    {
        // Add button to command mappings here.
        // See https://docs.wpilib.org/en/stable/docs/software/commandbased/binding-commands-to-triggers.html
    }
    
    
    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand()
    {
        // Use in FollowCommands and RotateCommands
        Consumer<SwerveModuleState[]> outputModuleStates = new Consumer<SwerveModuleState[]>() {
            @Override
            public void accept(SwerveModuleState[] swerveModuleStates) {
                drive.drive(swerveModuleStates);
            }
        };

        PathPlannerTrajectory straightTestTrajectory = PathPlanner.loadPath("Straight_Test", 0.75, 2);

        return new FollowCommand(straightTestTrajectory, outputModuleStates, drive);
    }

    private static double deadband(double value, double deadband) {
        if (Math.abs(value) > deadband) {
            if (value > 0) {
                return (value - deadband) / (1 - deadband);
            }
            else {
                return (value + deadband) / (1 - deadband);
            }
        }
        else {
            return 0;
        }
    }

    private static double modifyAxis(double value) {
        value = deadband(value, 0.1);
        value = Math.copySign(value * value, value);
        return value;
    }
}
