package entity;

public class Aircraft {

    @Override
    public String toString() {
        return "aircraftID:" + aircraftID + ", model:" + model + ", economySeats:" + economySeats + ", businessSeats:" + businessSeats + ", firstSeats:" + firstSeats + "\n";
    }

    private String aircraftID;
    private String model;
    private int economySeats;
    private int businessSeats;
    private int firstSeats;

    /**
     * Creates a new Aircraft object.
     * 
     * @param aircraftID
     * @param model
     * @param economySeats
     * @param businessSeats
     * @param firstSeats 
     */
    public Aircraft(String aircraftID, String model, int economySeats, int businessSeats, int firstSeats) {
        this.aircraftID = aircraftID;
        this.model = model;
        this.economySeats = economySeats;
        this.businessSeats = businessSeats;
        this.firstSeats = firstSeats;
    }

    public String getAircraftID() {
        return aircraftID;
    }

    public String getModel() {
        return model;
    }

    public int getEconomySeats() {
        return economySeats;
    }

    public int getBusinessSeats() {
        return businessSeats;
    }

    public int getFirstSeats() {
        return firstSeats;
    }
    
} // end class Aircraft
