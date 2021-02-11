package org.firstinspires.ftc.teamcode.library;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandGroupBase {

    private ArrayList<Integer> stages = new ArrayList<>();
    private ArrayList<CommandBase> commands = new ArrayList<>();
    private int nextStage = 0;
    private int currentStage;

    public void addSequential(CommandBase command) {
        stages.add(stages.get(stages.size() - 1) + 1);
        commands.add(command);
    }

    public void addParallel(CommandBase command) {
        stages.add(stages.get(stages.size() - 1));
        commands.add(command);
    }

    public void start() {
        scheduleStage();
    }

    public void periodic() {
        if (isStageComplete()) {
            scheduleStage();
        }
    }

    private boolean isStageComplete() {
        ArrayList<Boolean> completedCommands = new ArrayList<>();
        boolean isCompleted = true;
        for (int i = 0; i < stages.size(); i++) {
            if (stages.get(i) == currentStage) {
                completedCommands.add(commands.get(i).isFinished());
            }
        }
        for (Boolean isFinished : completedCommands) {
            if (!isFinished) {
                isCompleted = false;
            }
        }
        return isCompleted;
    }

    private void scheduleStage() {
        for (int i = 0; i < stages.size(); i++) {
            if (stages.get(i) == nextStage) {
                commands.get(i).start();
            }
        }
        currentStage = nextStage;
        nextStage++;
    }

    protected boolean isFinished() {
        boolean lastStage = stages.get(stages.size() - 1) == currentStage;
        return (stages.get(stages.size() - 1) == currentStage) && isStageComplete();
    }

}
