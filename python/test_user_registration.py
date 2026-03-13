import unittest
from datetime import datetime, timedelta
from user_registration import register_user

class TestUserRegistration(unittest.TestCase):

    def setUp(self):
        # Helper to generate dates based on current time
        self.today = datetime.now()

    def test_successful_normal_registration(self):
        dob = (self.today - timedelta(days=365 * 20)).strftime("%Y-%m-%d")
        raw_data = {
            "firstName": "John",
            "lastName": "Doe",
            "email": "john.doe@example.com",
            "phone": "1234567890",
            "dateOfBirth": dob 
        }
        
        result = register_user(raw_data)
        
        self.assertEqual("SUCCESS", result.get("status"))
        self.assertEqual("NORMAL", result.get("userType"))
        self.assertEqual("john.doe@example.com", result.get("email"))

    def test_successful_dependent_registration_age_15(self):
        dob = (self.today - timedelta(days=365 * 15)).strftime("%Y-%m-%d")
        raw_data = {
            "firstName": "Jane",
            "lastName": "Smith",
            "email": "jane.smith@example.com",
            "phone": "0987654321",
            "dateOfBirth": dob
        }
        
        result = register_user(raw_data)
        
        self.assertEqual("SUCCESS", result.get("status"))
        self.assertEqual("DEPENDENT", result.get("userType"))

    def test_successful_dependent_registration_age_1(self):
        dob = (self.today - timedelta(days=365 * 1)).strftime("%Y-%m-%d")
        raw_data = {
            "firstName": "Baby",
            "lastName": "Doe",
            "email": "baby.doe@example.com",
            "phone": "1112223333",
            "dateOfBirth": dob
        }
        
        result = register_user(raw_data)
        
        self.assertEqual("SUCCESS", result.get("status"))
        self.assertEqual("DEPENDENT", result.get("userType"))

    def test_invalid_age_zero(self):
        dob = self.today.strftime("%Y-%m-%d")
        raw_data = {
            "firstName": "Newborn",
            "lastName": "Doe",
            "email": "new.doe@example.com",
            "phone": "1112223333",
            "dateOfBirth": dob
        }
        
        result = register_user(raw_data)
        
        self.assertEqual("ERROR", result.get("status"))
        self.assertIsNotNone(result.get("errors"))
        self.assertTrue(len(result.get("errors")) > 0)

    def test_invalid_age_negative(self):
        dob = (self.today + timedelta(days=365)).strftime("%Y-%m-%d")
        raw_data = {
            "firstName": "Future",
            "lastName": "Doe",
            "email": "future@example.com",
            "phone": "1112223333",
            "dateOfBirth": dob
        }
        
        result = register_user(raw_data)
        
        self.assertEqual("ERROR", result.get("status"))

    def test_missing_required_fields(self):
        raw_data = {
            "firstName": "John",
            "email": "john.doe@example.com"
        }
        
        result = register_user(raw_data)
        
        self.assertEqual("ERROR", result.get("status"))

    def test_empty_fields(self):
        raw_data = {
            "firstName": "John",
            "lastName": "",
            "email": "john.doe@example.com",
            "phone": "1234567890",
            "dateOfBirth": "1990-01-01" 
        }
        
        result = register_user(raw_data)
        
        self.assertEqual("ERROR", result.get("status"))

    def test_invalid_email_format(self):
        raw_data = {
            "firstName": "John",
            "lastName": "Doe",
            "email": "not-an-email",
            "phone": "1234567890",
            "dateOfBirth": "1990-01-01" 
        }
        
        result = register_user(raw_data)
        
        self.assertEqual("ERROR", result.get("status"))
        
    def test_invalid_phone_format(self):
        raw_data = {
            "firstName": "John",
            "lastName": "Doe",
            "email": "john.doe@example.com",
            "phone": "123abc7890", # letters in phone
            "dateOfBirth": "1990-01-01" 
        }
        
        result = register_user(raw_data)
        
        self.assertEqual("ERROR", result.get("status"))

    def test_invalid_phone_format_too_short(self):
        raw_data = {
            "firstName": "John",
            "lastName": "Doe",
            "email": "john.doe@example.com",
            "phone": "1234567",
            "dateOfBirth": "1990-01-01" 
        }
        
        result = register_user(raw_data)
        
        self.assertEqual("ERROR", result.get("status"))

if __name__ == '__main__':
    unittest.main()
