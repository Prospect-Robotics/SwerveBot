package com.team2813.frc;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public final class Controls {
    // driver controls
    private static final PS4Controller DRIVER = new PS4Controller(0);
    public static final JoystickButton START_MODULE_CAL = new JoystickButton(DRIVER, 1);
    public static final JoystickButton STOP_MODULE_CAL = new JoystickButton(DRIVER, 2);

    // operator controls
    private static final PS4Controller OPERATOR = new PS4Controller(1);
}
