package org.locadora.database;



import org.json.JSONArray;
import org.json.JSONObject;
import org.locadora.model.Agency;
import org.locadora.model.costumer.Costumer;
import org.locadora.model.costumer.LegalPerson;
import org.locadora.model.costumer.NaturalPerson;
import org.locadora.model.vehicle.Motorcycle;
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

    //seria o caso de trabalharmos com generics aqui pra fazer essa leitura independente do tipo?
    private Path dbPath;
    private List<Costumer> costumers;
    private List<Vehicle> vehicles;
    private List<Agency> agencies;
    private static Database instance;

    private Database() {
        dbPath = Paths.get("src/database/database.json");
        costumers = new ArrayList<>();
        vehicles = new ArrayList<>();
        agencies = new ArrayList<>();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    //TODO: INICIAR COM CONTATO, VEÍCULO OU AGÊNCIA

    public void init() throws IOException {
        JSONObject costumersObject = new JSONObject(String.join(" ", Files.readAllLines(dbPath, StandardCharsets.UTF_8)));
        JSONArray costumersArray = (JSONArray) costumersObject.get("costumers");
        for (Object costumertObject : costumersArray) {

            JSONObject costumer = (JSONObject) costumertObject;

            //CONDIÇÃO SE PESSOA FÍSICA OU PESSOA JURÍDICA
            //Pessoa Física
            String name = (String) costumer.get("name");
            String surname = (String) costumer.get("surname");
            String cpf = (String) costumer.get("cpf");
            String driverLicense = (String) costumer.get("driverLicense");
            Costumer natualPerson = new NaturalPerson(name, surname, cpf, driverLicense);
            costumers.add(natualPerson);

            //TODO: USEI LEGALPERSON SÓ PARA INSTANCIAR E NÃO DAR ERRO DE EXECUÇÃO

            Costumer legalPerson = new LegalPerson();
            costumers.add(legalPerson);
        }

        JSONObject agenciesObject = new JSONObject(String.join(" ", Files.readAllLines(dbPath, StandardCharsets.UTF_8)));
        JSONArray agenciesArray = (JSONArray) costumersObject.get("agencies");
        for (Object agencyObject : agenciesArray) {

            JSONObject agency = (JSONObject) agencyObject;

            String agencyName = (String) agency.get("name");
            Agency newAgency = new Agency(agencyName);
            agencies.add(newAgency);
        }
        JSONObject vehiclesObject = new JSONObject(String.join(" ", Files.readAllLines(dbPath, StandardCharsets.UTF_8)));
        JSONArray vehiclesArray = (JSONArray) costumersObject.get("vehicles");
        for (Object vehicleObject : vehiclesArray) {

            JSONObject vehicle = (JSONObject) vehicleObject;

            String vehicleManufacturer = (String) vehicle.get("manufacturer");
            String vehicleModel = (String) vehicle.get("model");
            String vehicleRegPlate = (String) vehicle.get("registration plate");
            //TODO: USEI MOTORCYCLE SÓ PARA INSTANCIAR E NÃO DAR ERRO DE EXECUÇÃO
            Vehicle newVehicle = new Motorcycle(vehicleManufacturer, vehicleModel, vehicleRegPlate);
            vehicles.add(newVehicle);
        }
    }

    public Costumer get(int index) {
        return costumers.get(index);
    }

    // TODO: VALIDAR SE O CASTING ESTÁ FUNCIONANDO

    public List<Costumer> searchCostumer(String value) {
        List<Costumer> matchCostumers = new ArrayList<>();

        for (Costumer costumer : costumers) {
            if (costumer instanceof NaturalPerson) {
                String fullName = costumer.getName() + " " + ((NaturalPerson)costumer).getSurname();
                if (fullName.contains(value)) {
                    matchCostumers.add(costumer);
                }
            } else {
                String fullName = costumer.getName() + " " + ((LegalPerson)costumer).getNickname();
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
        Files.newBufferedWriter(dbPath);
        JSONArray costumersArray = new JSONArray();

        for (Costumer costumer : costumers) {
            JSONObject costumerObject = new JSONObject();
            costumerObject.put("name", costumer.getName());
            if (costumer instanceof NaturalPerson) {
                costumerObject.put("surname", ((NaturalPerson) costumer).getSurname());
            } else {
                costumerObject.put("surname", ((LegalPerson) costumer).getNickname());
            }
            costumerObject.put("address", costumer.getAddress());
            costumerObject.put("telephone", costumer.getTelephone());
            costumersArray.put(costumerObject);
        }

        JSONObject object = new JSONObject().put("costumers", costumersArray);

        Files.writeString(dbPath, object.toString(), StandardOpenOption.WRITE);
    }
}