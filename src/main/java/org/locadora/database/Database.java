package org.locadora.database;



import org.json.JSONArray;
import org.json.JSONObject;
import org.locadora.model.Agency;
import org.locadora.model.customer.Customer;
import org.locadora.model.customer.LegalPerson;
import org.locadora.model.customer.NaturalPerson;
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
    private List<Customer> customers;
    private List<Vehicle> vehicles;
    private List<Agency> agencies;
    private static Database instance;

    private Database() {
        dbPath = Paths.get("src/database/database.json");
        customers = new ArrayList<>();
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
        JSONObject customersObject = new JSONObject(String.join(" ", Files.readAllLines(dbPath, StandardCharsets.UTF_8)));
        JSONArray customersArray = (JSONArray) customersObject.get("customers");
        for (Object customerObject : customersArray) {

            JSONObject customer = (JSONObject) customerObject;

            //CONDIÇÃO SE PESSOA FÍSICA OU PESSOA JURÍDICA
            //Pessoa Física
            String name = (String) customer.get("name");
            String surname = (String) customer.get("surname");
            String cpf = (String) customer.get("cpf");
            String driverLicense = (String) customer.get("driverLicense");
            Customer natualPerson = new NaturalPerson(name, surname, cpf, driverLicense);
            customers.add(natualPerson);

            //TODO: USEI LEGALPERSON SÓ PARA INSTANCIAR E NÃO DAR ERRO DE EXECUÇÃO

            Customer legalPerson = new LegalPerson();
            customers.add(legalPerson);
        }

        JSONObject agenciesObject = new JSONObject(String.join(" ", Files.readAllLines(dbPath, StandardCharsets.UTF_8)));
        JSONArray agenciesArray = (JSONArray) customersObject.get("agencies");
        for (Object agencyObject : agenciesArray) {

            JSONObject agency = (JSONObject) agencyObject;

            String agencyName = (String) agency.get("name");
            Agency newAgency = new Agency(agencyName);
            agencies.add(newAgency);
        }
        JSONObject vehiclesObject = new JSONObject(String.join(" ", Files.readAllLines(dbPath, StandardCharsets.UTF_8)));
        JSONArray vehiclesArray = (JSONArray) customersObject.get("vehicles");
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

    public Customer getCustomer(int index) {
        return customers.get(index);
    }

    public Agency getAgency(int index) {
        return agencies.get(index);
    }

    // TODO: VALIDAR SE O CASTING ESTÁ FUNCIONANDO

    public List<Customer> searchCustomer(String value) {
        List<Customer> matchCustomers = new ArrayList<>();

        for (Customer customer : customers) {
            if (customer instanceof NaturalPerson) {
                String fullName = customer.getName() + " " + ((NaturalPerson)customer).getSurname();
                if (fullName.contains(value)) {
                    matchCustomers.add(customer);
                }
            } else {
                String fullName = customer.getName() + " " + ((LegalPerson)customer).getNickname();
                if (fullName.contains(value)) {
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

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Agency> getAgencies(){
        return agencies;
    }

    public List<Vehicle> getVehicles(){
        return vehicles;
    }

    public boolean addCustomer(Customer customer) { //RN6
        if (customers.contains(customer)) return false;

        customers.add(customer);
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
        customers.clear();
    }

    public void remove(int index) {
        customers.remove(index);
    }

    public void close() throws IOException {
        Files.newBufferedWriter(dbPath);
        JSONArray customersArray = new JSONArray();

        for (Customer customer : customers) {
            JSONObject customerObject = new JSONObject();
            customerObject.put("name", customer.getName());
            if (customer instanceof NaturalPerson) {
                customerObject.put("surname", ((NaturalPerson) customer).getSurname());
            } else {
                customerObject.put("surname", ((LegalPerson) customer).getNickname());
            }
            customerObject.put("address", customer.getAddress());
            customerObject.put("telephone", customer.getTelephone());
            customersArray.put(customerObject);
        }

        JSONObject object = new JSONObject().put("customers", customersArray);

        Files.writeString(dbPath, object.toString(), StandardOpenOption.WRITE);
    }
}