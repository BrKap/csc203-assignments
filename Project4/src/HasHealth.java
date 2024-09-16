import processing.core.PImage;

import java.util.List;

public abstract class HasHealth extends HasAction {

    private int health;

    public HasHealth(Point position, List<PImage> images, int imageIndex, String id, String kind, double animationPeriod, double actionPeriod, int health) {
        super(position, images, imageIndex, id, kind, animationPeriod, actionPeriod);
        this.health = health;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
