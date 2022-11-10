package com.team2813.frc.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.team2813.lib.solenoid.SolenoidGroup;

public class Nugget extends SubsystemBase {

    private SolenoidGroup nugget;
    public Nugget() {
        nugget = new SolenoidGroup(PneumaticsModuleType.CTREPCM, 0);
        System.out.println("Logging to the console"); // Checking if logging even works
    }

    public void activate() {
        nugget.extend();
        System.out.println("activating Jefferry");
    }

    public void deactivate() {
        nugget.retract();
        System.out.println("deactivating Jefferry");
    }
}
