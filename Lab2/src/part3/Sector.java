package part3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sector {

    private String name;
    private Map<Integer, Double> emissions;

    public Sector(String name, Map<Integer, Double> emissions) {
        this.name = name;
        this.emissions = emissions;
    }

    public String getName() {
        return name;
    }

    public Map<Integer, Double> getEmissions() {
        return emissions;
    }

    public int getYearWithHighestEmissions() {
        Map<Integer, Double> emissions = this.emissions;
        double max = 0.0;
        int highestYear = 0;

        for (Map.Entry<Integer, Double> current : emissions.entrySet()) {
            Integer year = current.getKey();
            double emissionMax = current.getValue();
            if (emissionMax > max) {
                max = emissionMax;
                highestYear = year;
            }
        }

        return highestYear;
    }

    public static Sector sectorWithBiggestChangeInEmissions(List<Sector> sectors, int startYear, int endYear) {
        Sector sectorWithHighestAverageEmissions = null;
        double highestAverageEmissions = 0.0;

        for (Sector sector : sectors) {
            double totalEmissions = 0.0;
            int yearsCounted = 0;

            for (int year = startYear; year <= endYear; year++) {
                if (sector.getEmissions().containsKey(year)) {
                    totalEmissions += sector.getEmissions().get(year);
                    yearsCounted++;
                }

            }

            if (yearsCounted > 0) {
                double averageEmissions = totalEmissions / yearsCounted;

                if (averageEmissions > highestAverageEmissions) {
                    highestAverageEmissions = averageEmissions;
                    sectorWithHighestAverageEmissions = sector;
                }
            }
        }
        System.out.println("The sector with the biggest average emissions is " + sectorWithHighestAverageEmissions.name + " at " + highestAverageEmissions);
        return sectorWithHighestAverageEmissions;
    }
}
