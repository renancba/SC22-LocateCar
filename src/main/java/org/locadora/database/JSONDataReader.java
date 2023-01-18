package org.locadora.database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.locadora.model.Agency;
import org.locadora.model.RentalOperation;
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

    List<RentalOperation> readOperations() throws IOException;
}

class JSONDataReader implements DataReader {
    private Path dbPath;
    private JSONObjectFactory objectFactory;

    public JSONDataReader(Path dbPath) {

        this.dbPath = dbPath;
        this.objectFactory = new JSONObjectFactory();
    }

    public List<Costumer> readCostumers() throws IOException {
        List<Costumer> costumers = new ArrayList<>();
        try {
            JSONObject costumersObject = new JSONObject(String.join(" ", Files.readAllLines(dbPath, StandardCharsets.UTF_8)));
            JSONArray costumersArray = (JSONArray) costumersObject.get("costumers");
            for (Object costumerObject : costumersArray) {
                JSONObject costumer = (JSONObject) costumerObject;
                costumers.add(objectFactory.createCostumer(costumer));
            }
            return costumers;
        } catch (JSONException jex) {
            return costumers;
        }
    }

    public List<RentalOperation> readOperations() throws IOException {
        List<RentalOperation> operations = new ArrayList<>();
        try {
            JSONObject operationsObject = new JSONObject(String.join(" ", Files.readAllLines(dbPath, StandardCharsets.UTF_8)));
            JSONArray operationsArray = (JSONArray) operationsObject.get("operations");
            for (Object operationObject : operationsArray) {
                JSONObject operation = (JSONObject) operationObject;
                operations.add(objectFactory.createOperation(operation));
            }
            return operations;
        } catch (JSONException jex) {
            return operations;
        }
    }

    public List<Vehicle> readVehicles() throws IOException {
        List<Vehicle> vehicles = new ArrayList<>();
        try {
            JSONObject vehiclesObject = new JSONObject(String.join(" ", Files.readAllLines(dbPath, StandardCharsets.UTF_8)));
            JSONArray vehiclesArray = (JSONArray) vehiclesObject.get("vehicles");
            for (Object vehicleObject : vehiclesArray) {
                JSONObject vehicle = (JSONObject) vehicleObject;
                vehicles.add(objectFactory.createVehicle(vehicle));
            }
            return vehicles;
        } catch (JSONException jex) {
            return vehicles;
        }
    }

    public List<Agency> readAgencies() throws IOException {
        List<Agency> agencies = new ArrayList<>();
        try {
            JSONObject agenciesObject = new JSONObject(String.join(" ", Files.readAllLines(dbPath, StandardCharsets.UTF_8)));
            JSONArray agenciesArray = (JSONArray) agenciesObject.get("agencies");
            for (Object agencyObject : agenciesArray) {
                JSONObject agency = (JSONObject) agencyObject;
                agencies.add(objectFactory.createAgency(agency));
            }
            return agencies;
        } catch (JSONException jex) {
            return agencies;
        }
    }
}
