package org.firstinspires.ftc.teamcode.library;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * A class used to manage driving a Mecanum drive train
 */
public class MecanumDrive {

    private final DcMotor leftFrontMotor;
    private final DcMotor rightFrontMotor;
    private final DcMotor leftBackMotor;
    private final DcMotor rightBackMotor;

    /**
     * @param leftFrontMotor DcMotor at the front left of the drive train
     * @param rightFrontMotor DcMotor at the front right of the drive train
     * @param leftBackMotor DcMotor at the back left of the drive train
     * @param rightBackMotor DcMotor at the back right of the drive train
     */
    public MecanumDrive(DcMotor leftFrontMotor, DcMotor rightFrontMotor,
                        DcMotor leftBackMotor, DcMotor rightBackMotor) {
        this.leftFrontMotor = leftFrontMotor;
        this.rightFrontMotor = rightFrontMotor;
        this.leftBackMotor = leftBackMotor;
        this.rightBackMotor = rightBackMotor;

        rightFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBackMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    /**
     * Move the drive train with a speed, strafe, and rotation.
     * Make sure the drive train is in the correct mode before using
     *
     * @param speed how fast to move the drive train forwards and backwards (positive values are forwards) [-1..1]
     * @param strafe how fast to move the drive train left and right (positive values are right) [-1..1]
     * @param rotation how fast to turn the drive train (positive values are clockwise) [-1..1]
     */
    public void drive(double speed, double strafe, double rotation) {
        drive(speed, strafe, rotation, false);
    }

    /**
     * Move the drive train with a speed, strafe, and rotation
     * Make sure the drive train is in the correct mode before using
     *
     * @param speed how fast to move the drive train forwards and backwards (positive values are forwards) [-1..1]
     * @param strafe how fast to move the drive train left and right (positive values are right) [-1..1]
     * @param rotation how fast to turn the drive train (positive values are clockwise) [-1..1]
     * @param squareInputs speed, strafe, and rotation will be squared (but keep their - sign) if set to true.
     *                     this makes driving with a controller at slower speeds more precise, but doesn't limit top speed
     */
    public void drive(double speed, double strafe, double rotation, boolean squareInputs) {
        if (squareInputs) {
            speed = Math.copySign(speed * speed, speed);
            strafe = Math.copySign(strafe * strafe, strafe);
            rotation = Math.copySign(rotation * rotation, rotation);
        }
        leftFrontMotor.setPower(speed + strafe + rotation);
        rightFrontMotor.setPower(speed - strafe - rotation);
        leftBackMotor.setPower(speed - strafe + rotation);
        rightBackMotor.setPower(speed + strafe - rotation);
    }

    /**
     * Sets all drive train motors to RUN_WITHOUT_ENCODER run mode
     */
    public void switchToPowerMode() {
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    /**
     * Sets all drive train motors to RUN_USING_ENCODER run mode
     */
    public void switchToVelocityMode() {
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * Resets all drive train encoders
     * WARNING: this will stop the drive train
     * After using this method, put drive train motors back into desired run mode
     */
    public void resetEncoders() {
        leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    /**
     * Moves all drive train motors to given target positions using PID
     * After using this method, put drive train motors back into desired run mode
     *
     * @param targets ordered list of target positions [leftFront, rightFront, leftBack, rightBack]
     * @param maxPower the maximum power the motors will be able to run at [-1..1]
     */
    public void runToPositions(int[] targets, double maxPower) {
        leftFrontMotor.setTargetPosition(targets[0]);
        rightFrontMotor.setTargetPosition(targets[1]);
        leftBackMotor.setTargetPosition(targets[2]);
        rightBackMotor.setTargetPosition(targets[3]);

        leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftFrontMotor.setPower(maxPower);
        rightFrontMotor.setPower(maxPower);
        leftBackMotor.setPower(maxPower);
        rightBackMotor.setPower(maxPower);
    }

}
