package com.team2813.frc;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public final class Controls {
    // driver controls
    private static final PS4Controller DRIVER = new PS4Controller(0);
    private static final PS4Controller OPERATOR = new PS4Controller(1);
    public static final JoystickButton INTAKE_BUTTON = new JoystickButton(OPERATOR, PS4Controller.Button.kR1.value);
    public static final JoystickButton OUTTAKE_BUTTON = new JoystickButton(OPERATOR, PS4Controller.Button.kL1.value);
    public static final JoystickButton SPOOL_BUTTON = new JoystickButton(OPERATOR, PS4Controller.Button.kTriangle.value);

    public static final JoystickButton LOW_SHOOT_BUTTON = new JoystickButton(DRIVER, PS4Controller.Button.kL1.value);
    public static final JoystickButton HIGH_SHOOT_BUTTON = new JoystickButton(DRIVER, PS4Controller.Button.kR1.value);
}
