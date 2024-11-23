package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

@Autonomous(name="Autonomous parking", group = "Real")
public class ParkingOpMode extends LinearOpMode {

    Robot robot = new Robot(this);
    @Override
    public void runOpMode() throws InterruptedException {


        robot.configureAutoModeBindings();

        waitForStart();

        // to be changed later to make it park in the observation zone
        driveAndSleep(1, 0.5, 1000);
        driveAndSleep(1, 0, 1000);
        robot.driveSubsystem.drive(0, 0);

        while (opModeIsActive() && !isStopRequested()) {
            robot.run();
        }
    }
    public void driveAndSleep(double forward, double rotate, int sleepTimeMilli)
    {
        robot.driveSubsystem.drive(forward, rotate);
        sleep(sleepTimeMilli);
    }
}