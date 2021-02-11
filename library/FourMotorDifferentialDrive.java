package org.firstinspires.ftc.teamcode.library;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * A class used to manage the driving of a four motor differential drive
 */
public class FourMotorDifferentialDrive {

    private final DcMotor leftMotorOne;
    private final DcMotor leftMotorTwo;
    private final DcMotor rightMotorOne;
    private final DcMotor rightMotorTwo;

    public FourMotorDifferentialDrive(DcMotor leftMotorOne, DcMotor leftMotorTwo, DcMotor rightMotorOne, DcMotor rightMotorTwo) {
        this.leftMotorOne = leftMotorOne;
        this.leftMotorTwo= leftMotorTwo;
        this.rightMotorOne = rightMotorOne;
        this.rightMotorTwo = rightMotorTwo;

        rightMotorOne.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotorTwo.setDirection(DcMotorSimple.Direction.REVERSE);
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
        leftMotorOne.setPower(speed + rotation);
        leftMotorOne.setPower(speed + rotation);
        rightMotorOne.setPower(speed - rotation);
        rightMotorTwo.setPower(speed - rotation);
    }

    /**
     * Sets all drive train motors to RUN_WITHOUT_ENCODER run mode
     */
    public void switchToPowerMode() {
        leftMotorOne.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftMotorTwo.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotorOne.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotorTwo.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    /**
     * Sets all drive train motors to RUN_USING_ENCODER run mode
     */
    public void switchToVelocityMode() {
        leftMotorOne.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotorTwo.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotorOne.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotorTwo.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * Resets all drive train motors
     * WARNING: this will stop the drive train
     * After using this method, put drive train motors back into desired run mode
     */
    public void resetEncoders() {
        leftMotorOne.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotorTwo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotorOne.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotorTwo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    /**
     * Moves all drive train motors to given target positions using PID
     * After using this method, put drive train motors back into desired run mode
     *
     * @param targetPositions ordered list of target positions [left, right]
     * @param maxPower the maximum power the motors will be able to run at [-1..1]
     */
    public void runToPositions(int[] targetPositions, double maxPower) {
        leftMotorOne.setTargetPosition(targetPositions[0]);
        leftMotorTwo.setTargetPosition(targetPositions[0]);
        rightMotorOne.setTargetPosition(targetPositions[1]);
        rightMotorTwo.setTargetPosition(targetPositions[1]);

        leftMotorOne.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftMotorTwo.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorOne.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorTwo.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftMotorOne.setPower(maxPower);
        leftMotorTwo.setPower(maxPower);
        rightMotorOne.setPower(maxPower);
        rightMotorTwo.setPower(maxPower);
    }

}
