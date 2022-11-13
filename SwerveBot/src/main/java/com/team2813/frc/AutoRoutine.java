package com.team2813.frc;

import com.team2813.frc.commands.*;
import com.team2813.frc.commands.AutoHighShootCommand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import static com.team2813.frc.Robot.ROBOT_CONTAINER;

public enum AutoRoutine {

        // STRAIGHT_TEST("Straight Test", new SequentialCommandGroup(
        // new AutoInitDriveCommand("Straight_Test", ROBOT_CONTAINER.getDrive()),
        // new FollowCommand("Straight_Test", ROBOT_CONTAINER.getDrive())
        // )),
        // STRAFE_TEST("Strafe Test", new SequentialCommandGroup(
        // new AutoInitDriveCommand("Strafe_Test", ROBOT_CONTAINER.getDrive()),
        // new FollowCommand("Strafe_Test", ROBOT_CONTAINER.getDrive())
        // )),
        // ROTATE_90_TEST("Rotate 90 Test", new SequentialCommandGroup(
        // new AutoInitDriveCommand(new Rotation2d(), ROBOT_CONTAINER.getDrive()),
        // new RotateCommand(90, ROBOT_CONTAINER.getDrive())
        // )),
        // ROTATE_180_Test("Rotate 180 Test", new SequentialCommandGroup(
        // new AutoInitDriveCommand(new Rotation2d(), ROBOT_CONTAINER.getDrive()),
        // new RotateCommand(180, ROBOT_CONTAINER.getDrive())
        // )),
        // ADVANCED_TEST_1("Advanced Test 1", new SequentialCommandGroup(
        // new AutoInitDriveCommand("Advanced_Test_1", ROBOT_CONTAINER.getDrive()),
        // new FollowCommand("Advanced_Test_1", ROBOT_CONTAINER.getDrive())
        // )),
        // ADVANCED_TEST_2("Advanced Test 2", new SequentialCommandGroup(
        // new AutoInitDriveCommand("Advanced_Test_2", ROBOT_CONTAINER.getDrive()),
        // new FollowCommand("Advanced_Test_2", ROBOT_CONTAINER.getDrive())
        // )),
        // ADVANCED_TEST_3("Advanced Test 3", new SequentialCommandGroup(
        // new AutoInitDriveCommand("Advanced_Test_3", ROBOT_CONTAINER.getDrive()),
        // new FollowCommand("Advanced_Test_3", ROBOT_CONTAINER.getDrive())
        // )),
        // ADVANCED_TEST_4("Advanced Test 4", new SequentialCommandGroup(
        // new AutoInitDriveCommand("Advanced_Test_4", ROBOT_CONTAINER.getDrive()),
        // new FollowCommand("Advanced_Test_4", ROBOT_CONTAINER.getDrive())
        // )),
        TWO_BALL_TARMAC_BOTTOM("2-ball Tarmac Bottom", new SequentialCommandGroup(
                        new AutoIntakeCommand(ROBOT_CONTAINER.getIntake(), ROBOT_CONTAINER.getMag(),
                                        ROBOT_CONTAINER.getShooter()),
                        new AutoInitDriveCommand("TwoBall_Bottom_Intake", ROBOT_CONTAINER.getDrive()),
                        new FollowCommand("TwoBall_Bottom_Intake", ROBOT_CONTAINER.getDrive()),
                        new WaitCommand(1),
                        new ParallelCommandGroup(
                                        new AutoStopIntakeCommand(
                                                        ROBOT_CONTAINER.getIntake(), ROBOT_CONTAINER.getMag(),
                                                        ROBOT_CONTAINER.getShooter(), true),
                                        new FollowCommand("TwoBall_Bottom_IntakeToShoot", ROBOT_CONTAINER.getDrive())),
                        new AutoHighShootCommand(ROBOT_CONTAINER.getShooter(), ROBOT_CONTAINER.getMag()))),
        TWO_BALL_TARMAC_SIDE("2-ball Tarmac Side", new SequentialCommandGroup(
                        new AutoIntakeCommand(ROBOT_CONTAINER.getIntake(), ROBOT_CONTAINER.getMag(),
                                        ROBOT_CONTAINER.getShooter()),
                        new AutoInitDriveCommand("TwoBall_Side_Intake", ROBOT_CONTAINER.getDrive()),
                        new FollowCommand("TwoBall_Side_Intake", ROBOT_CONTAINER.getDrive()),
                        new WaitCommand(1),
                        new ParallelCommandGroup(
                                        new AutoStopIntakeCommand(
                                                        ROBOT_CONTAINER.getIntake(), ROBOT_CONTAINER.getMag(),
                                                        ROBOT_CONTAINER.getShooter(), true),
                                        new FollowCommand("TwoBall_Side_IntakeToShoot", ROBOT_CONTAINER.getDrive())),
                        new AutoHighShootCommand(ROBOT_CONTAINER.getShooter(), ROBOT_CONTAINER.getMag()))),
        TWO_BALL_TARMAC_TOP("2-ball Tarmac Top", new SequentialCommandGroup(
                        new AutoIntakeCommand(ROBOT_CONTAINER.getIntake(), ROBOT_CONTAINER.getMag(),
                                        ROBOT_CONTAINER.getShooter()),
                        new AutoInitDriveCommand("TwoBall_Top_Intake", ROBOT_CONTAINER.getDrive()),
                        new FollowCommand("TwoBall_Top_Intake", ROBOT_CONTAINER.getDrive()),
                        new WaitCommand(1),
                        new ParallelCommandGroup(
                                        new AutoStopIntakeCommand(
                                                        ROBOT_CONTAINER.getIntake(), ROBOT_CONTAINER.getMag(),
                                                        ROBOT_CONTAINER.getShooter(), true),
                                        new FollowCommand("TwoBall_Top_IntakeToShoot", ROBOT_CONTAINER.getDrive())),
                        new AutoHighShootCommand(ROBOT_CONTAINER.getShooter(), ROBOT_CONTAINER.getMag()))),
        TWO_BALL_FENDER_BOTTOM("2-ball Fender Bottom", new SequentialCommandGroup(
                        new AutoLowShootCommand(ROBOT_CONTAINER.getShooter(), ROBOT_CONTAINER.getMag()),
                        new AutoIntakeCommand(ROBOT_CONTAINER.getIntake(), ROBOT_CONTAINER.getMag(),
                                        ROBOT_CONTAINER.getShooter()),
                        new AutoInitDriveCommand("2_Ball_Bottom_Fender_Back", ROBOT_CONTAINER.getDrive()),
                        new FollowCommand("2_Ball_Bottom_Fender_Back", ROBOT_CONTAINER.getDrive()),
                        new WaitCommand(1),
                        new ParallelCommandGroup(
                                        new AutoStopIntakeCommand(
                                                        ROBOT_CONTAINER.getIntake(), ROBOT_CONTAINER.getMag(),
                                                        ROBOT_CONTAINER.getShooter(), true),
                                        new FollowCommand("2_Ball_Bottom_Fender_Forward", ROBOT_CONTAINER.getDrive())),
                        new AutoLowShootCommand(ROBOT_CONTAINER.getShooter(), ROBOT_CONTAINER.getMag()))),
        TWO_BALL_FENDER_SIDE("2-ball Fender Side", new SequentialCommandGroup(
                        new AutoLowShootCommand(ROBOT_CONTAINER.getShooter(), ROBOT_CONTAINER.getMag()),
                        new AutoIntakeCommand(ROBOT_CONTAINER.getIntake(), ROBOT_CONTAINER.getMag(),
                                        ROBOT_CONTAINER.getShooter()),
                        new AutoInitDriveCommand("2_Ball_Side_Fender_Back", ROBOT_CONTAINER.getDrive()),
                        new FollowCommand("2_Ball_Side_Fender_Back", ROBOT_CONTAINER.getDrive()),
                        new WaitCommand(1),
                        new ParallelCommandGroup(
                                        new AutoStopIntakeCommand(
                                                        ROBOT_CONTAINER.getIntake(), ROBOT_CONTAINER.getMag(),
                                                        ROBOT_CONTAINER.getShooter(), true),
                                        new FollowCommand("2_Ball_Side_Fender_Forward", ROBOT_CONTAINER.getDrive())),
                        new AutoLowShootCommand(ROBOT_CONTAINER.getShooter(), ROBOT_CONTAINER.getMag()))),
        TWO_BALL_FENDER_TOP("2-ball Fender Top", new SequentialCommandGroup(
                        new AutoLowShootCommand(ROBOT_CONTAINER.getShooter(), ROBOT_CONTAINER.getMag()),
                        new AutoIntakeCommand(ROBOT_CONTAINER.getIntake(), ROBOT_CONTAINER.getMag(),
                                        ROBOT_CONTAINER.getShooter()),
                        new AutoInitDriveCommand("2_Ball_Top_Fender_Back", ROBOT_CONTAINER.getDrive()),
                        new FollowCommand("2_Ball_Top_Fender_Back", ROBOT_CONTAINER.getDrive()),
                        new WaitCommand(1),
                        new ParallelCommandGroup(
                                        new AutoStopIntakeCommand(
                                                        ROBOT_CONTAINER.getIntake(), ROBOT_CONTAINER.getMag(),
                                                        ROBOT_CONTAINER.getShooter(), true),
                                        new FollowCommand("2_Ball_Top_Fender_Forward", ROBOT_CONTAINER.getDrive())),
                        new AutoLowShootCommand(ROBOT_CONTAINER.getShooter(), ROBOT_CONTAINER.getMag()))),
        TAXI("Taxi", new SequentialCommandGroup(
                        new AutoInitDriveCommand("Taxi", ROBOT_CONTAINER.getDrive()),
                        new FollowCommand("Taxi", ROBOT_CONTAINER.getDrive()))),
        TWO_BALL_FENDER_TOP_HIGH("2-ball Fender Top", new SequentialCommandGroup(
                        new AutoLowShootCommand(ROBOT_CONTAINER.getShooter(), ROBOT_CONTAINER.getMag()),
                        new AutoIntakeCommand(ROBOT_CONTAINER.getIntake(), ROBOT_CONTAINER.getMag(),
                                        ROBOT_CONTAINER.getShooter()),
                        new AutoInitDriveCommand("2_Ball_Top_Fender_Back", ROBOT_CONTAINER.getDrive()),
                        new FollowCommand("2_Ball_Top_Fender_Back", ROBOT_CONTAINER.getDrive()),
                        new WaitCommand(1),
                        new ParallelCommandGroup(
                                        new AutoStopIntakeCommand(
                                                        ROBOT_CONTAINER.getIntake(), ROBOT_CONTAINER.getMag(),
                                                        ROBOT_CONTAINER.getShooter(), true),
                                        new FollowCommand("TwoBall_Top_Fender_ShootHigh", ROBOT_CONTAINER.getDrive())),
                        new AutoHighShootCommand(ROBOT_CONTAINER.getShooter(), ROBOT_CONTAINER.getMag())));
        // Has issues and no time to fix, so it's commented out
        // ADVANCED_DEFENSE("Advanced Defense", new SequentialCommandGroup(
        // new AutoLowShootCommand(ROBOT_CONTAINER.getShooter(),
        // ROBOT_CONTAINER.getMag()),
        // new AutoIntakeCommand(ROBOT_CONTAINER.getIntake(), ROBOT_CONTAINER.getMag(),
        // ROBOT_CONTAINER.getShooter()),
        // new AutoInitDriveCommand("AdvancedDefense_IntakeFirst",
        // ROBOT_CONTAINER.getDrive()),
        // new FollowCommand("AdvancedDefence_IntakeFirst", ROBOT_CONTAINER.getDrive()),
        // new AutoStopIntakeCommand(
        // ROBOT_CONTAINER.getIntake(), ROBOT_CONTAINER.getMag(),
        // ROBOT_CONTAINER.getShooter()),
        // new RotateCommand(38.05, ROBOT_CONTAINER.getDrive()), // Rotate to face our
        // hangar
        // new AutoOuttakeCommand(ROBOT_CONTAINER.getIntake(), ROBOT_CONTAINER.getMag(),
        // ROBOT_CONTAINER.getShooter()),
        // new WaitCommand(2),
        // new AutoStopIntakeCommand(
        // ROBOT_CONTAINER.getIntake(), ROBOT_CONTAINER.getMag(),
        // ROBOT_CONTAINER.getShooter()),
        // new RotateCommand(-68.05, ROBOT_CONTAINER.getDrive()), // Rotate to not
        // interfere with other robots
        // new WaitCommand(7), // Wait for other robot to taxi
        // new AutoIntakeCommand(ROBOT_CONTAINER.getIntake(), ROBOT_CONTAINER.getMag(),
        // ROBOT_CONTAINER.getShooter()),
        // new FollowCommand("AdvancedDefense_IntakeSecond",
        // ROBOT_CONTAINER.getDrive()),
        // new WaitCommand(1),
        // new AutoOuttakeCommand(ROBOT_CONTAINER.getIntake(), ROBOT_CONTAINER.getMag(),
        // ROBOT_CONTAINER.getShooter()),
        // new AutoStopIntakeCommand(
        // ROBOT_CONTAINER.getIntake(), ROBOT_CONTAINER.getMag(),
        // ROBOT_CONTAINER.getShooter())
        // ));

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
