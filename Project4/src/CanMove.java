import processing.core.PImage;

import java.util.List;

public abstract class CanMove extends HasAction{

    private PathingStrategy strategy;
    public CanMove(Point position, List<PImage> images, int imageIndex, String id, String kind, double animationPeriod, double actionPeriod, PathingStrategy strategy) {
        super(position, images, imageIndex, id, kind, animationPeriod, actionPeriod);
        this.strategy = strategy;
    }

    public PathingStrategy getStrategy() {
        return this.strategy;
    }

    public static boolean neighbors(Point p1, Point p2)   {
        return p1.getX()+1 == p2.getX() && p1.getY() == p2.getY() ||
                p1.getX()-1 == p2.getX() && p1.getY() == p2.getY() ||
                p1.getX() == p2.getX() && p1.getY()+1 == p2.getY() ||
                p1.getX() == p2.getX() && p1.getY()-1 == p2.getY();
    }

    public abstract Point nextPosition(WorldModel world, Point destPos);
    public abstract boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler);
}
