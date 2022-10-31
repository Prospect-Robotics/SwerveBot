package com.team2813.frc.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.team2813.lib.motors.TalonFXWrapper;
import static com.team2813.frc.Constants.*;
import com.team2813.lib.motors.ControlMode;

import com.ctre.phoenix.motorcontrol.TalonFXInvertType;

public class Magazine extends CommandBase {
    
    private static final double INTAKE_DEMAND = 0.67;
    private static final double OUTTAKE_DEMAND = -0.33;
    
    TalonFXWrapper mag;

    public Magazine() {
        mag = new TalonFXWrapper(MAG_MOTOR_ID, TalonFXInvertType.Clockwise);
    }

    public void intake() {
        mag.set(ControlMode.DUTY_CYCLE, INTAKE_DEMAND);
    }

    public void outtake() {
        mag.set(ControlMode.DUTY_CYCLE, OUTTAKE_DEMAND);
    }
    // I don't know if it will automatically set the demand to zero, but just to be safe, here is a method for turning off intake/outtake.
    public void disable() {
        mag.set(ControlMode.DUTY_CYCLE, 0);
    }
}
