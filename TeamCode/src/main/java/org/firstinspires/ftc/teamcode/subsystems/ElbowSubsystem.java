package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Constants;

public class ElbowSubsystem extends SubsystemBase {
    private final HardwareMap hardwareMap;
    private final DcMotorEx intakeArmAngleMotor;
    public ElbowSubsystem(HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;

        intakeArmAngleMotor = hardwareMap.get(DcMotorEx.class, Constants.DriveConstants.INTAKE_ARM_ANGLE_MOTOR);

        intakeArmAngleMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        resetEncoders();
    }

    public void resetEncoders()
    {
        intakeArmAngleMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intakeArmAngleMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void runElbowMotor(double power){
        intakeArmAngleMotor.setPower(Range.clip(power,-1,1));
    }
}
