package org.locadora.database;

import org.json.JSONArray;
import org.json.JSONObject;
import org.locadora.model.Agency;
import org.locadora.model.costumer.Costumer;
import org.locadora.model.costumer.LegalPerson;
import org.locadora.model.costumer.NaturalPerson;
import org.locadora.model.vehicle.Vehicle;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private Path dbPath;
    private DataReader dataReader;
    private List<Costumer> costumers;
    private List<Vehicle> vehicles;
    private List<Agency> agencies;
    private static Database instance;

    private Database() {
        dbPath = Paths.get("src/database/database.json");
        this.dataReader = new JSONDataReader(dbPath);
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void init() throws IOException {
        costumers = dataReader.readCostumers();
        vehicles = dataReader.readVehicles();
        agencies = dataReader.readAgencies();
    }

    public Costumer getCustomer(int index) {
        return costumers.get(index);
    }

    public Agency getAgency(int index) {
        return agencies.get(index);
    }

    // TODO: VALIDAR SE O CASTING ESTÁ FUNCIONANDO

    public List<Costumer> searchCostumer(String value) {
        List<Costumer> matchCostumers = new ArrayList<>();

        for (Costumer costumer : costumers) {
            if (costumer instanceof NaturalPerson) {
                String fullName = costumer.getName() + " " + ((NaturalPerson) costumer).getSurname();
                if (fullName.contains(value)) {
                    matchCostumers.add(costumer);
                }
            } else {
                String fullName = costumer.getName() + " " + ((LegalPerson) costumer).getNickname();
                if (fullName.contains(value)) {
                    matchCostumers.add(costumer);
                }
            }
        }
        return matchCostumers;
    }

    public List<Vehicle> searchVehicles(String value) {
        List<Vehicle> matchVehicles = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            String fullName = vehicle.getVehicleManufacturer() + " " + vehicle.getVehicleModel();
            if (fullName.contains(value)) {
                matchVehicles.add(vehicle);
            }
        }
        return matchVehicles;
    }

    public List<Agency> searchAgencies(String value) {
        List<Agency> matchAgencies = new ArrayList<>();

        for (Agency agency : agencies) {
            String fullName = agency.getName() + " " + agency.getAddress();
            if (fullName.contains(value)) {
                matchAgencies.add(agency);
            }
        }
        return matchAgencies;
    }

    public List<Costumer> getCostumers() {
        return costumers;
    }

    public List<Agency> getAgencies() {
        return agencies;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public boolean addCostumer(Costumer costumer) { //RN6
        if (costumers.contains(costumer)) return false;

        costumers.add(costumer);
        return true;
    }

    public boolean addVehicle(Vehicle vehicle) { //RN1
        if (vehicles.contains(vehicle)) return false;

        vehicles.add(vehicle);
        return true;
    }

    public boolean addAgency(Agency agency) { //RN5
        if (agencies.contains(agency)) return false;

        agencies.add(agency);
        return true;
    }

    public void deleteAll() {
        costumers.clear();
    }

    public void remove(int index) {
        costumers.remove(index);
    }

    public void close() throws IOException {
        Files.newBufferedWriter(dbPath, StandardCharsets.UTF_8);
        JSONArray costumersArray = new JSONArray();

        System.out.println(costumers);
        for (Costumer costumer : costumers) {

            //Criar um if para null aqui e tratar as excessões
            JSONObject costumerObject = costumer.toJSONObject();
            System.out.println(costumer);
            costumersArray.put(costumerObject);
        }

        //Preciso tratar a null pointer aqui
        JSONObject object = new JSONObject().put("costumers", costumersArray).put("vehicles", new JSONArray()).put("agencies", new JSONArray());
        Files.writeString(dbPath, object.toString(), StandardOpenOption.WRITE);


    }
}


