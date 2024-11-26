package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.arcrobotics.ftclib.command.RunCommand;

import org.firstinspires.ftc.teamcode.subsystems.*;

public class Robot {

    private final OpMode opMode;

    private final GamepadEx driverGamepad;
    private final GamepadEx operatorGamepad;

    public final DriveSubsystem driveSubsystem;
    public final IntakeSubsystem intakeSubsystem;

    public Robot(OpMode opMode) {
        this.opMode = opMode;

        driverGamepad = new GamepadEx(opMode.gamepad1);
        operatorGamepad = new GamepadEx(opMode.gamepad2);

        driveSubsystem = new DriveSubsystem(opMode.hardwareMap);
        intakeSubsystem = new IntakeSubsystem(opMode.hardwareMap);

    }

    public void configureTeleOpBindings() {

        /* Controls:
         * Driver:
         *   Forward -> left y axis
         *   Turn -> right x axis
         *
         *   Reduce Speed -> right trigger
         */

        CommandScheduler.getInstance().reset();
        CommandScheduler.getInstance().cancelAll();

        RunCommand defaultDriveCommand = new RunCommand(() -> driveSubsystem.drive(driverGamepad.getLeftY(), driverGamepad.getRightX()));
        defaultDriveCommand.addRequirements(driveSubsystem);

        driveSubsystem.setDefaultCommand(defaultDriveCommand);


    }

    public void configureAutoModeBindings() {
        CommandScheduler.getInstance().reset();
        CommandScheduler.getInstance().cancelAll();
    }



    public void run() {
        CommandScheduler.getInstance().run();
        if (operatorGamepad.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.5) {//if left trigger intake
            intakeSubsystem.activeIntakeServo(1);
        } else if(operatorGamepad.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.5){//if right trigger outtake
            intakeSubsystem.activeIntakeServo(-1);
        } else {// if not triggers stop intake servo
            intakeSubsystem.activeIntakeServo(0);
        }


        if (isPressed(operatorGamepad.getRightX())) {
            intakeSubsystem.servoUpPosition(operatorGamepad.getRightX());
        } else if (isPressed(operatorGamepad.getRightX())) {
            intakeSubsystem.servoDownPosition(operatorGamepad.getRightX());
        }
        opMode.telemetry.update();
    }

    public void autoRun()
    {
        CommandScheduler.getInstance().run();
    }


    public  boolean isPressed(double controllerInput) {
        return Math.abs(controllerInput) >= Constants.DriveConstants.DEADZONE;
    }
}