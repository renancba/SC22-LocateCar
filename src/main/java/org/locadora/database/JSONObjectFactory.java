package org.locadora.database;

import org.json.JSONObject;
import org.locadora.model.Agency;
import org.locadora.model.costumer.Costumer;
import org.locadora.model.costumer.LegalPerson;
import org.locadora.model.costumer.NaturalPerson;
import org.locadora.model.vehicle.Motorcycle;
import org.locadora.model.vehicle.Vehicle;


interface ObjectFactory {
    static Costumer createCostumer(JSONObject costumer) {
        return null;
    }

    static Vehicle createVehicle(JSONObject vehicle) {
        return null;
    }

    static Agency createAgency(JSONObject agency) {
        return null;
    }
}

class JSONObjectFactory implements ObjectFactory {
    public Costumer createCostumer(JSONObject costumer) {
        if (costumer.get("type").equals("natural")) {
            String name = (String) costumer.get("name");
            String surname = (String) costumer.get("surname");
            String cpf = (String) costumer.get("cpf");
            String driverLicense = (String) costumer.get("driverLicense");
            return new NaturalPerson(name, surname, cpf, driverLicense);
        } else {
            String name = (String) costumer.get("name");
            String nickname = (String) costumer.get("nickname");
            String cnpj = (String) costumer.get("cnpj");
            return new LegalPerson(name,nickname, cnpj);
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
}