// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class WristCommand extends CommandBase {
  double speed;

  /** Creates a new IntakeWristCommand. */
  public WristCommand(double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.WristSubsystem);

    this.speed = speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.WristSubsystem.intakeWrist(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.WristSubsystem.wristReset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // if (Robot.IntakeWristSubsystem.wristLimitReached(20)) {
    //   return true;
    // }

    return false;
  }
}
