package hu.bme.aut.httpdemo.data.weather


data class CurrencyRate(
    val currencyCode: String,
    val exchangeRate: Double
)

object CurrencyRates {
    private val rates = mutableMapOf<String, Double>()

    fun updateRate(currencyCode: String, exchangeRate: Double) {
        rates[currencyCode] = exchangeRate
    }

    fun getRate(currencyCode: String): Double? {
        return rates[currencyCode]
    }

    fun convert(amount: Double, fromCurrency: String, toCurrency: String): Double? {
        val fromRate = rates[fromCurrency]
        val toRate = rates[toCurrency]
        return if (fromRate != null && toRate != null) {
            (amount / fromRate) * toRate
        } else null
    }

    // Helper function for expense conversion
    fun convertExpense(expense: ExpenseEntry, toCurrency: String): ExpenseEntry? {
        val convertedAmount = convert(expense.amount, expense.currency, toCurrency)
        return convertedAmount?.let {
            expense.copy(amount = it, currency = toCurrency)
        }
    }
}
