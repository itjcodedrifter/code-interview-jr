package com.cams.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class UserRegistrationTest {

    private final UserRegistration registration = new UserRegistration();
    private LocalDate today;

    @BeforeEach
    void setUp() {
        today = LocalDate.now();
    }

    private String getPastDate(int yearsToSubtract) {
        return today.minusYears(yearsToSubtract).toString();
    }

    @Test
    void testSuccessfulNormalRegistration() {
        Map<String, String> rawData = new HashMap<>();
        rawData.put("firstName", "John");
        rawData.put("lastName", "Doe");
        rawData.put("email", "john.doe@example.com");
        rawData.put("phone", "1234567890");
        rawData.put("dateOfBirth", getPastDate(20));

        UserRegistration.RegistrationResponse result = registration.registerUser(rawData);
        
        assertEquals("SUCCESS", result.status);
        assertEquals("NORMAL", result.userType);
        assertEquals("john.doe@example.com", result.email);
    }

    @Test
    void testSuccessfulDependentRegistrationAge15() {
        Map<String, String> rawData = new HashMap<>();
        rawData.put("firstName", "Jane");
        rawData.put("lastName", "Smith");
        rawData.put("email", "jane.smith@example.com");
        rawData.put("phone", "0987654321");
        rawData.put("dateOfBirth", getPastDate(15));

        UserRegistration.RegistrationResponse result = registration.registerUser(rawData);
        
        assertEquals("SUCCESS", result.status);
        assertEquals("DEPENDENT", result.userType);
    }

    @Test
    void testSuccessfulDependentRegistrationAge1() {
        Map<String, String> rawData = new HashMap<>();
        rawData.put("firstName", "Baby");
        rawData.put("lastName", "Doe");
        rawData.put("email", "baby@example.com");
        rawData.put("phone", "1112223333");
        rawData.put("dateOfBirth", getPastDate(1));

        UserRegistration.RegistrationResponse result = registration.registerUser(rawData);
        
        assertEquals("SUCCESS", result.status);
        assertEquals("DEPENDENT", result.userType);
    }

    @Test
    void testInvalidAgeZero() {
        Map<String, String> rawData = new HashMap<>();
        rawData.put("firstName", "Newborn");
        rawData.put("lastName", "Doe");
        rawData.put("email", "newborn@example.com");
        rawData.put("phone", "1112223333");
        rawData.put("dateOfBirth", today.toString());

        UserRegistration.RegistrationResponse result = registration.registerUser(rawData);
        
        assertEquals("ERROR", result.status);
        assertNotNull(result.errors);
        assertFalse(result.errors.isEmpty());
    }

    @Test
    void testInvalidAgeNegative() {
        Map<String, String> rawData = new HashMap<>();
        rawData.put("firstName", "Future");
        rawData.put("lastName", "Doe");
        rawData.put("email", "future@example.com");
        rawData.put("phone", "1112223333");
        rawData.put("dateOfBirth", today.plusYears(1).toString());

        UserRegistration.RegistrationResponse result = registration.registerUser(rawData);
        
        assertEquals("ERROR", result.status);
    }

    @Test
    void testMissingRequiredFields() {
        Map<String, String> rawData = new HashMap<>();
        rawData.put("firstName", "John");
        rawData.put("email", "john.doe@example.com");

        UserRegistration.RegistrationResponse result = registration.registerUser(rawData);
        
        assertEquals("ERROR", result.status);
    }

    @Test
    void testEmptyStringFields() {
        Map<String, String> rawData = new HashMap<>();
        rawData.put("firstName", "John");
        rawData.put("lastName", "");
        rawData.put("email", "john.doe@example.com");
        rawData.put("phone", "1234567890");
        rawData.put("dateOfBirth", "1990-01-01");

        UserRegistration.RegistrationResponse result = registration.registerUser(rawData);
        
        assertEquals("ERROR", result.status);
    }

    @Test
    void testInvalidEmailFormat() {
        Map<String, String> rawData = new HashMap<>();
        rawData.put("firstName", "John");
        rawData.put("lastName", "Doe");
        rawData.put("email", "not-an-email");
        rawData.put("phone", "1234567890");
        rawData.put("dateOfBirth", "1990-01-01");

        UserRegistration.RegistrationResponse result = registration.registerUser(rawData);
        
        assertEquals("ERROR", result.status);
    }

    @Test
    void testInvalidPhoneFormatContainsLetters() {
        Map<String, String> rawData = new HashMap<>();
        rawData.put("firstName", "John");
        rawData.put("lastName", "Doe");
        rawData.put("email", "john.doe@example.com");
        rawData.put("phone", "1A34567890");
        rawData.put("dateOfBirth", "1990-01-01");

        UserRegistration.RegistrationResponse result = registration.registerUser(rawData);
        
        assertEquals("ERROR", result.status);
    }

    @Test
    void testInvalidPhoneFormatTooShort() {
        Map<String, String> rawData = new HashMap<>();
        rawData.put("firstName", "John");
        rawData.put("lastName", "Doe");
        rawData.put("email", "john.doe@example.com");
        rawData.put("phone", "1234567");
        rawData.put("dateOfBirth", "1990-01-01");

        UserRegistration.RegistrationResponse result = registration.registerUser(rawData);
        
        assertEquals("ERROR", result.status);
    }
}
