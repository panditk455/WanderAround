package hu.bme.aut.httpdemo.data.weather

data class TripEntry(
    val tripId: Int,
    val destination: String,
    val startDate: String,
    val endDate: String,
    val travelCompanions: List<String> = emptyList(),
    val journalEntries: MutableList<JournalEntry> = mutableListOf(),
    val photos: MutableList<PhotoEntry> = mutableListOf(),
    val expenses: MutableList<ExpenseEntry> = mutableListOf()
) {
    fun addJournalEntry(entry: JournalEntry) {
        journalEntries.add(entry)
    }

    fun addPhoto(photo: PhotoEntry) {
        photos.add(photo)
    }

    fun addExpense(expense: ExpenseEntry) {
        expenses.add(expense)
    }

    fun totalExpenses(): Double {
        return expenses.sumOf { it.amount }
    }

    fun convertExpenses(toCurrency: String) {
        val convertedExpenses = expenses.mapNotNull { expense ->
            CurrencyRates.convertExpense(expense, toCurrency)
        }
        expenses.clear()
        expenses.addAll(convertedExpenses)
    }
}

data class JournalEntry(
    val date: String,
    val text: String,
    val photos: List<PhotoEntry> = emptyList()
)

data class PhotoEntry(
    val photoUrl: String,
    val caption: String = ""
)

data class ExpenseEntry(
    val category: String,
    val amount: Double,
    val currency: String
)
