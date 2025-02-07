/* Black Knights Robotics (C) 2025 */
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.ClimberConstants;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.utils.ConfigManager;

public class ClimberCommand extends Command {
    private final ClimberSubsystem climberSubsystem;

    private final ConfigManager configManager = ConfigManager.getInstance();

    private final boolean goingUp;

    public ClimberCommand(ClimberSubsystem climberSubsystem, boolean goingUp) {
        this.climberSubsystem = climberSubsystem;
        addRequirements(climberSubsystem);
        this.goingUp = goingUp;
    }

    @Override
    public void execute() {
        if (goingUp) {
            climberSubsystem.lockRotary(
                    configManager.get("rotary_voltage", ClimberConstants.ROTARY_VOLTAGE));
            climberSubsystem.setJointAngle(
                    configManager.get("climb_angle", ClimberConstants.CLIMB_ANGLE));
        } else {
            climberSubsystem.unlockRotary(
                    configManager.get("rotary_voltage", ClimberConstants.ROTARY_VOLTAGE));
            climberSubsystem.setJointAngle(
                    configManager.get("climb_angle", ClimberConstants.REST_ANGLE));
        }
    }

    @Override
    public void initialize() {}
}
