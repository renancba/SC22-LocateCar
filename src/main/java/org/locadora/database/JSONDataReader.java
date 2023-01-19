package org.locadora.database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.locadora.model.Agency;
import org.locadora.model.RentalOperation;
import org.locadora.model.customer.Customer;
import org.locadora.model.vehicle.Vehicle;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

interface DataReader {
    List<Customer> readCustomers() throws IOException;

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

    public List<Customer> readCustomers() throws IOException {
        List<Customer> customers = new ArrayList<>();
        try {
            JSONObject customersObject = new JSONObject(String.join(" ", Files.readAllLines(dbPath, StandardCharsets.UTF_8)));
            JSONArray customersArray = (JSONArray) customersObject.get("customers");
            for (Object customerObject : customersArray) {
                JSONObject customer = (JSONObject) customerObject;
                customers.add(objectFactory.createCustomer(customer));
            }
            return customers;
        } catch (JSONException jex) {
            return customers;
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
