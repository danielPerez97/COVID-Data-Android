package daniel.perez.covidapi

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StateData(
    val date: String,
    val state: String,
    val positive: Long,
    val negative: Long
)