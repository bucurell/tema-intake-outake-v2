package org.firstinspires.ftc.teamcode.hardware

import com.qualcomm.hardware.rev.RevTouchSensor
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.hardware.TouchSensor

import org.firstinspires.ftc.robotcore.external.Telemetry
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter
import java.util.*
import kotlin.math.absoluteValue
import com.qualcomm.robotcore.hardware.DistanceSensor
import kotlin.coroutines.coroutineContext


/**
 * OutTake subsystem.
 *
 * This class controls the hardware for placing freight
 */

class Outtake(hwMap: HardwareMap) {
    companion object {
        const val clawOuttakeOpen = 0.9
        const val clawOuttakeClosed = 0.6

        const val armOuttakeGrab = 0.2
        const val armOuttakeDrop = 0.8

        const val sliderLeftInitial = 0
        const val sliderRightInitial = 0

        const val sliderLeftMiddle = 750
        const val sliderRightMiddle = 750

        const val sliderLeftFinal = 1500
        const val sliderRightFinal = 1500

        var isOpen:Boolean = false


        // New

        const val intakeServoLeft=1.0
        const val intakeServoRight=0.0


      /*** AUTO ***/
      //START





    }
    private val sliderLeft = hwMap.dcMotor["sliderLeft"]
    private val sliderRight = hwMap.dcMotor["sliderRight"]

    private val clawOuttake= hwMap.servo["clawOuttake"]
    private val armOuttake= hwMap.servo["armOuttake"]



    init {

        sliderLeft.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
        sliderLeft.direction = DcMotorSimple.Direction.FORWARD
        sliderLeft.mode = DcMotor.RunMode.RUN_USING_ENCODER
        sliderLeft.power = 0.0

        sliderRight.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
        sliderRight.direction = DcMotorSimple.Direction.REVERSE
        sliderRight.mode = DcMotor.RunMode.RUN_USING_ENCODER
        sliderRight.power = 0.0



    }

    //SLIDER positions are protected in order to remain private. That's why we use these 3 functions to determine where the slider is
    //relative to target position


    fun sliderDown {

        sliderLeft.targetPosition= sliderLeftInitial
        sliderRight.targetPosition= sliderRightInitial

        sliderLeft.mode=DcMotor.RunMode.RUN_TO_POSITION
        sliderRight.mode=DcMotor.RunMode.RUN_TO_POSITION

        sliderLeft.power=0.3 //de reglat
        sliderRight.power=0.3 //de reglat

        sliderRight.mode=DcMotor.RunMode.STOP_AND_RESET_ENCODER
        sliderLeft.mode=DcMotor.RunMode.STOP_AND_RESET_ENCODER

    }

    fun sliderUp() {
        sliderLeft.targetPosition= sliderLeftFinal
        sliderRight.targetPosition=sliderRightFinal

        sliderLeft.mode=DcMotor.RunMode.RUN_TO_POSITION
        sliderRight.mode=DcMotor.RunMode.RUN_TO_POSITION

        sliderLeft.power=0.3
        sliderRight.power=0.3
    }

    fun moveArmOuttakeUp() {

        armOuttake.position = armOuttakeDrop


    }

    fun moveArmOuttakeDown() {


        armOuttake.position = armOuttakeGrab

    }

    fun clawOuttakeGrab() {



        clawOuttake.position= clawOuttakeClosed
    }

    fun clawOuttakeDrop() {



        clawOuttake.position= clawOuttakeOpen
    }

    fun getPositionRelativeMedium(): Int {
        return sliderRightMiddle-sliderLeft.currentPosition
    }

    fun getPositionRelativeHigh(): Int {
        return sliderRightFinal-sliderLeft.currentPosition
    }










}