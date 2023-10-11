package org.firstinspires.ftc.teamcode

import android.service.autofill.Validators.and
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DistanceSensor
import com.qualcomm.robotcore.util.ElapsedTime
import com.qualcomm.robotcore.hardware.TouchSensor
import org.firstinspires.ftc.teamcode.hardware.Hardware
import org.firstinspires.ftc.teamcode.hardware.Intake
import org.firstinspires.ftc.teamcode.hardware.Outtake
import java.lang.Math.atan2

@TeleOp(name = "CompleteDrive", group = "Main")
class CompleteDrive: OpMode() {

    override fun preInit() {
    }
    override fun preInitLoop() {
        telemetry.addLine("Waiting for start...")
        telemetry.update()
        idle()
    }




    override fun Hardware.run() {

        val gp1 = Gamepad(gamepad1)
        val gp2 = Gamepad(gamepad2)



        //TODO Adjust these variables according to the needs
        var outtakeClawOpen= false
        var intakeClawOpen= false
        var outtakeSliderOpen= false
        waitForStart()
        while(opModeIsActive())
        {
            val power = speed
            val rotPower = rotation
            hw.motors.move(direction, power, rotPower)



            // Cod de Drive

            if (gp2.checkToggle(Gamepad.Button.X)){
                if (intakeClawOpen=true) {
                    intake.clawIntakeDrop()
                   intakeClawOpen=false
                }
                else {
                    intake.clawIntakeGrab()
                    intakeClawOpen=true
                }
            }


            if (gp2.checkToggle(Gamepad.Button.A)){

                intake.armIntakeDrop()
            }

            if (gp2.checkToggle(Gamepad.Button.Y)){
                outtake.moveArmOuttakeDown()
            }

            if (gp2.checkToggle(Gamepad.Button.DPAD_DOWN)){
               if (outtakeClawOpen=true) {
                   outtake.clawOuttakeDrop()

                   outtakeClawOpen=false
               }

               else {
                   outtake.clawOuttakeGrab()

                   outtakeClawOpen=true
               }
            }



            if (gp2.checkToggle(Gamepad.Button.RIGHT_BUMPER)){
                outtake.sliderUp()
                outtakeSliderOpen= true
            }

            if (outtake.getPositionRelativeMedium() >= 750 ) {
                if(outtakeSliderOpen= false){outtake.moveArmOuttakeUp()}
            }

            if ((outtake.getPositionRelativeHigh() >= 1500) and !(outtakeClawOpen=true)) {
                outtake.clawOuttakeDrop()
            }

        if (gp2.checkToggle(Gamepad.Button.LEFT_BUMPER)){
            outtake.sliderDown()
            outtake.moveArmOuttakeDown()
            intake.armIntakeGrab()
            intake.clawIntakeDrop()
            outtakeSliderOpen= false
        }

            if (gp2.checkToggle(Gamepad.Button.A)) {
                intake.armIntakeGrab()
            }








        }
    }

    ///The direction in which the robot is translating
    private val direction: Double
        get() {
            val x = -gamepad1.left_stick_x.toDouble()  // -
            val y = -gamepad1.left_stick_y.toDouble() // +

            return atan2(y, x) / Math.PI * 180.0 - 90.0
        }

    /// Rotation around the robot's Z axis.
    private val rotation: Double
        get() = -gamepad1.right_stick_x.toDouble()  // -

    /// Translation speed.
    private val speed: Double
        get() {
            val x = gamepad1.left_stick_x.toDouble() //+
            val y = gamepad1.left_stick_y.toDouble() //+

            return Math.sqrt((x * x) + (y * y))
        }









    }









}