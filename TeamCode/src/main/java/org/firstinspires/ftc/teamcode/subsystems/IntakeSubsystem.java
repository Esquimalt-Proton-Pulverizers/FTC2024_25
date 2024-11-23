package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;

import org.firstinspires.ftc.teamcode.Constants;

public class IntakeSubsystem {

    private final DcMotorEx intakeArmAngleMotor;
    private final ServoEx wristServo;
    private final CRServo activeIntakeServo;


    public IntakeSubsystem (HardwareMap hardwareMap){

        intakeArmAngleMotor = hardwareMap.get(DcMotorEx.class, Constants.DriveConstants.INTAKE_ARM_ANGLE_MOTOR);
        activeIntakeServo = hardwareMap.get(CRServo.class, Constants.DriveConstants.ACTIVE_INTAKE_SERVO);
        wristServo = new SimpleServo(hardwareMap,Constants.DriveConstants.WRIST_SERVO, Constants.DriveConstants.WRIST_SERVO_MIN_ANGLE,Constants.DriveConstants.WRIST_SERVO_MAX_ANGLE);

        intakeArmAngleMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        resetEncoders();
    }

    public void resetEncoders()
    {
        intakeArmAngleMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intakeArmAngleMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    /** Moves the wrist servo into position to retract
     */
    public void servoUpPosition(double speed) {
        double currentPosition= wristServo.getPosition();
        if (currentPosition<150){//TODO find an appropriate max position and add to constants
            wristServo.turnToAngle(currentPosition+Math.abs(speed) * 0.7);
        }
    }

    /** Moves the wrist servo into position to pick up samples
     */
    public void servoDownPosition(double speed) {
        double currentPosition= wristServo.getPosition();
        if (currentPosition>=0){//TODO find an appropriate min position and add to constants
            wristServo.turnToAngle(currentPosition-Math.abs(speed) * 0.7);
        }
    }

    public void armExtensionControl(){

    }

    public void activeIntakeServo(double speed) {
        activeIntakeServo.setPower(speed);
    }
}
