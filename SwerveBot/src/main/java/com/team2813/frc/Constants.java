// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team2813.frc;

import com.swervedrivespecialties.swervelib.SdsModuleConfigurations;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // CAN IDs

    // Swerve Modules

    // Front Left
    public static final int FRONT_LEFT_DRIVE_ID = 1;
    public static final int FRONT_LEFT_STEER_ID = 2;
    public static final int FRONT_LEFT_ENCODER_ID = 3;

    // Front Right
    public static final int FRONT_RIGHT_DRIVE_ID = 4;
    public static final int FRONT_RIGHT_STEER_ID = 5;
    public static final int FRONT_RIGHT_ENCODER_ID = 6;

    // Back Left
    public static final int BACK_LEFT_DRIVE_ID = 7;
    public static final int BACK_LEFT_STEER_ID = 8;
    public static final int BACK_LEFT_ENCODER_ID = 9;

    // Back Right
    public static final int BACK_RIGHT_DRIVE_ID = 10;
    public static final int BACK_RIGHT_STEER_ID = 11;
    public static final int BACK_RIGHT_ENCODER_ID = 12;

    // Other Drive Stuff

    public static final int PIGEON_ID = 13;

    // magazine

    public static final int MAG_MOTOR_ID = 14;

    // intake

    public static final int INTAKE_MOTOR_ID = 15;

    // Piston stuff
    public static final int LEFT_PISTON_PORT = 1;
    public static final int RIGHT_PISTON_PORT = 2;

    // shooter

    public static final int SHOOTER_MOTOR_FRONT_ID = 16;
    public static final int SHOOTER_MOTOR_BACK_ID = 17;

    // Physical Drive Constants

    // Steer offsets
    public static final double FRONT_LEFT_STEER_OFFSET = -Math.toRadians(161.3671875);
    public static final double FRONT_RIGHT_STEER_OFFSET = -Math.toRadians(285.205078125);
    public static final double BACK_LEFT_STEER_OFFSET = -Math.toRadians(150.732421875);
    public static final double BACK_RIGHT_STEER_OFFSET = -Math.toRadians(250.576171875);

    public static final double TRACKWIDTH = Units.inchesToMeters(19.5); // meters
    public static final double WHEELBASE = Units.inchesToMeters(21.5); // meters
    public static final double WHEEL_CIRCUMFERENCE = SdsModuleConfigurations.MK4_L2.getWheelDiameter() * Math.PI; // meters

    // Auto Constants
    public static final double AUTO_MAX_VEL = 2.25; // m/s
    public static final double AUTO_MAX_ACCEL = 6; // m/s

    // Intake and Outtake Speeds
    public static final double INTAKE_DEMAND = 0.85;
    public static final double MAG_INTAKE_DEMAND = 0.15;
    public static final double MAG_SHOOT_DEMAND = 0.17;
    public static final double SHUFFLE_DEMAND = -0.1;
    public static final double OUTTAKE_DEMAND = -0.15;
}
