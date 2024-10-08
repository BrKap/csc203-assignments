public class Animation extends Action {

    private int repeatCount;

    public Animation(Entity entity, int repeatCount) {
        super(entity);
        this.repeatCount = repeatCount;
    }

    @Override
    public void execute(EventScheduler scheduler) {
        this.getEntity().nextImage();

        if (this.repeatCount != 1) {
            HasAnimation animation = (HasAnimation)this.getEntity();
            Action animated = new Animation(this.getEntity(), Math.max(this.repeatCount - 1, 0));
            scheduler.scheduleEvent(this.getEntity(), animated, animation.getAnimationPeriod());
        }
    }
}
