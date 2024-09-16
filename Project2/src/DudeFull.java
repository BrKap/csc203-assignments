import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DudeFull extends Dude {

    public DudeFull(Point position, List<PImage> images, int imageIndex, String id, String kind, double animationPeriod, double actionPeriod, int resourceLimit) {
        super(position, images, imageIndex, id, kind, animationPeriod, actionPeriod, resourceLimit);
    }


    @Override
    public void execute(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> fullTarget = world.findNearest(this.getPosition(), new ArrayList<>(Arrays.asList(House.HOUSE_KEY)));

        if (fullTarget.isPresent() && moveTo(world, fullTarget.get(), scheduler)) {
            this.transform(world, scheduler, imageStore);
        } else {
            Activity activity = new Activity(this, world, imageStore);
            scheduler.scheduleEvent( this, activity, this.getActionPeriod());
        }
    }

    @Override
    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        if (this.getPosition().adjacent(target.getPosition())) {
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
        Dude dude = new DudeNotFull(this.getPosition(), this.getImages(), 0, this.getId(),
                Dude.DUDE_KEY, this.getAnimationPeriod(), this.getActionPeriod(), this.getResourceLimit(), 0);

        world.removeEntity(scheduler, this);

        world.addEntity(dude);
        dude.scheduleAction(scheduler, world, imageStore);
        return true;
    }

}
