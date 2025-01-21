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

    public final DriveSubsystem driveSubsystem;

    public Robot(OpMode opMode) {
        this.opMode = opMode;

        driverGamepad = new GamepadEx(opMode.gamepad1);

        driveSubsystem = new DriveSubsystem(opMode.hardwareMap);

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