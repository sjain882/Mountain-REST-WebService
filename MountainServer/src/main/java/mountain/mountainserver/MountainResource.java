package mountain.mountainserver;

import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Root resource (exposed at "mountainresource" path)
 */
@Singleton
@Path("mountainresource")
public class MountainResource {   
  
    /* Attributes */

    // List that stores the mountains on the server
    private ArrayList<Mountain> mountainList = new ArrayList();


    /* Constants */

    private static final String INVALID_INTEGER = "Specified height"
                                                + " was not a valid integer";

    private static final String DUPLICATE_MOUNTAIN = "Mountain already exists";

    private static final String NONEXISTENT_MOUNTAIN = "Specified mountain"
									+ " does not exist";

    private static final String DELETE_MOUNTAIN_SUCCESS = "Successfully deleted"
									+ " Mountain with name ";

    private static final String UPDATE_MOUNTAIN_SUCCESS = "Successfully updated"
								    + " height of Mountain with name ";

    private static final String NO_MOUNTAINS = "No mountains have been added";

    private static final String MOUNTAIN_HEIGHT = "Height of specified mountain ";

    private static final String NO_MOUNTAINS_RANGE_COUNTRY = "No mountains with"
								+ " the specified range and country exist";

    private static final String NO_MOUNTAINS_HEMISPHERE = "There are no mountains"
							+ " within this hemisphere";

    private static final String NO_MOUNTAINS_COUNTRY = "There are no mountains"
								+ " within this country";

    private static final String NO_MOUNTAINS_ABOVE = "No mountains above the"
							+ " specified height of ";


    // Add a mountain
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/add")
    public Response addMountain(@QueryParam("name") String name,
                                @QueryParam("range") String range,
                                @QueryParam("hemisphere") String hemisphere,
                                @QueryParam("country") String country,
                                @QueryParam("height") String height) {
        
        int heightAsInt = 0;
        
        // Detect invalid integer height and return error if needed
        try {
            heightAsInt = Integer.parseInt(height);
        } catch (NumberFormatException ex) {
            
            return Response.status(Response.Status.BAD_REQUEST)
                                    .entity(INVALID_INTEGER)
                                    .type(MediaType.TEXT_PLAIN).build();
            
        }
        
        // Create the potential mountain object now so it's available for
        // comparisons
        Mountain proposedNewMountain = new Mountain(name,
                                                    range,
                                                    hemisphere,
                                                    country, heightAsInt);
                
        // If there is atleast one mountain already added...
        if (!mountainList.isEmpty()) {
            
            // ...check if any of those are conflicting
            for (Mountain currentMountain : mountainList) {
                if (currentMountain.equals(proposedNewMountain)) {

                    return Response.status(Response.Status.CONFLICT)
                                            .entity(DUPLICATE_MOUNTAIN)
                                            .type(MediaType.TEXT_PLAIN).build();

                }
            }            
        }
        
        // Should be fine if we got to here, so add the mountain
        mountainList.add(proposedNewMountain);
        
        // Success if the code reaches this point
        return Response
                .status(Response.Status.OK)
                .entity("Successfully added mountain with name " + name
                        + ", range " + range + ", " + hemisphere
                        + " hemisphere, country " + country + " and height of "
                        + height + "m")
                .type(MediaType.TEXT_PLAIN)
                .build();
        
    }
    

    // Delete a mountain
    @DELETE
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/deleteMountain")
    public Response deleteMountain(@QueryParam("name") String name,
                                    @QueryParam("range") String range,
                                    @QueryParam("country") String country) {
        
        
                
        boolean doesSpecifiedMountainExist = false;
        String mountainToDeleteName = "";
        // We copy the mountain list to prevent concurrent modification
        ArrayList<Mountain> copyMountainList = new ArrayList<>(mountainList);
                
        
        // If any of the stored mountains match the conditions,
        // that mountain
        for (Mountain currentMountain : mountainList) {
            if (currentMountain.getMountainRange().equalsIgnoreCase(range)
                && currentMountain.getCountry().equalsIgnoreCase(country)
                && currentMountain.getName().equalsIgnoreCase(name)) {
                
                // Remove from the copied list
                copyMountainList.remove(currentMountain);
                doesSpecifiedMountainExist = true;
                mountainToDeleteName = currentMountain.getName();
                
            }
        }
        
        // If the specified mountain does not exist
        if (!doesSpecifiedMountainExist) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(NONEXISTENT_MOUNTAIN)
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }

        // If the operation was successful, finalise the delete
        if (doesSpecifiedMountainExist) {
            mountainList = new ArrayList<>(copyMountainList);
        }
        
        // Success if the code reaches this point
        return Response
                .status(Response.Status.OK)
                .entity(DELETE_MOUNTAIN_SUCCESS + mountainToDeleteName)
                .type(MediaType.TEXT_PLAIN)
                .build();
        
    }


    // Update a mountain's height
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/updateMountain")
    public Response updateMountain(@QueryParam("name") String name,
                                    @QueryParam("range") String range,
                                    @QueryParam("country") String country,
                                    @QueryParam("height") String newHeight) {
        
        
               
        int heightAsInt = 0;
        boolean doesSpecifiedMountainExist = false;
        String mountainToUpdateName = "";
        int mountainToUpdateOrigHeight = 0;
        
        // Detect invalid integer height and return error if needed
        try {
            heightAsInt = Integer.parseInt(newHeight);
        } catch (NumberFormatException ex) {
            
            return Response.status(Response.Status.BAD_REQUEST)
                                    .entity(INVALID_INTEGER)
                                    .type(MediaType.TEXT_PLAIN).build();
            
        }
        
        
        // If any of the stored mountains match the conditions,
        // that mountain
        for (Mountain currentMountain : mountainList) {
            if (currentMountain.getMountainRange().equalsIgnoreCase(range)
                && currentMountain.getCountry().equalsIgnoreCase(country)
                && currentMountain.getName().equalsIgnoreCase(name)) {
                    
                // Store some original parameters of the mountain for output later
                mountainToUpdateOrigHeight = currentMountain.getHeight();
                currentMountain.setHeight(heightAsInt);
                doesSpecifiedMountainExist = true;
                mountainToUpdateName = currentMountain.getName();
                
            }
        }
        
        // If the specified mountain does not exist
        if (!doesSpecifiedMountainExist) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(NONEXISTENT_MOUNTAIN)
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }        
        
        // Success if the code reaches this point
        return Response
                .status(Response.Status.OK)
                .entity(UPDATE_MOUNTAIN_SUCCESS
                        + mountainToUpdateName + " from height "
                        + mountainToUpdateOrigHeight + "m to "
                        + heightAsInt + "m")
                .type(MediaType.TEXT_PLAIN)
                .build();
        
    }
    
    
    // Get mountain height by name, range, country  
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/getHeightByNameRangeCountry")
    public Response getHeightByNameRangeCountry(@QueryParam("name") String name,
                                        @QueryParam("range") String range,
                                        @QueryParam("country") String country) {
        
        int returnedHeight = 0;
        boolean doesSpecifiedMountainExist = false;
        
        // If there are no stored mountains, return 404 not found
        if (mountainList.isEmpty()) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(NO_MOUNTAINS)
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
        
        // If any of the stored mountains match the conditions, add them
        // to the return list
        for (Mountain currentMountain : mountainList) {
            if (currentMountain.getMountainRange().equalsIgnoreCase(range)
                && currentMountain.getCountry().equalsIgnoreCase(country)
                && currentMountain.getName().equalsIgnoreCase(name)) {
                
                returnedHeight = currentMountain.getHeight();
                doesSpecifiedMountainExist = true;
                
            }
        }
                        
        // If the specified mountain does not exist
        if (!doesSpecifiedMountainExist) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(NONEXISTENT_MOUNTAIN)
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }    
        
        // Return the list of mountains to return
        return Response
                .status(Response.Status.OK)
                .entity(MOUNTAIN_HEIGHT + name + " is "
                        + returnedHeight + "m")
                .type(MediaType.TEXT_PLAIN)
                .build();
        
    }
    
    
    // Get all mountains by range and country
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllbyRangeAndCountry")
    public Response getAllbyRangeAndCountry(@QueryParam("range") String range,
                                    @QueryParam("country") String country) {
        
        // List of mountains to return
        ArrayList<SimpleMountain> returnedList = new ArrayList<>();
        
        boolean doesAnyMountainExist = false;
        
        // If any of the stored mountains match the conditions, add them
        // to the return list
        for (Mountain currentMountain : mountainList) {
                        
            if ((currentMountain.getMountainRange().equalsIgnoreCase(range))
                && (currentMountain.getCountry().equalsIgnoreCase(country))) {
                                
                    returnedList.add(new SimpleMountain(currentMountain));
                    doesAnyMountainExist = true;
                
            }
        }
        
        // If the specified mountain does not exist
        if (!doesAnyMountainExist) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(NO_MOUNTAINS_RANGE_COUNTRY)
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
        
        // Return the list of mountains to return
        return Response
                .status(Response.Status.OK)
                .entity(returnedList)
                .type(MediaType.APPLICATION_JSON)
                .build();
        
    }
    
    
    // Get all mountains by country
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllByCountry")
    public Response getAllByCountry(@QueryParam("country") String country) {
        
        // List of mountains to return
        ArrayList<SimpleMountain> returnedList = new ArrayList<>();
        boolean foundMountainWithCountry = false;
        
        // If any of the stored mountains match the conditions, add them
        // to the return list
        for (Mountain currentMountain : mountainList) {
            if (currentMountain.getCountry().equalsIgnoreCase(country)) {
                
                returnedList.add(new SimpleMountain(currentMountain));
                foundMountainWithCountry = true;
                
            }
        }
        
        // If there are no mountains with this country
        if (!foundMountainWithCountry) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(NO_MOUNTAINS_COUNTRY)
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
        
        // Return the list of mountains to return
        return Response
                .status(Response.Status.OK)
                .entity(returnedList)
                .type(MediaType.APPLICATION_JSON)
                .build();
        
    }
    
    
    // Get all mountains by hemisphere
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllByHemisphere")
    public Response getAllByHemisphere(@QueryParam("hemisphere") String hemisphere) {
        
        // List of mountains to return
        ArrayList<SimpleMountain> returnedList = new ArrayList<>();
        boolean foundMountainWithHemisphere = false;
        
        // If any of the stored mountains match the conditions, add them
        // to the return list
        for (Mountain currentMountain : mountainList) {
            if (currentMountain.getHemisphere().equalsIgnoreCase(hemisphere)) {
                
                returnedList.add(new SimpleMountain(currentMountain));
                foundMountainWithHemisphere = true;
                
            }
        }
        
        // If there are no mountains with this hemisphere
        if (!foundMountainWithHemisphere) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(NO_MOUNTAINS_HEMISPHERE)
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
        
        // Return the list of mountains to return
        return Response
                .status(Response.Status.OK)
                .entity(returnedList)
                .type(MediaType.APPLICATION_JSON)
                .build();
        
    }

    
    // Get all mountains over a certain height
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllOverHeight")
    public Response getAllOverHeight(@QueryParam("height") String height) {
        
        int heightAsInt = 0;
        boolean doAnyMountainsAboveHeightExist = false;
        
        // Detect invalid integer height and return error if needed
        try {
            heightAsInt = Integer.parseInt(height);
        } catch (NumberFormatException ex) {
            
            return Response.status(Response.Status.BAD_REQUEST)
                                    .entity(INVALID_INTEGER)
                                    .type(MediaType.TEXT_PLAIN).build();
            
        }
        
        
        // List of mountains to return
        ArrayList<SimpleMountain> returnedList = new ArrayList<>();
        
        // If any of the stored mountains match the conditions, add them
        // to the return list
        for (Mountain currentMountain : mountainList) {
            if (currentMountain.getHeight() > heightAsInt) {
                
                returnedList.add(new SimpleMountain(currentMountain));
                doAnyMountainsAboveHeightExist = true;
                
            }
        }
        
        // If the specified mountain does not exist
        if (!doAnyMountainsAboveHeightExist) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(NO_MOUNTAINS_ABOVE
                            + height + "m exist")
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
        
        // Return the list of mountains to return
        return Response
                .status(Response.Status.OK)
                .entity(returnedList)
                .type(MediaType.APPLICATION_JSON)
                .build();
        
    }

}
