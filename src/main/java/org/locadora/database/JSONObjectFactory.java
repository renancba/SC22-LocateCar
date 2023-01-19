package org.locadora.database;

import org.json.JSONArray;
import org.json.JSONObject;
import org.locadora.model.Address;
import org.locadora.model.Agency;
import org.locadora.model.RentalOperation;
import org.locadora.model.Telephone;
import org.locadora.model.customer.Customer;
import org.locadora.model.customer.LegalPerson;
import org.locadora.model.customer.NaturalPerson;
import org.locadora.model.vehicle.Motorcycle;
import org.locadora.model.vehicle.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;


class JSONObjectFactory {
    public Customer createCustomer(JSONObject costumer) {

        JSONObject address = (JSONObject) costumer.get("address");
        String cep = (String) address.get("zipcode");
        String street = (String) address.get("street");
        String number = (String) address.get("number");
        String city = (String) address.get("city");
        String state = (String) address.get("state");

        Address newAddress = new Address(cep, street, number, city, state);

        JSONObject telephone = (JSONObject) costumer.get("telephone");
        String ddd = (String) telephone.get("ddd");
        String telNumber = (String) telephone.get("number");

        Telephone newTelePhone = new Telephone(ddd, telNumber);


        if (costumer.has("cpf")) {
            String name = (String) costumer.get("name");
            String surname = (String) costumer.get("surname");
            String cpf = (String) costumer.get("cpf");
            String driverLicense = (String) costumer.get("driverLicense");


            return new NaturalPerson(name, surname, cpf, driverLicense, newTelePhone, newAddress);

        } else {
            String name = (String) costumer.get("name");
            String nickname = (String) costumer.get("nickname");
            String cnpj = (String) costumer.get("cnpj");

            return new LegalPerson(name, nickname, cnpj, newTelePhone, newAddress);
        }
    }

    public Vehicle createVehicle(JSONObject vehicle) {
        String vehicleManufacturer = (String) vehicle.get("manufacturer");
        String vehicleModel = (String) vehicle.get("model");
        String vehicleRegPlate = (String) vehicle.get("registration plate");
        return new Motorcycle(vehicleManufacturer, vehicleModel, vehicleRegPlate);
    }

    public Agency createAgency(JSONObject agency) {

        Integer id = (Integer) agency.get("id");
        String agencyName = (String) agency.get("name");

        JSONObject address = (JSONObject) agency.get("address");
        String cep = (String) address.get("zipcode");
        String street = (String) address.get("street");
        String number = (String) address.get("number");
        String city = (String) address.get("city");
        String state = (String) address.get("state");

        Address newAddress = new Address(cep, street, number, city, state);

        Agency newAgency = new Agency(agencyName, newAddress);
        newAgency.setId(id);


//        JSONArray vehiclesArray = (JSONArray) agency.get("vehicles");

//        for (Object vehicleObject : vehiclesArray) {
//            JSONObject contact = (JSONObject) vehicleObject;
//
//            String name = (String) contact.get("name");
//            String surname = (String) contact.get("surname");
//            Contact newContact = new Contact(name, surname);
//            getAddresses(contact, newContact);
//            getTelephones(contact, newContact);
//            contacts.add(newContact);
//        }

        return newAgency;
    }

    public RentalOperation createOperation(JSONObject operation) {

        //Criar um costumer a partir das informações do costumer
        Customer customer = createCustomer((JSONObject) operation.get("customer"));

        // Criar um carro a partir das informações do vehicle
        Vehicle vehicle = createVehicle((JSONObject) operation.get("vehicle"));

        // Criar uma agencia a partir das informações da Agency
        Agency agency = createAgency((JSONObject) operation.get("agency"));

        Integer contrato = (Integer) operation.get("id");
        LocalDate startDate = (LocalDate) operation.get("startDate");
        LocalDate endDate = (LocalDate) operation.get("endDate");
        BigDecimal cost = new BigDecimal((Double) operation.get("cost"));
        boolean isOver = (boolean) operation.get("isOver");


        return new RentalOperation(contrato, customer, vehicle, startDate, endDate, agency, cost, isOver);
    }


}