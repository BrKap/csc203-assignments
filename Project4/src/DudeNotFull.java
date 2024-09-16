import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DudeNotFull extends Dude {

    private int resourceCount;

    public DudeNotFull(Point position, List<PImage> images, int imageIndex, String id, String kind, double animationPeriod,
                       double actionPeriod, int resourceLimit, int resourceCount, PathingStrategy strategy) {
        super(position, images, imageIndex, id, kind, animationPeriod, actionPeriod, resourceLimit, strategy);
        this.resourceCount = resourceCount;
    }

    public int getResourceCount() {
        return this.resourceCount;
    }

    public void setResourceCount(int value) {
        this.resourceCount = value;
    }

    @Override
    public void execute(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> target = world.findNearest(this.getPosition(), new ArrayList<>(Arrays.asList(Tree.TREE_KEY, Sapling.SAPLING_KEY)));

        if (target.isEmpty() || !moveTo(world, target.get(), scheduler) || !this.transform(world, scheduler, imageStore)) {
            Activity activity = new Activity(this, world, imageStore);
            scheduler.scheduleEvent( this, activity, this.getActionPeriod());
        }
    }


    @Override
    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        if (this.getPosition().adjacent(target.getPosition())) {
            this.setResourceCount(this.getResourceCount() + 1);
            ((HasHealth)target).setHealth(((HasHealth) target).getHealth() - 1);
            return true;
        } else {
            Point nextPos = nextPosition(world, target.getPosition());

            if (!this.getPosition().equals(nextPos)) {
                world.moveEntity(scheduler, this, nextPos);
            }
            return false;
        }
    }

    @Override
    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (this.getResourceCount() >= this.getResourceLimit()) {
            Dude dude = new DudeFull(this.getPosition(), this.getImages(), 0, this.getId(),
                    Dude.DUDE_KEY, this.getAnimationPeriod(), this.getActionPeriod(), this.getResourceLimit(), Dude.DUDE_STRATEGY);

            world.removeEntity(scheduler, this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(dude);
            dude.scheduleAction(scheduler, world, imageStore);

            return true;
        }

        return false;
    }

}
