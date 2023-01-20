package org.locadora.database;

import org.json.JSONArray;
import org.json.JSONObject;
import org.locadora.model.Agency;
import org.locadora.model.customer.Customer;
import org.locadora.model.customer.LegalPerson;
import org.locadora.model.customer.NaturalPerson;
import org.locadora.model.RentalOperation;
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
    private List<Customer> customers;
    private DataReader dataReader;
    private List<Vehicle> vehicles;
    private List<Agency> agencies;
    private List<RentalOperation> operations;
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
        customers = dataReader.readCustomers();
        vehicles = dataReader.readVehicles();
        agencies = dataReader.readAgencies();
        operations = dataReader.readOperations();
    }

    public Customer getCustomer(int index) {
        return customers.get(index);
    }

    public RentalOperation getOperation(int index) {
        return getOperations().get(index);
    }

    public Agency getAgency(int index) {
        return agencies.get(index);
    }

    // TODO: VALIDAR SE O CASTING ESTÁ FUNCIONANDO

    public List<Customer> searchCustomer(String value) {
        List<Customer> matchCustomers = new ArrayList<>();

        for (Customer customer : customers) {
            if (customer instanceof NaturalPerson) {
                String fullName = customer.getName() + " " + ((NaturalPerson) customer).getSurname();
                if (fullName.toUpperCase().contains(value)) {
                    matchCustomers.add(customer);
                }
            } else {
                String fullName = customer.getName() + " " + ((LegalPerson) customer).getNickname();
                if (fullName.toUpperCase().contains(value)) {
                    matchCustomers.add(customer);
                }
            }
        }
        return matchCustomers;
    }

    public List<Vehicle> searchVehicles(String value) {
        List<Vehicle> matchVehicles = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            String fullName = vehicle.getVehicleManufacturer() + " " + vehicle.getVehicleModel();
            if (fullName.toUpperCase().contains(value)) {
                matchVehicles.add(vehicle);
            }
        }
        return matchVehicles;
    }

    public List<Agency> searchAgencies(String value) {
        List<Agency> matchAgencies = new ArrayList<>();

        for (Agency agency : agencies) {
            String fullName = agency.getName() + " " + agency.getAddress();
            if (fullName.toUpperCase().contains(value)) {
                matchAgencies.add(agency);
            }
        }
        return matchAgencies;
    }

    public Agency searchByAgencyId(Integer id) {
        return agencies.stream()
                .filter(agency -> agency.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public RentalOperation searchByOperationId(Integer id) {
        return operations.stream()
                .filter(operation -> operation.getRentalID().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Agency> getAgencies() {
        return agencies;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public boolean addCustomer(Customer customer) { //RN6
        if (customers.contains(customer)) return false;
        customers.add(customer);
        return true;
    }

    public List<RentalOperation> getOperations() {
        return operations;
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

    public boolean addOperation(RentalOperation operation) { //RN5
        if (operations.contains(operation)) return false;
        operations.add(operation);
        return true;
    }
    public boolean updateAgency(Agency updatedAgency) {
        boolean agencyExists = false;

        for (int i = 0; i < agencies.size(); i++) {
            if (agencies.get(i).getId().equals(updatedAgency.getId())) {
                agencies.set(i, updatedAgency);
                agencyExists = true;
                break;
            }
        }
        return agencyExists;
    }

    public boolean updateCustomer(Customer updatedCustomer) {
        boolean customerExists = false;

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getDocument().equals(updatedCustomer.getDocument())) {
                customers.set(i, updatedCustomer);
                customerExists = true;
                break;
            }

        }
        return customerExists;
    }




    public void deleteAll() {
        customers.clear();
    }

    public void remove(int index) {
        customers.remove(index);
    }

    public void close() throws IOException {
        Files.newBufferedWriter(dbPath, StandardCharsets.UTF_8);
        JSONArray costumersArray = new JSONArray();
        JSONArray agenciesArray = new JSONArray();

        for (Customer costumer : customers) {
            JSONObject costumerObject = costumer.toJSONObject();
            costumersArray.put(costumerObject);
        }

        for (Agency agency : agencies) {
            JSONObject agencieObject = agency.toJSONObject();
            agenciesArray.put(agencieObject);
        }

        //Preciso tratar a null pointer aqui
        JSONObject object = new JSONObject().put("customers", costumersArray).put("vehicles", new JSONArray()).put("agencies", agenciesArray);
        Files.writeString(dbPath, object.toString(), StandardOpenOption.WRITE);


    }
}


