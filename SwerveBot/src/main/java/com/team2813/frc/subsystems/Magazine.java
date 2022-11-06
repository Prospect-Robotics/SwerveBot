package com.team2813.frc.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static com.team2813.frc.Constants.*;

import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.team2813.lib.motors.ControlMode;
import com.team2813.lib.motors.TalonFXWrapper;

public class Magazine extends SubsystemBase {

    TalonFXWrapper mag;

    public Magazine() {
        mag = new TalonFXWrapper(MAG_MOTOR_ID, TalonFXInvertType.Clockwise);
        mag.setNeutralMode(NeutralMode.Brake);
    }

    public void intake() {
        mag.set(ControlMode.DUTY_CYCLE, INTAKE_DEMAND);
    }

    public void outtake() {
        mag.set(ControlMode.DUTY_CYCLE, OUTTAKE_DEMAND);
    }

    public void disable() {
        mag.set(ControlMode.DUTY_CYCLE, 0);
    }
}
