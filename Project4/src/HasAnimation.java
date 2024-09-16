import processing.core.PImage;

import java.util.List;

public abstract class HasAnimation extends Entity {

    private double animationPeriod;


    public HasAnimation(Point position, List<PImage> images, int imageIndex, String id, String kind, double animationPeriod) {
        super(position, images, imageIndex, id, kind);
        this.animationPeriod = animationPeriod;
    }

    public double getAnimationPeriod() {
        return animationPeriod;
    }


    public void scheduleAction(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {

        Action animation = new Animation(this, 0);
        scheduler.scheduleEvent(this, animation, getAnimationPeriod());
    }
}
