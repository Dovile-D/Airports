import DAO.AirportDAO;

import entity.Airport;

public class Main {
    public static void main(String[] args) {
        Airport airport = new Airport("Barcelona entity.Airport", "Av. del Comandante García Morato, s/n, 29004",
                "Barcelona");

//        AirportDAO.create(airport);

        Airport airport1 = new Airport(1, "Warsaw", "Polish pride street 55", "Warsaw");

        AirportDAO.update(airport1);

        AirportDAO.deleteById(3);
    }
}
