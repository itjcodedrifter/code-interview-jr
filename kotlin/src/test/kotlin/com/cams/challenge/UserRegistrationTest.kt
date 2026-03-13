package com.cams.challenge

import org.junit.jupiter.api.Test
import java.time.LocalDate
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class UserRegistrationTest {

    private val today = LocalDate.now()

    private fun getPastDate(yearsToSubtract: Long): String {
        return today.minusYears(yearsToSubtract).toString()
    }

    @Test
    fun `Successful NORMAL Registration`() {
        val rawData = mapOf(
            "firstName" to "John",
            "lastName" to "Doe",
            "email" to "john.doe@example.com",
            "phone" to "1234567890",
            "dateOfBirth" to getPastDate(20)
        )
        
        val result = registerUser(rawData)
        
        assertEquals("SUCCESS", result.status)
        assertEquals("NORMAL", result.userType)
        assertEquals("john.doe@example.com", result.email)
    }

    @Test
    fun `Successful DEPENDENT Registration Age 15`() {
        val rawData = mapOf(
            "firstName" to "Jane",
            "lastName" to "Smith",
            "email" to "jane.smith@example.com",
            "phone" to "0987654321",
            "dateOfBirth" to getPastDate(15)
        )
        
        val result = registerUser(rawData)
        
        assertEquals("SUCCESS", result.status)
        assertEquals("DEPENDENT", result.userType)
    }

    @Test
    fun `Successful DEPENDENT Registration Age 1`() {
        val rawData = mapOf(
            "firstName" to "Baby",
            "lastName" to "Doe",
            "email" to "baby@example.com",
            "phone" to "1112223333",
            "dateOfBirth" to getPastDate(1)
        )
        
        val result = registerUser(rawData)
        
        assertEquals("SUCCESS", result.status)
        assertEquals("DEPENDENT", result.userType)
    }

    @Test
    fun `Invalid Age Zero`() {
        val rawData = mapOf(
            "firstName" to "Newborn",
            "lastName" to "Doe",
            "email" to "newborn@example.com",
            "phone" to "1112223333",
            "dateOfBirth" to today.toString()
        )
        
        val result = registerUser(rawData)
        
        assertEquals("ERROR", result.status)
        assertNotNull(result.errors)
        assertTrue(result.errors!!.isNotEmpty())
    }

    @Test
    fun `Invalid Age Negative`() {
        val rawData = mapOf(
            "firstName" to "Future",
            "lastName" to "Doe",
            "email" to "future@example.com",
            "phone" to "1112223333",
            "dateOfBirth" to today.plusYears(1).toString()
        )
        
        val result = registerUser(rawData)
        
        assertEquals("ERROR", result.status)
    }

    @Test
    fun `Missing Required Fields`() {
        val rawData = mapOf(
            "firstName" to "John",
            "email" to "john.doe@example.com"
        )
        
        val result = registerUser(rawData)
        
        assertEquals("ERROR", result.status)
    }
    
    @Test
    fun `Empty String Fields`() {
        val rawData = mapOf(
            "firstName" to "John",
            "lastName" to "",
            "email" to "john.doe@example.com",
            "phone" to "1234567890",
            "dateOfBirth" to "1990-01-01"
        )
        
        val result = registerUser(rawData)
        
        assertEquals("ERROR", result.status)
    }

    @Test
    fun `Invalid Email Format`() {
        val rawData = mapOf(
            "firstName" to "John",
            "lastName" to "Doe",
            "email" to "not-an-email",
            "phone" to "1234567890",
            "dateOfBirth" to "1990-01-01"
        )
        
        val result = registerUser(rawData)
        
        assertEquals("ERROR", result.status)
    }

    @Test
    fun `Invalid Phone Format Contains Letters`() {
        val rawData = mapOf(
            "firstName" to "John",
            "lastName" to "Doe",
            "email" to "john.doe@example.com",
            "phone" to "1A34567890",
            "dateOfBirth" to "1990-01-01"
        )
        
        val result = registerUser(rawData)
        
        assertEquals("ERROR", result.status)
    }
    
    @Test
    fun `Invalid Phone Format Too Short`() {
        val rawData = mapOf(
            "firstName" to "John",
            "lastName" to "Doe",
            "email" to "john.doe@example.com",
            "phone" to "1234567",
            "dateOfBirth" to "1990-01-01"
        )
        
        val result = registerUser(rawData)
        
        assertEquals("ERROR", result.status)
    }
}
