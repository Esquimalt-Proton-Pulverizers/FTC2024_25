package org.firstinspires.ftc.teamcode; 

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

public abstract class Constants {

    public static abstract class DriveConstants {
        public static final String FRONT_LEFT_MOTOR_NAME = "frontLeftMotor";
        public static final String FRONT_RIGHT_MOTOR_NAME = "frontRightMotor";
        public static final String IMU_NAME = "imu";

        public static final String INTAKE_ARM_ANGLE_MOTOR = "intakeArmAngleMotor";
        public static final String ACTIVE_INTAKE_SERVO = "activeIntakeServo";
        public static final String WRIST_SERVO = "wristServo";
        public static final double WRIST_SERVO_MIN_ANGLE = 0;
        public static final double WRIST_SERVO_MAX_ANGLE = 180;
        public static final double WRIST_SERVO_UP_POSITION = 0;
        public static final double WRIST_SERVO_DOWN_POSITION = 180;
        
        public static final DcMotorSimple.Direction FRONT_LEFT_MOTOR_DIRECTION = DcMotorSimple.Direction.FORWARD;
        public static final DcMotorSimple.Direction FRONT_RIGHT_MOTOR_DIRECTION = DcMotorSimple.Direction.REVERSE;

        public static final IMU.Parameters IMU_PARAMETERS = new IMU.Parameters(
            new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
            )
        ); 
        
        public static final double DEADZONE = 0.1;
    }

}
