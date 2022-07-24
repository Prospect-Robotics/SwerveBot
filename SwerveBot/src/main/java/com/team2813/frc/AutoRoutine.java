package com.team2813.frc;

import com.team2813.frc.commands.AutoInitDriveCommand;
import com.team2813.frc.commands.FollowCommand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import static com.team2813.frc.Robot.ROBOT_CONTAINER;
public enum AutoRoutine {

    STRAIGHT_TEST("Straight Test", new SequentialCommandGroup(
            new AutoInitDriveCommand("Straight_Test", ROBOT_CONTAINER.getDrive()),
            new FollowCommand("Straight_Test", ROBOT_CONTAINER.getDrive())
    )),
    TEST_1("Test 1", new SequentialCommandGroup(
            new AutoInitDriveCommand("Test_Path_1", ROBOT_CONTAINER.getDrive()),
            new FollowCommand("Test_Path_1", ROBOT_CONTAINER.getDrive())
    )),
    TEST_2("Test 2", new SequentialCommandGroup(
            new AutoInitDriveCommand("Test_Path_2", ROBOT_CONTAINER.getDrive()),
            new FollowCommand("Test_Path_2", ROBOT_CONTAINER.getDrive())
    ));

    private final String name;
    private final Command command;

    AutoRoutine(String name, Command command) {
        this.name = name;
        this.command = command;
    }

    public String getName() {
        return name;
    }

    Command getCommand() {
        return command;
    }
}
