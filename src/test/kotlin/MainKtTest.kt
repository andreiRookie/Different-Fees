import org.junit.Test

import org.junit.Assert.*
import kotlin.math.exp

class MainKtTest {

    /**
     * "Names for test methods
    In tests (and only in tests), you can use method names with spaces enclosed in backticks.
    Note that such method names are currently not supported by the Android runtime.
    Underscores in method names are also allowed in test code."
     */

    @Test
    fun calculateTransferFee_failCheck() {
        //arrange
        val amount = 10000U
        //act
        val actualTransferFee = calculateTransferFee(amount)
        //assert
        assertEquals("\"${object {}.javaClass.enclosingMethod.name}\"", 100, actualTransferFee)
    }
    @Test
    fun calculateTransferFee_visaCardTest() {

        //  ARRANGE
        val amount: UInt = 15000U
        val paymentSystem: PaymentSystems = PaymentSystems.VISA

        val expectedResult = 112_50U

        //  ACT
        val actualResult = calculateTransferFee(amount = amount, paymentSystem = paymentSystem)

        //  ASSERT
        assertEquals("\"${object{}.javaClass.enclosingMethod.name}\"", expectedResult, actualResult)
    }
    @Test
    fun calculateTransferFee_mirCardTest() {

        //  ARRANGE
        val amount: UInt = 33300U
        val paymentSystem: PaymentSystems = PaymentSystems.MIR

        val expectedResult = 249_75U

        //  ACT
        val actualResult = calculateTransferFee(amount = amount, paymentSystem = paymentSystem)

        //  ASSERT
        assertEquals("\"${object{}.javaClass.enclosingMethod.name}\"", expectedResult, actualResult)
    }


    @Test
    fun calculateTransferFee_visaCardMinimalSumTest() {
        val amount: UInt = 500U
        val paymentSystem: PaymentSystems = PaymentSystems.VISA

        val expectedResult = 35_00U

        val actualResult = calculateTransferFee(amount = amount, paymentSystem = paymentSystem)

        assertEquals("\"${object{}.javaClass.enclosingMethod.name}\"", expectedResult, actualResult)
    }

    @Test
    fun calculateTransferFee_mirCardMinimalSumTest() {
        val amount: UInt = 1000U
        val paymentSystem: PaymentSystems = PaymentSystems.MIR

        val expectedResult = 35_00U

        val actualResult = calculateTransferFee(amount = amount, paymentSystem = paymentSystem)

        assertEquals("\"${object{}.javaClass.enclosingMethod.name}\"", expectedResult, actualResult)
    }

    @Test
    fun calculateTransferFee_mastercardOverLimitTest() {
        //  ARRANGE
        val amount: UInt = 10000U
        val sumOfTransfersPerMonth: UInt = 75001U
        val paymentSystem: PaymentSystems = PaymentSystems.MASTERCARD

        val expectedResult = 80_00U

        //  ACT
        val actualResult = calculateTransferFee(amount = amount,
            sumOfTransfersPerMonth = sumOfTransfersPerMonth,
            paymentSystem = paymentSystem)

        //  ASSERT
        assertEquals(expectedResult,actualResult)

    }

    @Test
    fun calculateTransferFee_maestroLessThanLimitTest() {
        //  ARRANGE
        val amount: UInt = 10000U
        val paymentSystem: PaymentSystems = PaymentSystems.MAESTRO

        val expectedResult = 0U

        //  ACT
        val actualResult = calculateTransferFee(amount = amount,
            paymentSystem = paymentSystem)

        //  ASSERT
        assertEquals(expectedResult,actualResult)
    }
    @Test
    fun calculateTransferFee_byDefaultTest() {
        //  ARRANGE
        val amount: UInt = 333000U

        val expectedResult = 0U

        //  ACT
        val actualResult = calculateTransferFee(amount = amount)

        //  ASSERT
        assertEquals(expectedResult,actualResult)
    }

    @Test
    fun feeAsStringTest() {
        val fee = 555112U

        val expectedResult = "5551руб. 12коп."

        val actualResult = feeAsString(fee)

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun getPaymentSystemTest_Visa(){

        val number = 3

        val expectedResult = PaymentSystems.VISA

        val actualResult = getPaymentSystem(number)

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun getPaymentSystemTest_Mir(){

        val number = 4

        val expectedResult = PaymentSystems.MIR

        val actualResult = getPaymentSystem(number)

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun getPaymentSystemTest_Mastercard(){

        val number = 1

        val expectedResult = PaymentSystems.MASTERCARD

        val actualResult = getPaymentSystem(number)

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun getPaymentSystemTest_Maestro(){

        val number = 2

        val expectedResult = PaymentSystems.MAESTRO

        val actualResult = getPaymentSystem(number)

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun getPaymentSystemTest_byDefault(){

        val number = 0

        val expectedResult = PaymentSystems.VK_PAY

        val actualResult = getPaymentSystem(number)

        assertEquals(expectedResult, actualResult)
    }


}