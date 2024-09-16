import processing.core.PImage;

import java.util.List;

public class Sapling extends HasHealth implements Transform {
    public static final String SAPLING_KEY = "sapling";
    public static final int SAPLING_NUM_PROPERTIES = 1;
    public static final double SAPLING_ACTION_ANIMATION_PERIOD = 1.000; // have to be in sync since grows and gains health at same time
    public static final int SAPLING_HEALTH = 0;
    public static final int SAPLING_HEALTH_LIMIT = 5;

    private int healthLimit;

    public Sapling(Point position, List<PImage> images, int imageIndex, String id, String kind, double animationPeriod, double actionPeriod, int health, int healthLimit) {
        super(position, images, imageIndex, id, kind, animationPeriod, actionPeriod, health);
        this.healthLimit = healthLimit;
    }


    @Override
    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (this.getHealth() <= 0) {
            Entity stump = new Stump(this.getPosition(), imageStore.getImageList(Stump.STUMP_KEY), 0, Stump.STUMP_KEY + "_" + this.getId(), Stump.STUMP_KEY);

            world.removeEntity(scheduler, this);

            world.addEntity(stump);

            return true;
        } else if (this.getHealth() >= this.healthLimit) {
            Tree tree = new Tree(this.getPosition(),
                    imageStore.getImageList(Tree.TREE_KEY), 0, Tree.TREE_KEY + "_" + this.getId(), Tree.TREE_KEY,
                    this.getPosition().getNumFromRange(Tree.TREE_ANIMATION_MAX, Tree.TREE_ANIMATION_MIN),
                    this.getPosition().getNumFromRange(Tree.TREE_ACTION_MAX, Tree.TREE_ACTION_MIN),
                    this.getPosition().getIntFromRange(Tree.TREE_HEALTH_MAX, Tree.TREE_HEALTH_MIN));

            world.removeEntity(scheduler, this);

            world.addEntity(tree);
            tree.scheduleAction(scheduler, world, imageStore);

            return true;
        }

        return false;
    }

    @Override
    public void execute(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        this.setHealth(this.getHealth() + 1);
        if (!this.transform(world, scheduler, imageStore)) {
            Activity activity = new Activity(this, world, imageStore);
            scheduler.scheduleEvent(this, activity, this.getActionPeriod());
        }
    }
}
