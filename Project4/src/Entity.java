import processing.core.PImage;
import java.util.*;

public class Entity {

    public static final int PROPERTY_KEY = 0;
    public static final int PROPERTY_ID = 1;
    public static final int PROPERTY_COL = 2;
    public static final int PROPERTY_ROW = 3;
    public static final int ENTITY_NUM_PROPERTIES = 4;

    private Point position;
    private List<PImage> images;
    private int imageIndex;
    private String id;
    private String kind;
    private boolean recentlySpawned = false;


    public Entity(Point position, List<PImage> images, int imageIndex, String id, String kind) {
        this.position = position;
        this.images = images;
        this.imageIndex = imageIndex;
        this.id = id;
        this.kind = kind;
    }

    public void setRecentlySpawned(boolean recentlySpawned) {
        this.recentlySpawned = recentlySpawned;
    }
    public boolean getRecentlySpawned() {return recentlySpawned;}

    public String getKind() {
        return this.kind;
    }
    public List<PImage> getImages() {
        return this.images;
    }
    public void nextImage() {
        this.imageIndex++;
    }

    public PImage getCurrentImage() {

        return this.images.get(this.imageIndex % this.images.size());
    }

    public Point getPosition() {
        return this.position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public String getId() {
        return this.id;
    }

    public void addId(String id) {
        this.id = id + "_" + this.getId();
    }


    /**
     * Helper method for testing. Preserve this functionality while refactoring.
     */
    public String log(){
        return this.id.isEmpty() ? null :
                String.format("%s %d %d %d", this.id, this.position.getX(),
                        this.position.getY(), this.imageIndex);
    }
}
