// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArmSubsystem extends SubsystemBase {

  CANSparkMax armMotorOne;
  CANSparkMax armMotorTwo;

  DigitalInput armLimitSwitch;
  DigitalInput armBottomSwitch;
  boolean armEnabled;

  RelativeEncoder armEncoderOne;
  RelativeEncoder armEncoderTwo;

  double rampValue;

  

  public ArmSubsystem() {
    armMotorOne = new CANSparkMax(RobotMap.armMotorOne, MotorType.kBrushless);
    armMotorTwo = new CANSparkMax(RobotMap.armMotorTwo, MotorType.kBrushless);
    armLimitSwitch = new DigitalInput(RobotMap.armLimitSwitch);
    armBottomSwitch = new DigitalInput(RobotMap.armBottomSwitch);
    armMotorTwo.setInverted(true);

    armEncoderOne = armMotorOne.getEncoder();
    armEncoderTwo = armMotorTwo.getEncoder();

    armEncoderOne.setPosition(0);
    armEncoderTwo.setPosition(0);

    // armMotorOne.setOpenLoopRampRate(rampValue);
    // armMotorTwo.setOpenLoopRampRate(rampValue);


    

    // rampValue = 0.5;
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("#1 arm position", armEncoderOne.getPosition());
    SmartDashboard.putNumber("#2 arm position", armEncoderTwo.getPosition());
    SmartDashboard.putBoolean("arm switch1 closed", armLimitSwitch.get());
    SmartDashboard.putBoolean("arm switch2 closed", armBottomSwitch.get());

  }

  public boolean encoderLimitReached(double setpoint) {
    double encoderPosition = armEncoderOne.getPosition();
    double error = Math.abs(encoderPosition - setpoint);

    if (error <= 1) {
      return true;
    } else {
      return false;
    }
  }

  // public double speedRamp(double speed) {
  //   if (speed > 0) {
  //     return 0.2 * (Math.pow(speed, 3)) + 0.8 * (Math.pow(speed, 2));
  //   } else {
  //     speed = speed * -1;
  //   }
  //   return -1 * (0.2 * (Math.pow(speed, 3)) + 0.8 * (Math.pow(speed, 2)));
  // }

  public void runArm(double speed) {
    // speed = speedRamp(speed);

    if (speed < 0) {
      if (bottomSwitchClosed()) {
        speed = 0;
      }
    } else if (speed > 0) {
      if (limitSwitchClosed()) {
        speed = 0;
      }
    }
    
    armMotorOne.set(speed);
    armMotorTwo.set(speed);

    
  //   SmartDashboard.putNumber("#1 arm velocity", armEncoderOne.getVelocity());
  //   SmartDashboard.putNumber("#2 arm velocity", armEncoderTwo.getVelocity());
  }

  public void armReset() {
    armMotorOne.set(0);
    armMotorTwo.set(0);
    // armEncoderOne.setPosition(0);
    // armEncoderTwo.setPosition(0);
  }

  public double encoderPosition() {
    return armEncoderOne.getPosition();
  }


  public boolean limitSwitchClosed() {
    // if (armLimitSwitch.get()) {
    //   return true;
    // } else {

    //   return false;
    // }
    return armLimitSwitch.get();
  } 

  public boolean bottomSwitchClosed() {
    // if (armBottomSwitch.get()) {
    //   return true;
    // } else {
    //   SmartDashboard.putBoolean("arm switch2 closed", false);
    //   return false;
    // }
    return armBottomSwitch.get();
  }

  // public boolean armEnabled(boolean enabled) {
  //   return enabled;
  // }

  public void armEncoderReset() {
    armEncoderOne.setPosition(0);
    armEncoderTwo.setPosition(0);
  }
  
}

  

