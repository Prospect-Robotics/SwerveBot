package com.team2813.frc.subsystems;

import com.team2813.lib.motors.ControlMode;
import com.team2813.lib.motors.TalonFXWrapper;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.team2813.lib.solenoid.SolenoidGroup;

import static com.team2813.frc.Constants.*;

import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake extends SubsystemBase {
    
    private static final double INTAKE_DEMAND = 0.67;

    TalonFXWrapper motor;
    SolenoidGroup pistons;

    public Intake() {
        motor = new TalonFXWrapper(INTAKE_MOTOR_ID, TalonFXInvertType.Clockwise);
        pistons = new SolenoidGroup(PCM_ID, PneumaticsModuleType.CTREPCM, LEFT_PISTON_PORT, RIGHT_PISTON_PORT);
    }

    public void deployIntake() {
        motor.set(ControlMode.DUTY_CYCLE, INTAKE_DEMAND);
        pistons.extend();
    }

    public void retractIntake() {
        motor.set(ControlMode.DUTY_CYCLE, INTAKE_DEMAND);
        pistons.retract();
    }
}
