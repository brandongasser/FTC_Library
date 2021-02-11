package org.firstinspires.ftc.teamcode.library;

import java.util.ArrayList;

public class Scheduler {

    private static ArrayList<CommandBase> commands = new ArrayList<>();
    private static ArrayList<SubsystemBase> subsystems = new ArrayList<>();
    private static ArrayList<CommandGroupBase> commandGroups = new ArrayList<>();

    protected static void scheduleCommand(CommandBase command) {
        for (SubsystemBase requiredSubsystem : command.getRequiredSubsystems()) {
            for (int i = 0; i < commands.size(); i++) {
                for (SubsystemBase subsystem : commands.get(i).getRequiredSubsystems()) {
                    if (requiredSubsystem == subsystem) {
                        commands.remove(i);
                    }
                }
            }
        }
        commands.add(command);
        command.initialize();
    }

    protected static void scheduleCommandGroup(CommandGroupBase commandGroup) {
        commandGroups.add(commandGroup);
        commandGroup.start();
    }

    protected static void addSubsystem(SubsystemBase subsystem) {
        subsystems.add(subsystem);
    }

    public static void cancelCommand(CommandBase command) {
        command.end();
        commands.remove(command);
    }

    public static void run() {
        for (CommandBase command : commands) {
            if (command.isFinished()) {
                command.end();
                commands.remove(command);
            } else {
                command.execute();
            }
        }
        for (SubsystemBase subsystem : subsystems) {
            subsystem.periodic();
        }
        for (CommandGroupBase commandGroup : commandGroups) {
            if (commandGroup.isFinished()) {
                commandGroups.remove(commandGroup);
            } else {
                commandGroup.periodic();
            }
        }
    }

}
