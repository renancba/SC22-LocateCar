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
import org.locadora.model.vehicle.Car;
import org.locadora.model.vehicle.Motorcycle;
import org.locadora.model.vehicle.Truck;
import org.locadora.model.vehicle.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


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

        String vehicleManufacturer = (String) vehicle.get("vehicleManufacturer");
        String vehicleModel = (String) vehicle.get("vehicleModel");
        String vehicleRegPlate = (String) vehicle.get("registrationPlate");
        boolean isAvaible = vehicle.optBoolean("isAvaible", true);

        Object rentalFeeObject = vehicle.get("rentalFee");
        BigDecimal rentalFee;

        if (rentalFeeObject instanceof BigDecimal) {
            rentalFee = (BigDecimal) rentalFeeObject;
        } else if (rentalFeeObject instanceof Double) {
            rentalFee = new BigDecimal(((Double) rentalFeeObject).toString());
        } else {
            rentalFee = new BigDecimal(((Integer) rentalFeeObject).toString());
        }

        if (vehicle.has("cylinderCapacity")) {

            String cylinderCapacity = (String) vehicle.get("cylinderCapacity");
            return new Motorcycle(vehicleManufacturer, vehicleModel, vehicleRegPlate, rentalFee, cylinderCapacity, isAvaible);

        } else if (vehicle.has("transmission")) {

            String transmission = (String) vehicle.get("transmission");
            return new Car(vehicleManufacturer, vehicleModel, vehicleRegPlate, rentalFee, transmission, isAvaible);

        } else {

            String numberOfAxles = (String) vehicle.get("number of axles");
            return new Truck(vehicleManufacturer, vehicleModel, vehicleRegPlate, rentalFee, numberOfAxles, isAvaible);
        }
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

        JSONArray vehiclesArray = (JSONArray) agency.get("vehicles");

        for (Object vehicleObject : vehiclesArray) {
            JSONObject vehicle = (JSONObject) vehicleObject;

            Vehicle responseVehicle = createVehicle(vehicle);

            if (vehicle != null) {
                newAgency.addVehicle(responseVehicle);
            }
        }


        return newAgency;
    }

    public RentalOperation createOperation(JSONObject operation) {
        Customer customer = createCustomer((JSONObject) operation.get("customer"));
        Vehicle vehicle = createVehicle((JSONObject) operation.get("vehicle"));
        Agency agency = createAgency((JSONObject) operation.get("agency"));

        Integer contrato = (Integer) operation.get("id");
        LocalDate startDate = (LocalDate) operation.get("startDate");
        LocalDate endDate = (LocalDate) operation.get("endDate");
        boolean isOver = (boolean) operation.get("isOver");

        Object costObject = operation.get("cost");
        BigDecimal cost;

        if (costObject instanceof BigDecimal) {
            cost = (BigDecimal) costObject;
        } else if (costObject instanceof Double) {
            cost = new BigDecimal(((Double) costObject).toString());
        } else {
            cost = new BigDecimal(((Integer) costObject).toString());
        }


        return new RentalOperation(contrato, customer, vehicle, startDate, endDate, agency, cost, isOver);
    }


}