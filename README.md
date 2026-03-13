# CAMS User Registration - Code Challenge

Welcome to the CAMS code challenge! 

In this challenge, you will implement a user registration validation and classification function. We have provided you with a skeleton project in 4 different languages: Java, Kotlin, Python, and Javascript. Please choose the language you are most comfortable with.

## Objective

Your task is to implement the `register` function (or equivalent in your chosen language) which takes a raw user data input (e.g., dictionary, map, or JSON object) and returns a structured response indicating if the registration was successful, and if so, what type of user was created.

### Input Data Format
The input will be a map/dictionary containing the following fields as strings:
- `firstName`: User's first name
- `lastName`: User's last name
- `email`: User's email address
- `phone`: User's phone number
- `dateOfBirth`: User's date of birth in `YYYY-MM-DD` format

### Validation Rules
You must apply the following validations:
1. **Required Fields**: All fields (`firstName`, `lastName`, `email`, `phone`, `dateOfBirth`) are required and cannot be empty or null.
2. **Email Format**: Must be a valid email format.
3. **Phone Format**: Must be a valid 10-digit phone number (digits only, e.g. `1234567890`).
4. **Age Restriction**: A user must have a valid age greater than 0. If the calculated age is 0 or negative, registration must fail.

### User Classification
If the data is valid, you must classify the user based on their age:
- **`NORMAL`**: Age 18 or older.
- **`DEPENDENT`**: Age under 18.

### Output format
If the registration is successful, return a success response containing:
- `status`: "SUCCESS"
- `userType`: "NORMAL" or "DEPENDENT"
- `email`: the validated email

If the registration fails due to validation errors (e.g., missing fields, invalid format, age <= 0), return an error response containing:
- `status`: "ERROR"
- `errors`: A list or map of error messages describing what went wrong.

## Getting Started

1. Navigate to the directory of your chosen language (e.g. `cd java`).
2. Follow the setup instructions specific to that language (usually running tests via Maven, Gradle, NPM, or Pytest).
3. Find the `UserRegistration` implementation file and the associated test file.
4. Implement the logic to make the failing tests pass.
5. Feel free to add more tests to cover edge cases you think are important.

**Good luck!**
