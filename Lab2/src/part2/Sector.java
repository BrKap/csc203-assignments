package part2;

import java.util.Map;

public class Sector {

    private String name;
    private Map<Integer, Double> emissions;

    public Sector(String name, Map<Integer, Double> emissions) {
        this.name = name;
        this.emissions = emissions;
    };

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
}
