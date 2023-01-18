package org.locadora.database;

import org.json.JSONObject;
import org.locadora.model.Agency;
import org.locadora.model.RentalOperation;
import org.locadora.model.costumer.Costumer;
import org.locadora.model.costumer.LegalPerson;
import org.locadora.model.costumer.NaturalPerson;
import org.locadora.model.vehicle.Motorcycle;
import org.locadora.model.vehicle.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;


class JSONObjectFactory {
    public Costumer createCostumer(JSONObject costumer) {
        if (costumer.has("cpf")) {
            String name = (String) costumer.get("name");
            String surname = (String) costumer.get("surname");
            String cpf = (String) costumer.get("cpf");
            String driverLicense = (String) costumer.get("driverLicense");
            return new NaturalPerson(name, surname, cpf, driverLicense);
        } else {
            String name = (String) costumer.get("name");
            String nickname = (String) costumer.get("nickname");
            String cnpj = (String) costumer.get("cnpj");
            String companyDriver = (String) costumer.get("companyDriver");
            String driverLicense = (String) costumer.get("driverLicense");
            return new LegalPerson(name, nickname, cnpj, companyDriver, driverLicense);
        }
    }

    public Vehicle createVehicle(JSONObject vehicle) {
        String vehicleManufacturer = (String) vehicle.get("manufacturer");
        String vehicleModel = (String) vehicle.get("model");
        String vehicleRegPlate = (String) vehicle.get("registration plate");
        return new Motorcycle(vehicleManufacturer, vehicleModel, vehicleRegPlate);
    }

    public Agency createAgency(JSONObject agency) {
        String agencyName = (String) agency.get("name");
        return new Agency(agencyName);
    }


    public RentalOperation createOperation(JSONObject operation) {

        //Criar um costumer a partir das informações do costumer
        Costumer costumer = createCostumer((JSONObject) operation.get("costumer"));

        // Criar um carro a partir das informações do vehicle
        Vehicle vehicle = createVehicle((JSONObject) operation.get("vehicle"));

        // Criar uma agencia a partir das informações da Agency
        Agency agency = createAgency((JSONObject) operation.get("agency"));

        Integer contrato = (Integer) operation.get("id");
        LocalDate startDate = (LocalDate) operation.get("startDate");
        LocalDate endDate = (LocalDate) operation.get("endDate");
        BigDecimal cost = new BigDecimal((Double) operation.get("cost"));


        return new RentalOperation(contrato, costumer, vehicle, startDate, endDate, agency, cost);
    }


}