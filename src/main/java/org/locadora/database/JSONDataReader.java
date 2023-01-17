package org.locadora.database;
import org.json.JSONArray;
import org.json.JSONObject;
import org.locadora.model.Agency;
import org.locadora.model.costumer.Costumer;
import org.locadora.model.vehicle.Vehicle;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

interface DataReader {
    List<Costumer> readCostumers() throws IOException;

    List<Vehicle> readVehicles() throws IOException;

    List<Agency> readAgencies() throws IOException;
}

class JSONDataReader implements DataReader {
    private Path dbPath;

    public JSONDataReader(Path dbPath) {
        this.dbPath = dbPath;
    }
    public List<Costumer> readCostumers() throws IOException {
        List<Costumer> costumers = new ArrayList<>();
        JSONObject costumersObject = new JSONObject(String.join(" ", Files.readAllLines(dbPath, StandardCharsets.UTF_8)));
        JSONArray costumersArray = (JSONArray) costumersObject.get("costumers");
        for (Object costumerObject : costumersArray) {
            JSONObject costumer = (JSONObject) costumerObject;
            costumers.add(ObjectFactory.createCostumer(costumer));
        }
        return costumers;
    }

    public List<Vehicle> readVehicles() throws IOException {
        List<Vehicle> vehicles = new ArrayList<>();
        JSONObject vehiclesObject = new JSONObject(String.join(" ", Files.readAllLines(dbPath, StandardCharsets.UTF_8)));
        JSONArray vehiclesArray = (JSONArray) vehiclesObject.get("vehicles");
        for (Object vehicleObject : vehiclesArray) {
            JSONObject vehicle = (JSONObject) vehicleObject;
            vehicles.add(ObjectFactory.createVehicle(vehicle));
        }
        return vehicles;
    }

    public List<Agency> readAgencies() throws IOException {
        List<Agency> agencies = new ArrayList<>();
        JSONObject agenciesObject = new JSONObject(String.join(" ", Files.readAllLines(dbPath, StandardCharsets.UTF_8)));
        JSONArray agenciesArray = (JSONArray) agenciesObject.get("agencies");
        for (Object agencyObject : agenciesArray) {
            JSONObject agency = (JSONObject) agencyObject;
            agencies.add(ObjectFactory.createAgency(agency));
        }
        return agencies;
    }
}
