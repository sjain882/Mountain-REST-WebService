package mountain.mountainserver;

import java.util.Objects;

/**
 *
 */
public class Mountain {

    /* Attributes */
    
    // Name of the mountain
    private String name;

    // "Set" of mountains.
    private String mountainRange;

    // Top or bottom half of the Earth where the mountain is located.
    private String hemisphere;
    
    // Country the mountain range belongs to.
    private String country;
    
    // How tall in m the mountain is.
    private int height;
    

    /* Constants */

    private static final int HASH_CODE_MASTER_HASH = 3;
    private static final int NAME_HASH_CODE = 3; 
    private static final int MOUNTAINRANGE_HASH_CODE = 3;
    private static final int HEMISPHERE_HASH_CODE = 3;
    private static final int COUNTRY_HASH_CODE = 3;
    private static final int HEIGHT_HASH_CODE = 3;


    // Constructor
    public Mountain(String name, String mountainRange, String hemisphere, String country, int height) {
        this.name = name;
        this.mountainRange = mountainRange;
        this.hemisphere = hemisphere;
        this.country = country;
        this.height = height;
    }

    // Returns the name of the mountain.
    public String getName() {
        return name;
    }

    // Sets the name of the mountain to the specified value.
    public void setName(String name) {
        this.name = name;
    }

    // Returns the mountain range of the mountain.
    public String getMountainRange() {
        return mountainRange;
    }

    // Sets the mountain range of the mountain to the specified value.
    public void setMountainRange(String mountainRange) {
        this.mountainRange = mountainRange;
    }

    // Returns the hemisphere of the mountain.
    public String getHemisphere() {
        return hemisphere;
    }

    // Sets the hemisphere of the mountain to the specified value.
    public void setHemisphere(String hemisphere) {
        this.hemisphere = hemisphere;
    }

    // Returns the country of the mountain.
    public String getCountry() {
        return country;
    }

    // Sets the country of the mountain to the specified value.
    public void setCountry(String country) {
        this.country = country;
    }

    // Returns the height of the mountain.
    public int getHeight() {
        return height;
    }

    // Sets the height of the mountain to the specified value.
    public void setHeight(int height) {
        this.height = height;
    }
    
    // Custom implementation of equals to ensure equality between Objects
    // of this class can be reliably determined.
    @Override
    public boolean equals(Object o) {
        
        Mountain m = null;
        boolean returnVal = false;
        
        // If the object is not of a Mountain type, it can't be a mountain.
        // Otherwise, cast it to a Mountain for further comparisons.
        if (o.getClass() != Mountain.class) {
            return false;
        } else {
            m = (Mountain) o;
        }
        
        // Check if all attributes are equal between the Mountain objects.
        if ((m.getHeight() == this.getHeight())
             && (m.getCountry().equals(this.getCountry()))
             && (m.getMountainRange().equals(this.getMountainRange()))
             && (m.getName().equals(this.getName()))
             && (m.getHemisphere().equals(this.getHemisphere()))
             ) {
            
            returnVal = true;
            
        }
        
        return returnVal;
        
    }

    // Default implementation of hashCode() copied here since equals was 
    // overridden.
    @Override
    public int hashCode() {
        int hash = HASH_CODE_MASTER_HASH;
        hash = NAME_HASH_CODE * hash + Objects.hashCode(this.name);
        hash = MOUNTAINRANGE_HASH_CODE * hash + Objects.hashCode(this.mountainRange);
        hash = HEMISPHERE_HASH_CODE * hash + Objects.hashCode(this.hemisphere);
        hash = COUNTRY_HASH_CODE * hash + Objects.hashCode(this.country);
        hash = HEIGHT_HASH_CODE * hash + this.height;
        return hash;
    }
   
}
