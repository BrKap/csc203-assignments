import processing.core.PImage;

import java.util.List;

public abstract class HasAction extends HasAnimation {

    private double actionPeriod;

    public HasAction(Point position, List<PImage> images, int imageIndex, String id, String kind, double animationPeriod, double actionPeriod) {
        super(position, images, imageIndex, id, kind, animationPeriod);
        this.actionPeriod = actionPeriod;
    }

    public double getActionPeriod() {
        return actionPeriod;
    }

    public abstract void execute(WorldModel world, ImageStore imageStore, EventScheduler scheduler);
    public void scheduleAction(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
//        System.out.println("Animation is being scheduled");
        Action animation = new Animation(this, 0);
        scheduler.scheduleEvent(this, animation, getAnimationPeriod());

//        System.out.println("Action is being scheduled");

        Action activity = new Activity(this, world, imageStore);
        scheduler.scheduleEvent(this, activity, this.actionPeriod);
    }

}
