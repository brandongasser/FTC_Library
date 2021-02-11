package org.firstinspires.ftc.teamcode.library;

import java.util.ArrayList;

public class CommandBase {

    private ArrayList<SubsystemBase> requiredSubsystems = new ArrayList<>();

    public void start() {
        Scheduler.scheduleCommand(this);
    }

    public void initialize() {
    }

    public void execute() {
    }

    public boolean isFinished() {
        return false;
    }

    public void end() {
    }

    private void addRequirement(SubsystemBase subsystem) {
        requiredSubsystems.add(subsystem);
    }

    protected ArrayList<SubsystemBase> getRequiredSubsystems() {
        return requiredSubsystems;
    }

}
