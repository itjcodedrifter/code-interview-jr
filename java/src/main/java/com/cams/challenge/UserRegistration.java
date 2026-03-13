package com.cams.challenge;

import java.util.Map;
import java.util.List;

public class UserRegistration {

    public static class RegistrationResponse {
        public String status;
        public String userType;
        public String email;
        public List<String> errors;
        
        // Helper constructors you can use
        public static RegistrationResponse success(String type, String email) {
            RegistrationResponse res = new RegistrationResponse();
            res.status = "SUCCESS";
            res.userType = type;
            res.email = email;
            return res;
        }

        public static RegistrationResponse error(List<String> errors) {
            RegistrationResponse res = new RegistrationResponse();
            res.status = "ERROR";
            res.errors = errors;
            return res;
        }
    }

    /**
     * Validates user data, classifies user based on age, and returns response.
     *
     * @param rawData A map representing the raw user input data.
     *                Expected keys: 'firstName', 'lastName', 'email', 'phone', 'dateOfBirth'.
     * @return RegistrationResponse indicating SUCCESS or ERROR.
     */
    public RegistrationResponse registerUser(Map<String, String> rawData) {
        // TODO: Implement validation and classification logic here
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
