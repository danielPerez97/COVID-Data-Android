package daniel.perez.populationapi

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Population(
	@Json(name = "State") val state: String,
	@Json(name = "ID Year") val year: Long,
	@Json(name = "Population") val population: Long
)