package com.team2813.frc.subsystems;

import com.team2813.lib.motors.ControlMode;
import com.team2813.lib.motors.TalonFXWrapper;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.team2813.lib.solenoid.SolenoidGroup;

import static com.team2813.frc.Constants.*;

import com.ctre.phoenix.motorcontrol.TalonFXInvertType;

public class Intake extends SubsystemBase {

    TalonFXWrapper motor;
    SolenoidGroup pistons;

    public Intake() {
        motor = new TalonFXWrapper(INTAKE_MOTOR_ID, TalonFXInvertType.CounterClockwise);
        pistons = new SolenoidGroup(PneumaticsModuleType.CTREPCM, LEFT_PISTON_PORT, RIGHT_PISTON_PORT);
    }

    public void deployIntake() {
        pistons.extend();
    }

    public void intake() {
        motor.set(ControlMode.DUTY_CYCLE, INTAKE_DEMAND);
    }

    public void outtake() {
        motor.set(ControlMode.DUTY_CYCLE, OUTTAKE_DEMAND);
    }

    public void stopIntake() {
        motor.set(ControlMode.DUTY_CYCLE, 0);
    }

    public void retractIntake() {
        pistons.retract();
    }
}
