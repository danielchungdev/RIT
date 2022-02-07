package scheduling;

import backtracker.Configuration;

import java.util.Collection;

public class JobSchedulingConfic implements Configuration {


    @Override
    public Collection<Configuration> getSuccessors() {
        return null;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public boolean isGoal() {
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
