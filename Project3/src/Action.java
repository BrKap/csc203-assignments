public abstract class Action {

    private Entity entity;

    public Action(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return this.entity;
    }
    public abstract void execute(EventScheduler scheduler);
}
