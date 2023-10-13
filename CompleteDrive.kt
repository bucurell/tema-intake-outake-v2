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

    var Outtakeopen= false


    override fun Hardware.run() {

        val gp1 = Gamepad(gamepad1)
        val gp2 = Gamepad(gamepad2)



        //TODO Adjust these variables according to the needs
        var clawIntakeOpen= false
        var clawOuttakeOpen= false
        var armIntakeUp= false
        var armOuttakeUp= false

        waitForStart()
        while(opModeIsActive())
        {
            val power = speed
            val rotPower = rotation
            hw.motors.move(direction, power, rotPower)



            // Cod de Drive

            if (gp2.checkToggle(Gamepad.Button.X)){
               if (clawIntakeOpen==false){

                intake.clawIntakeDrop()
                   clawIntakeOpen=true
               }

                else {
                    intake.clawIntakeGrab()
                   clawIntakeOpen=false
                }
            }



            if (gp2.checkToggle(Gamepad.Button.A)){
                if (armIntakeUp==false) {
                    intake.armIntakeDrop()
                    armIntakeUp=true
                }
                else{
                    intake.armIntakeGrab()
                    armIntakeUp=false
                }
            }

            if (gp2.checkToggle(Gamepad.Button.Y)){
                outtake.moveArmOuttakeDown()
            }

            if (gp2.checkToggle(Gamepad.Button.DPAD_DOWN)){
                if (clawOuttakeOpen==false) {
                    clawOuttakeDrop()
                    clawOuttakeOpen=true
                }
                else {
                    intake.clawOuttakeGrab()
                    clawOuttakeOpen=false
                }
            }



            if (gp2.checkToggle(Gamepad.Button.RIGHT_BUMPER)){
                outtake.sliderUp()
                armOuttakeUp=true
            }

            if (outtake.getPositionRelativeMedium() >= 750 ){
                outtake.moveArmOuttakeUp()
            }

            if (outtake.getPositionRelativeHigh() >= 1500)  {

              outtake.clawOuttakeDrop()

            }

        if (gp2.checkToggle(Gamepad.Button.LEFT_BUMPER)){
            outtake.sliderDown()
            outtake.moveArmOuttakeDown()
            intake.armIntakeGrab()
            intake.clawIntakeDrop()
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