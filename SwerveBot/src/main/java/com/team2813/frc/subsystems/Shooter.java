package com.team2813.frc.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.team2813.lib.motors.TalonFXWrapper;
import static com.team2813.frc.Constants.*;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.team2813.lib.motors.ControlMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    TalonFXWrapper shooter;
    private static final double LOW_SHOOT_DEMAND = 0.25;
    private static final double HIGH_SHOOT_DEMAND = 0.75;
    private static final double SPOOL_DEMAND = 0.5;

    public Shooter() {
        shooter = new TalonFXWrapper(SHOOTER_MOTOR_FRONT_ID, TalonFXInvertType.CounterClockwise);
        shooter.addFollower(SHOOTER_MOTOR_BACK_ID, TalonFXInvertType.OpposeMaster);
    }

    public void lowShoot() {
        shooter.set(ControlMode.DUTY_CYCLE, LOW_SHOOT_DEMAND);
    }

    public void highShoot() {
        shooter.set(ControlMode.DUTY_CYCLE, HIGH_SHOOT_DEMAND);
    }

    public void spool() {
    }

}
