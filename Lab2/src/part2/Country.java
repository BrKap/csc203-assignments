package part2;

import java.util.Map;

public class Country {
    private String name;

    private Map<Integer, Emission> emissions;

    public Country(String name, Map<Integer, Emission> emissions) {
        this.name = name;
        this.emissions = emissions;
    };

    public Map<Integer, Emission> getEmissions() {
        return emissions;
    }

    public String getName() {
        return name;
    }

    public int getYearWithHighestEmissions() {
        Map<Integer, Emission> emissions = this.emissions;
        double max = 0.0;
        int highestYear = 0;

        for (Map.Entry<Integer, Emission> current : emissions.entrySet()) {
            Integer year = current.getKey();
            Emission currentEmission = current.getValue();
            double emissionMax = currentEmission.getCH4() + currentEmission.getCO2() + currentEmission.getN2O();
            if (emissionMax > max) {
                max = emissionMax;
                highestYear = year;
            }
        }

        return highestYear;
    }
}
