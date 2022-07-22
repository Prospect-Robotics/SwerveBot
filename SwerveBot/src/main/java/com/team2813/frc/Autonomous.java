package com.team2813.frc;

import com.team2813.frc.subsystems.Drive;
import com.team2813.frc.util.ShuffleboardData;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Autonomous {

    public static final double MAX_VEL = 0.75; // m/s
    public static final double MAX_ACCEL = 2; // m/s

    // Use in FollowCommands and RotateCommands
    public static final Consumer<SwerveModuleState[]> outputModuleStates = new Consumer<SwerveModuleState[]>() {
        @Override
        public void accept(SwerveModuleState[] swerveModuleStates) {
            driveSubsystem.drive(swerveModuleStates);
        }
    };

    private static final List<Subsystem> allSubsystems = new ArrayList<>();
    private static final Drive driveSubsystem = getDriveSubsystem();

    public static void addSubsystems(Subsystem... subsystems) {
        allSubsystems.addAll(Arrays.asList(subsystems));
    }

    public static Command getAutoCommand() {
        AutoRoutine routine = ShuffleboardData.routineChooser.getSelected();
        return routine.getCommand();
    }

    public static void addRoutines() {
//        try {
//            for (AutoRoutine routine : AutoRoutine.values()) {
//                //ShuffleboardData.routineChooser.addOption(routine.name, routine);
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("Hi, Autonomous values");
//        System.out.printf("Autonomous values %s\n", Arrays.toString(AutoRoutine.values()));
        //System.out.println("Autonomous values" + AutoRoutine.class);
    }

    public static Drive getDriveSubsystem() {
        Drive driveSubsystem = null;
        for (Subsystem subsystem : allSubsystems) {
            if (subsystem instanceof Drive) {
                driveSubsystem = (Drive) subsystem;
                break;
            }
        }
        return driveSubsystem;
    }

//    public enum AutoRoutine {
//
//        STRAIGHT_TEST("Straight Test", new AutoRoutineCommand(
//                driveSubsystem, new FollowCommand("Straight_Test", outputModuleStates, driveSubsystem)));
//
//        private final String name;
//        private final Command command;
//
//        AutoRoutine(String name, Command command) {
//            this.name = name;
//            this.command = command;
//        }
//    }
}
