package com.team2813.frc.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.team2813.lib.motors.TalonFXWrapper;
import static com.team2813.frc.Constants.*;
import com.team2813.lib.motors.ControlMode;

import com.ctre.phoenix.motorcontrol.TalonFXInvertType;

public class Magazine extends CommandBase {
    
    private static double demand = 0.67;
    
    TalonFXWrapper mag;

    public Magazine() {
        mag = new TalonFXWrapper(MAGAZINE_LEFT_ID, TalonFXInvertType.Clockwise);
        mag.addFollower(MAGAZINE_RIGHT_ID, TalonFXInvertType.CounterClockwise);
    }

    public void intake() {
        mag.set(ControlMode.DUTY_CYCLE, demand);
    }

    public void outtake() {
        mag.set(ControlMode.DUTY_CYCLE, -demand);
    }
}
