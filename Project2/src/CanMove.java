public interface CanMove {


    public Point nextPosition(WorldModel world, Point destPos);
    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler);
}
