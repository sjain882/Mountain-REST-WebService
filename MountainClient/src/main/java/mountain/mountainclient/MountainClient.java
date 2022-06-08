/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mountain.mountainclient;

/**
 *
 */
public class MountainClient {
    
    public static void main(String[] args) {
        
        // Make a resource connector
        ResourceConnector connector = new ResourceConnector();
        
        
        // ==================================================================
        
        // Make mountain 1      
        String[] addParams = {"name","Snowden",
                              "range","Snowdonia",
                              "hemisphere","Northern",
                              "country","Wales",
                              "height","1080"};
        
        System.out.println();
        System.out.println(connector.postIt("add",
                                            addParams));
        
        
        // Make mountain 2      
        String[] addParams2 = {"name","Everest",
                              "range","Himalayas",
                              "hemisphere","Northern",
                              "country","China",
                              "height","8849"};
        
        System.out.println(connector.postIt("add",
                                            addParams2));
        
        // Make mountain with invalid integer height     
        String[] addParams3 = {"name","Everest",
                              "range","Himalayas",
                              "hemisphere","Northern",
                              "country","China",
                              "height","Text"};
        
        System.out.println(connector.postIt("add",
                                            addParams3));
        System.out.println();
        
        
        // ==================================================================
        
        
        // Get each mountain height by name, range and country
        String[] getParams1 = {"name","Snowden",
                              "range","Snowdonia",
                              "country","Wales"};

        System.out.println();
        System.out.println(connector.getIt("getHeightByNameRangeCountry",
                                            getParams1));


        // Get each mountain height by name, range and country
        String[] getParams2 = {"name","Everest",
                              "range","Himalayas",
                              "country","China"};

        System.out.println(connector.getIt("getHeightByNameRangeCountry",
                                            getParams2));
        
        
        // Get non-existent mountain height name, range and country
        String[] getParamsBad1 = {"name","Test",
                              "range","Junk",
                              "country","Fake"};

        System.out.println(connector.getIt("getHeightByNameRangeCountry",
                                            getParamsBad1));
        System.out.println();
        
        
        // ==================================================================
        
        
        // Get all mountains by range and country
        String[] getParams3 = {"range","Snowdonia",
                              "country","Wales"};

        System.out.println();
        System.out.println(connector.getIt("getAllbyRangeAndCountry",
                                            getParams3));
        
        // Get all mountains by range and country
        String[] getParams4 = {"range","Himalayas",
                              "country","China"};

        System.out.println(connector.getIt("getAllbyRangeAndCountry",
                                            getParams4));
        
        // Get all mountains by range and country - non existent
        String[] getParamsBad3 = {"range","Test",
                                    "country","Junk"};

        System.out.println(connector.getIt("getAllbyRangeAndCountry",
                                            getParamsBad3));
        System.out.println();
        
        
        // ==================================================================


        // Get all mountains by country
        String[] getParams99 = {"country","Wales"};

        System.out.println();
        System.out.println(connector.getIt("getAllByCountry",
                                    getParams99));

        // Get all mountains by country
        String[] getParams25 = {"country","China"};

        System.out.println(connector.getIt("getAllByCountry",
                                    getParams25));
        System.out.println();
        
        
        // ==================================================================
        
        
        // Get all mountains by hemisphere
        String[] getParams5 = {"hemisphere","Northern"};

        System.out.println();
        System.out.println(connector.getIt("getAllByHemisphere",
                                            getParams5));
        
        
        // Get all mountains by fake hemisphere - non existent
        String[] getParamsBad2 = {"hemisphere","Test"};
        System.out.println(connector.getIt("getAllByHemisphere",
                                            getParamsBad2));
        System.out.println();
        
        
        // ==================================================================
        
        
        // Get all mountains above a specified height
        String[] getParams6 = {"height","500"};

        System.out.println();
        System.out.println(connector.getIt("getAllOverHeight",
                                            getParams6));
        
        
        // Get all mountains above a specified height
        String[] getParams7 = {"height","2000"};

        System.out.println(connector.getIt("getAllOverHeight",
                                            getParams7));
        
        // Get all mountains above a specified height - invalid integer
        String[] badParam5 = {"height","Test"};

        System.out.println(connector.getIt("getAllOverHeight",
                                            badParam5));
        System.out.println();
        
        
        // ==================================================================
        
        
        // Update mountain 1
        String[] putParams = {"name","Snowden",
                              "range","Snowdonia",
                              "country","Wales",
                              "height","4"};
        
        System.out.println();
        System.out.println(connector.putIt("updateMountain",
                                            putParams));
        
        // Update mountain 2   
        String[] putParams66 = {"name","Everest",
                              "range","Himalayas",
                              "country","China",
                              "height","10000"};
        
        System.out.println(connector.putIt("updateMountain",
                                            putParams66));
        
        // Update non-existent mountain
        String[] putParams3 = {"name","Test",
                              "range","Junk",
                              "hemisphere","Northern",
                              "height","10000"};
        
        System.out.println(connector.putIt("updateMountain",
                                            putParams3));
        
        // Update existing mountain with non-integer height
        String[] putParams4 = {"name","Everest",
                              "range","Himalayas",
                              "hemisphere","Northern",
                              "height","Test"};
        
        System.out.println(connector.putIt("updateMountain",
                                            putParams4));
        System.out.println();
        
        
        
        
        // ==================================================================
        
        
        // Get each mountain height by name, range and country

        System.out.println();
        System.out.println(connector.getIt("getHeightByNameRangeCountry",
                                            getParams1));

        System.out.println(connector.getIt("getHeightByNameRangeCountry",
                                            getParams2));
        System.out.println();
        
        
        // ==================================================================
        
        
        // Get all mountains above a specified height
        String[] getParams8 = {"height","500"};

        System.out.println();
        System.out.println(connector.getIt("getAllOverHeight",
                                            getParams8));
        
        // Get all mountains above a specified height (invalid integer)
        String[] getParams9 = {"height","random text"};

        System.out.println(connector.getIt("getAllOverHeight",
                                            getParams9));
        System.out.println();
        
        
        // ==================================================================
        
        
        // Delete each mountain
        String[] deleteParams = {"name","Snowden",
                                 "range","Snowdonia",
                                 "country","Wales",};
        
        System.out.println();
        System.out.println(connector.deleteIt("deleteMountain",
                                              deleteParams));
        
        
        // Delete each mountain
        String[] deleteParams2 = {"name","Everest",
                                 "range","Himalayas",
                                 "country","China",};
        
        System.out.println(connector.deleteIt("deleteMountain",
                                              deleteParams2));
        
        
        // Delete non-existent mountain
        System.out.println(connector.deleteIt("deleteMountain",
                                              deleteParams));
        
        // Delete non-existent mountain
        System.out.println(connector.deleteIt("deleteMountain",
                                              deleteParams2));
        
        // Delete non-existent mountain
        String[] deleteParams3 = {"name","Junk",
                                 "range","Fake",
                                 "country","Non-existent",};
        
        System.out.println(connector.deleteIt("deleteMountain",
                                              deleteParams3));
        System.out.println();
        
        
        // ==================================================================
        
        
        // Get each mountain height by name, range and country

        System.out.println();
        System.out.println(connector.getIt("getHeightByNameRangeCountry",
                                            getParams1));

        System.out.println(connector.getIt("getHeightByNameRangeCountry",
                                            getParams2));
        System.out.println();
        
        
        // ==================================================================
        
        
        


        
    }
    
    
}