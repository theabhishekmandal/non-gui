package lowleveldesign.parkinglot.design.multiThreaded;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SearchManager {
    private final Map<String, String> ticketAndVehicleIdToSpotNumber = new ConcurrentHashMap<>();
    private final Map<String, List<String>> spotIdToTicketAndVehicleNumber = new ConcurrentHashMap<>();

    public boolean save(String vehicleId, String ticketId, String spotId) {
        ticketAndVehicleIdToSpotNumber.put(vehicleId, spotId);
        ticketAndVehicleIdToSpotNumber.put(ticketId, spotId);

        spotIdToTicketAndVehicleNumber.put(spotId, List.of(vehicleId, ticketId));
        print();
        return true;
    }

    public String search(String query) {
        String s = ticketAndVehicleIdToSpotNumber.get(query);
        return s == null? "" : s;
    }

    public boolean remove(String spotId) {
        List<String> remove = spotIdToTicketAndVehicleNumber.remove(spotId);
        if (remove != null && !remove.isEmpty()) {
            ticketAndVehicleIdToSpotNumber.remove(remove.get(0));
            ticketAndVehicleIdToSpotNumber.remove(remove.get(1));
            print();
            return true;
        }
        return false;
    }

    private void print() {
        String printString = """
                    ThreadName = %s
                    vehicleAndTicketToSpotId = %s,
                    spotIdToTicketAndVehicle = %s,
                """.formatted(Thread.currentThread().getName(),
                ticketAndVehicleIdToSpotNumber,
                spotIdToTicketAndVehicleNumber);

        System.out.println(printString);
    }
}
