package part1;
import part1.Emission;
import part1.Country;
import part1.Sector;

import java.util.List;
import java.util.Map;


public class Util {
    public static int getYearWithHighestEmissions(Country c) {
        Map<Integer, Emission> emissions = c.getEmissions();
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

    public static int getYearWithHighestEmissions(Sector s) {
        Map<Integer, Double> emissions = s.getEmissions();
        double max = 0.0;
        int highestYear = 0;

        for (Map.Entry<Integer, Double> current : emissions.entrySet()) {
            Integer year = current.getKey();
            Double currentEmission = current.getValue();
            double emissionMax = currentEmission;
            if (emissionMax > max) {
                max = emissionMax;
                highestYear = year;
            }

        }
        return highestYear;
    }
}


