package at.ac.fhcampuswien.corostats.data.vo


import com.google.gson.annotations.SerializedName

data class Statistic(
    @SerializedName("active_cases_critical_percentage")
    val activeCasesCriticalPercentage: String,
    @SerializedName("active_cases_mild_percentage")
    val activeCasesMildPercentage: String,
    @SerializedName("cases_with_outcome")
    val casesWithOutcome: String,
    @SerializedName("closed_cases_death_percentage")
    val closedCasesDeathPercentage: String,
    @SerializedName("closed_cases_recovered_percentage")
    val closedCasesRecoveredPercentage: String,
    @SerializedName("critical_condition_active_cases")
    val criticalConditionActiveCases: String,
    @SerializedName("currently_infected")
    val currentlyInfected: String,
    @SerializedName("death_cases")
    val deathCases: String,
    @SerializedName("death_closed_cases")
    val deathClosedCases: String,
    @SerializedName("general_death_rate")
    val generalDeathRate: String,
    @SerializedName("last_update")
    val lastUpdate: String,
    @SerializedName("mild_condition_active_cases")
    val mildConditionActiveCases: String,
    @SerializedName("recovered_closed_cases")
    val recoveredClosedCases: String,
    @SerializedName("recovery_cases")
    val recoveryCases: String,
    @SerializedName("total_cases")
    val totalCases: String
)