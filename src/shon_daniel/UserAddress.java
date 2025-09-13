package shon_daniel;

public class UserAddress {

    private String country;
    private String city;
    private String streetName;
    private int buildingNumber;

    //--------------------------------- ctor --------------------------------------

    public UserAddress(String country, String city, String streetName, int BuildingNumber){
        this.country=country;
        this.city=city;
        this.streetName=streetName;
        this.buildingNumber=BuildingNumber;
    }

    //----------------------------------- getters --------------------------------
    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    //--------------------------------- toString --------------------------------------

    @Override
    public String toString() {
        return "UserAddress{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", streetName='" + streetName + '\'' +
                ", buildingNumber=" + buildingNumber +
                "}\n";
    }
}
