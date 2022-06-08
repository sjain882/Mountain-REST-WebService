package mountain.mountainserver;

/**
 *
 */
public class SimpleMountain {

    /* Attributes */
    
    // Name of the mountain
    private String name;
    
    // How tall in m the mountain is.
    private int height;

    // Constructor
    public SimpleMountain(String name, int height) {
        this.name = name;
        this.height = height;
    }
    
    // Copy constructor for Mountain class
    public SimpleMountain(Mountain m) {
        this.name = m.getName();
        this.height = m.getHeight();
    }

    // Returns the name of the mountain.
    public String getName() {
        return name;
    }

    // Sets the name of the mountain to the specified value.
    public void setName(String name) {
        this.name = name;
    }

    // Returns the height of the mountain.
    public int getHeight() {
        return height;
    }

    // Sets the height of the mountain to the specified value.
    public void setHeight(int height) {
        this.height = height;
    }
}
