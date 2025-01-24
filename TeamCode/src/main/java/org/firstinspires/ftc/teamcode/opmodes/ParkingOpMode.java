package org.firstinspires.ftc.teamcode.opmodes;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

import org.firstinspires.ftc.teamcode.Robot;

@Autonomous(name="Autonomous parking", group = "Linear Opmode")
public class ParkingOpMode extends LinearOpMode {
    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {

        Robot robot = new Robot(this);
        DriveSubsystem driveSubsystem;
        driveSubsystem = new DriveSubsystem(hardwareMap);
        robot.configureAutoModeBindings();



        waitForStart();

        /** original code - as is
        driveSubsystem.drive(1, 0);
        sleep(200);
        driveSubsystem.drive(0,1, "right");
        sleep(1700);
        driveSubsystem.drive(1, 0);
        sleep(1500);
        driveSubsystem.drive(0, 0);
        */

        driveSubsystem.drive(1, 0);
        sleep(2000);
        driveSubsystem.drive(0, 0);

        while (opModeIsActive() && !isStopRequested()) {
            robot.autoRun();
        }
    }
}