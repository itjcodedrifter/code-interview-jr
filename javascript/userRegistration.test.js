const registerUser = require('./userRegistration');

describe('User Registration Challenge', () => {

    const today = new Date();

    const getPastDate = (years) => {
        const d = new Date(today);
        d.setFullYear(d.getFullYear() - years);
        return d.toISOString().split('T')[0];
    };
    
    test('Successful NORMAL Registration', () => {
        const rawData = {
            firstName: "John",
            lastName: "Doe",
            email: "john.doe@example.com",
            phone: "1234567890",
            dateOfBirth: getPastDate(20) 
        };
        
        const result = registerUser(rawData);
        
        expect(result.status).toBe('SUCCESS');
        expect(result.userType).toBe('NORMAL');
        expect(result.email).toBe('john.doe@example.com');
    });

    test('Successful DEPENDENT Registration (Age 15)', () => {
        const rawData = {
            firstName: "Jane",
            lastName: "Smith",
            email: "jane.smith@example.com",
            phone: "0987654321",
            dateOfBirth: getPastDate(15) 
        };
        
        const result = registerUser(rawData);
        
        expect(result.status).toBe('SUCCESS');
        expect(result.userType).toBe('DEPENDENT');
    });

    test('Successful DEPENDENT Registration (Age 1)', () => {
        const rawData = {
            firstName: "Baby",
            lastName: "Doe",
            email: "baby@example.com",
            phone: "1112223333",
            dateOfBirth: getPastDate(1) 
        };
        
        const result = registerUser(rawData);
        
        expect(result.status).toBe('SUCCESS');
        expect(result.userType).toBe('DEPENDENT');
    });

    test('Invalid validation for age 0', () => {
        const rawData = {
            firstName: "Newborn",
            lastName: "Doe",
            email: "newborn@example.com",
            phone: "1112223333",
            dateOfBirth: today.toISOString().split('T')[0]
        };
        
        const result = registerUser(rawData);
        
        expect(result.status).toBe('ERROR');
        expect(result.errors).toBeDefined();
        expect(result.errors.length).toBeGreaterThan(0);
    });

    test('Invalid validation for negative age (future date)', () => {
        const rawData = {
            firstName: "Future",
            lastName: "Doe",
            email: "future@example.com",
            phone: "1112223333",
            dateOfBirth: getPastDate(-1) 
        };
        
        const result = registerUser(rawData);
        
        expect(result.status).toBe('ERROR');
    });

    test('Missing Required Fields', () => {
        const rawData = {
            firstName: "John",
            email: "john.doe@example.com"
            // Missing last name, phone, dob
        };
        
        const result = registerUser(rawData);
        
        expect(result.status).toBe('ERROR');
    });

    test('Empty String Fields', () => {
        const rawData = {
            firstName: "John",
            lastName: "",
            email: "john.doe@example.com",
            phone: "1234567890",
            dateOfBirth: "1990-01-01" 
        };
        
        const result = registerUser(rawData);
        
        expect(result.status).toBe('ERROR');
    });

    test('Invalid Email Format', () => {
         const rawData = {
            firstName: "John",
            lastName: "Doe",
            email: "not-an-email",
            phone: "1234567890",
            dateOfBirth: "1990-01-01" 
        };
        
        const result = registerUser(rawData);
        
        expect(result.status).toBe('ERROR');
    });
    
    test('Invalid Phone Format (Has Letters)', () => {
         const rawData = {
            firstName: "John",
            lastName: "Doe",
            email: "john.doe@example.com",
            phone: "1A34567890",
            dateOfBirth: "1990-01-01" 
        };
        
        const result = registerUser(rawData);
        
        expect(result.status).toBe('ERROR');
    });

    test('Invalid Phone Format (Too Short)', () => {
         const rawData = {
            firstName: "John",
            lastName: "Doe",
            email: "john.doe@example.com",
            phone: "1234567",
            dateOfBirth: "1990-01-01" 
        };
        
        const result = registerUser(rawData);
        
        expect(result.status).toBe('ERROR');
    });
});
