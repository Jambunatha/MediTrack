package com.airtribe.meditrack.util;

import java.util.Locale;

public final class AIHelper {
    private AIHelper() {
        // Private constructor to prevent instantiation
    }

    public static String suggestSpecialization(String symptom) {
        // In a real implementation, this would call an AI service like OpenAI's API
        if (symptom == null) {
            return "General Physician";
        }
        symptom = symptom.toLowerCase(Locale.ROOT);
        if (symptom.contains("skin") || symptom.contains("rash")) {
            return "Dermatology";
        }
        if (symptom.contains("heart") || symptom.contains("chest")) {
            return "Cardiology";
        }
        if (symptom.contains("bone") || symptom.contains("joint")) {
            return "Orthopedics";
        }
        if (symptom.contains("child") || symptom.contains("pediatric")) {
            return "Pediatrics";
        }
        return "General Physician";
    }
}
