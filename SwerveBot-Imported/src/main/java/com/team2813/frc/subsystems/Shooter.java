package com.team2813.frc.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.team2813.lib.motors.TalonFXWrapper;
import static com.team2813.frc.Constants.*;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.team2813.lib.motors.ControlMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    TalonFXWrapper shooter;
    private static final double HIGH_SHOOT_DEMAND = 0.45;
    private static final double LOW_SHOOT_DEMAND = 0.135;
    private static final double KICK_DEMAND = -0.1;

    public Shooter() {
        shooter = new TalonFXWrapper(SHOOTER_MOTOR_FRONT_ID, TalonFXInvertType.CounterClockwise);
        shooter.addFollower(SHOOTER_MOTOR_BACK_ID, TalonFXInvertType.FollowMaster);
        shooter.setNeutralMode(NeutralMode.Brake);
    }

    public void lowShoot() {
        shooter.set(ControlMode.DUTY_CYCLE, LOW_SHOOT_DEMAND);
    }

    public void highShoot() {
        shooter.set(ControlMode.DUTY_CYCLE, HIGH_SHOOT_DEMAND);
    }

    public void disable() {
        shooter.set(ControlMode.DUTY_CYCLE, 0);
    }

    public void kick() {
        shooter.set(ControlMode.DUTY_CYCLE, KICK_DEMAND);
    }
}
