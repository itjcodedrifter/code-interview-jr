package com.cams.challenge

data class RegistrationResponse(
    val status: String,
    val userType: String? = null,
    val email: String? = null,
    val errors: List<String>? = null
) {
    companion object {
        fun success(type: String, email: String) = RegistrationResponse("SUCCESS", type, email, null)
        fun error(errors: List<String>) = RegistrationResponse("ERROR", null, null, errors)
    }
}

/**
 * Validates user data, classifies user based on age, and returns response.
 *
 * @param rawData A map representing the raw user input data.
 *                Expected keys: 'firstName', 'lastName', 'email', 'phone', 'dateOfBirth'.
 * @return RegistrationResponse indicating SUCCESS or ERROR.
 */
fun registerUser(rawData: Map<String, String>): RegistrationResponse {
    // TODO: Implement validation and classification logic here
    throw NotImplementedError("Not implemented yet")
}
