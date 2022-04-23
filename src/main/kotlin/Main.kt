
fun main() {

    var sumOfTransfersPerMonth = 0U

    while (true) {
        print("Введите сумму перевода в рублях(для выхода введите 'end'): ")

        try {
            val inputAmount = readLine()
            if (inputAmount.equals("end", ignoreCase = true)) {
                println("До свидания!")
                break
            }

            val amountInRubles = inputAmount?.toUInt() ?: return

            while (true) {
                println("1.MASTERCARD\n2.MAESTRO\n3.VISA\n4.MIR")
                print("Введите номер системы платежей Вашей карты (VK_PAY по умолчанию - 0): ")
                val inputPaymentSystem = readLine()?.toInt() ?: return

                val paySystem = getPaymentSystem(inputPaymentSystem)

                sumOfTransfersPerMonth += amountInRubles

                val fee = calculateTransferFee(
                    amountInRubles,
                    sumOfTransfersPerMonth,
                    paySystem
                )

                println("Комиссия составит: ${feeAsString(fee)}")
                println("Сумма переводов за месяц: ${sumOfTransfersPerMonth}руб.")
                break
            }

        } catch (e: Exception) {
            println("Неверный ввод")
        }
    }
}

fun calculateTransferFee(
    amount: UInt,
    sumOfTransfersPerMonth: UInt = 0U,
    paymentSystem: PaymentSystems = PaymentSystems.VK_PAY
): UInt {

    val amountInKopecks = amount * 100U

    var transferFeeInKopecks = 0U

    when (paymentSystem) {

        PaymentSystems.MASTERCARD, PaymentSystems.MAESTRO -> {
            val feeRate = 0.006
            val maxSumOfTransfersPerMonth = 75000U

            transferFeeInKopecks = if (sumOfTransfersPerMonth <= maxSumOfTransfersPerMonth) {
                0U
            } else {
                (amountInKopecks.toDouble() * feeRate).toUInt() + 20_00U
            }
        }

        PaymentSystems.VISA, PaymentSystems.MIR -> {
            val feeRate = 0.0075
            val minimalFeeInKopecks = 35_00U

            val fee = (amountInKopecks.toDouble() * feeRate).toUInt()

            transferFeeInKopecks = if (fee < minimalFeeInKopecks) minimalFeeInKopecks else fee
        }

        PaymentSystems.VK_PAY -> transferFeeInKopecks = 0U

    }

    return transferFeeInKopecks
}

enum class PaymentSystems {
    MASTERCARD, MAESTRO, VISA, MIR, VK_PAY
}

fun getPaymentSystem(number: Int): PaymentSystems {

    return when (number) {
        1 -> PaymentSystems.MASTERCARD
        2 -> PaymentSystems.MAESTRO
        3 -> PaymentSystems.VISA
        4 -> PaymentSystems.MIR
        else -> {
            PaymentSystems.VK_PAY
        }
    }

}

fun feeAsString(fee: UInt): String {

    val rubles = (fee / 100U).toString()
    val kopecks = (fee % 100U).toString()

    return "${rubles}руб. ${kopecks}коп."
}