package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.*;

public class DriveSubsystem extends SubsystemBase {

    private HardwareMap hardwareMap;

    public final DcMotorEx frontLeftMotor;
    public final DcMotorEx frontRightMotor;

    private double speedLeftMotorMultiplier = 1.0;
    private double speedRightMotorMultiplier = 1.0;

    public DriveSubsystem(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;

        frontLeftMotor = hardwareMap.get(DcMotorEx.class, Constants.DriveConstants.FRONT_LEFT_MOTOR_NAME);
        frontRightMotor = hardwareMap.get(DcMotorEx.class, Constants.DriveConstants.FRONT_RIGHT_MOTOR_NAME);

        frontLeftMotor.setDirection(Constants.DriveConstants.FRONT_LEFT_MOTOR_DIRECTION);
        frontRightMotor.setDirection(Constants.DriveConstants.FRONT_RIGHT_MOTOR_DIRECTION);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        resetEncoders();
    }

    public void drive(double forward, double rotate) {
        forward = Math.abs(forward) >= Constants.DriveConstants.DEADZONE ? forward : 0;
        rotate = Math.abs(rotate) >= Constants.DriveConstants.DEADZONE ? rotate : 0;

        frontLeftMotor.setPower(Range.clip((forward - rotate), -1, 1) * speedLeftMotorMultiplier);
        frontRightMotor.setPower(Range.clip((forward + rotate), -1, 1) * speedRightMotorMultiplier);
    }

    public void drive(double forward, double rotate, String direction) {
        forward = Math.abs(forward) >= Constants.DriveConstants.DEADZONE ? forward : 0;
        rotate = Math.abs(rotate) >= Constants.DriveConstants.DEADZONE ? rotate : 0;

        // If the direction is "right", invert the rotate value to turn right
        if ("right".equalsIgnoreCase(direction)) {
            rotate = Math.abs(rotate);  // Ensure positive rotation for right turn
        }
        // If the direction is "left", invert the rotate value to turn left
        else if ("left".equalsIgnoreCase(direction)) {
            rotate = -Math.abs(rotate);  // Ensure negative rotation for left turn
        }

        // Set motor powers with clipping to the range [-1, 1] and apply speedMultiplier
        frontLeftMotor.setPower(Range.clip((forward - rotate), -1, 1) * speedLeftMotorMultiplier);
        frontRightMotor.setPower(Range.clip((forward + rotate), -1, 1) * speedRightMotorMultiplier);
    }


    public void setSpeedMultiplier(double multiplier) {
        speedLeftMotorMultiplier = Range.clip(multiplier, 0, 1);
        speedRightMotorMultiplier = Range.clip(multiplier, 0, 1);
    }

    public void resetEncoders() {
        frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}