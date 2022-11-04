package com.team2813.frc.subsystems;

import com.team2813.lib.motors.ControlMode;
import com.team2813.lib.motors.SparkMaxWrapper;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.team2813.lib.solenoid.SolenoidGroup;

import static com.team2813.frc.Constants.*;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake extends SubsystemBase {
    
    private static final double INTAKE_DEMAND = 0.67;

    SparkMaxWrapper motor;
    SolenoidGroup pistons;

    public Intake() {
        motor = new SparkMaxWrapper(INTAKE_MOTOR_ID, MotorType.kBrushless, true);
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
