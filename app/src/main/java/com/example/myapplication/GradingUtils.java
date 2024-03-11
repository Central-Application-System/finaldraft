package com.example.myapplication;

import java.util.HashMap;
import java.util.Map;

public class GradingUtils {
    private static Map<String, Integer> gradePointMap;

    static {
        gradePointMap = new HashMap<>();
        gradePointMap.put("A", 8);
        gradePointMap.put("B", 7);
        gradePointMap.put("C", 6);
        gradePointMap.put("D", 5);
        gradePointMap.put("E", 4);
    }

    public static int getPointForGrade(String grade) {
        return gradePointMap.getOrDefault(grade, 0);
    }
}

