package br.edu.infnet.lessonservice.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Classification {
    EXCELLENT,
    GOOD,
    AVERAGE,
    POOR,
    VERY_POOR;

    private static final Map<Classification, Integer> classificationValues = new HashMap<>();

    static {
        classificationValues.put(EXCELLENT, 5);
        classificationValues.put(GOOD, 4);
        classificationValues.put(AVERAGE, 3);
        classificationValues.put(POOR, 2);
        classificationValues.put(VERY_POOR, 1);
    }

    public static int getValue(Classification classification) {
        return classificationValues.get(classification);
    }

    public static Classification calculateAverageClassification(int totalSumRating, int totalRating, Classification classification) {

        double average = (double) (totalSumRating + classificationValues.get(classification)) / totalRating;

        if (average >= 4.5) {
            return EXCELLENT;
        } else if (average >= 3.5) {
            return GOOD;
        } else if (average >= 2.5) {
            return AVERAGE;
        } else if (average >= 1.5) {
            return POOR;
        } else {
            return VERY_POOR;
        }
    }
}

