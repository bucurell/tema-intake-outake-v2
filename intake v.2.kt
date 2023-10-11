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
import org.firstinspires.ftc.teamcode.hardware.Outtake.Companion.isOpen

//dc e numele rosu la class?
class Intake (hwMap: HardwareMap) {
    companion object {
        const val clawIntakeopen = 0.78
        const val clawIntakeclosed = 0.34


        const val armIntake1 = 0.0
        const val armIntake2 = 0.5


    }

    private var armIntake = hwMap.servo["intakeArm"]
    private var clawIntake = hwMap.servo["intakeClaw"]

    init {

        armIntake.position= armIntake1
        clawIntake.position= clawIntakeopen


    }

    fun clawIntakeGrab() {

        clawIntake.position= clawIntakeclosed
    }

    fun clawIntakeDrop(){

        clawIntake.position= clawIntakeopen
    }

    fun armIntakeGrab() {

        armIntake.position= armIntake1
    }

    fun armIntakeDrop() {

        armIntake.position= armIntake2
    }









}