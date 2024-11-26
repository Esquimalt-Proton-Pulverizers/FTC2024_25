package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;

import org.firstinspires.ftc.teamcode.Constants;

public class IntakeSubsystem {
    private final ServoEx wristServo;
    private final CRServo activeIntakeServo;


    public IntakeSubsystem (HardwareMap hardwareMap){
        activeIntakeServo = hardwareMap.get(CRServo.class, Constants.DriveConstants.ACTIVE_INTAKE_SERVO);
        wristServo = new SimpleServo(hardwareMap,Constants.DriveConstants.WRIST_SERVO, Constants.DriveConstants.WRIST_SERVO_MIN_ANGLE,Constants.DriveConstants.WRIST_SERVO_MAX_ANGLE);
    }
    /** Moves the wrist servo into position to retract
     */
    public void servoUpPosition(){
        wristServo.turnToAngle(Constants.IntakeConstants.SERVO_UP_POSITION);
    }

    public void servoDownPosition(){
        wristServo.turnToAngle(Constants.IntakeConstants.SERVO_DOWN_POSITION);
    }

    public void activeIntakeServo(double speed) {
        activeIntakeServo.setPower(speed);
    }

    public double returnCurrentWristServoPosition(){
        return wristServo.getPosition();
    }
}
