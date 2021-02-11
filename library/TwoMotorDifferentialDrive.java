package org.firstinspires.ftc.teamcode.library;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * A class used to manage the driving of a two motor differential drive
 */
public class TwoMotorDifferentialDrive {

    private final DcMotor leftMotor;
    private final DcMotor rightMotor;

    /**
     * @param leftMotor DcMotor controlling the left wheel
     * @param rightMotor DcMotor controlling the right wheel
     */
    public TwoMotorDifferentialDrive(DcMotor leftMotor, DcMotor rightMotor) {
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;

        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    /**
     * Move the drive train with a speed and a rotation
     * Make sure the drive train is in the correct mode before using
     *
     * @param speed how fast to move the drive train (positive values are forwards) [-1..1]
     * @param rotation how fast to rotate the drive train (positive values are clockwise) [-1..1]
     */
    public void drive(double speed, double rotation) {
        drive(speed, rotation, false);
    }

    /**
     * Move the drive train with a speed and a rotation
     * Make sure the drive train is in the correct mode before using
     *
     * @param speed how fast to move the drive train (positive values are forwards) [-1..1]
     * @param rotation how fast to rotate the drive train (positive values are clockwise) [-1..1]
     * @param squareInputs speed and rotation will be squared (but keep their - sign) if set to true
     *                     this makes driving with a controller at slower speeds more precise, but doesn't limit top speed
     */
    public void drive(double speed, double rotation, boolean squareInputs) {
        leftMotor.setPower(speed + rotation);
        rightMotor.setPower(speed - rotation);
    }

    /**
     * Sets both drive train motors to RUN_WITHOUT_ENCODER run mode
     */
    public void switchToPowerMode() {
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    /**
     * Sets both drive train motors to RUN_USING_ENCODER run mode
     */
    public void switchToVelocityMode() {
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * Resets both drive train motors
     * WARNING: this will stop the drive train
     * After using this method, put drive train motors back into desired run mode
     */
    public void resetEncoders() {
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    /**
     * Moves both drive train motors to given target positions using PID
     * After using this method, put drive train motors back into desired run mode
     *
     * @param targetPositions ordered list of target positions [left, right]
     * @param maxPower the maximum power the motors will be able to run at [-1..1]
     */
    public void runToPositions(int[] targetPositions, double maxPower) {
        leftMotor.setTargetPosition(targetPositions[0]);
        rightMotor.setTargetPosition(targetPositions[1]);

        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftMotor.setPower(maxPower);
        rightMotor.setPower(maxPower);
    }

}
