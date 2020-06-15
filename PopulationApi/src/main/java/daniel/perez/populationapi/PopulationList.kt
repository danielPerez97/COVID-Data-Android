package daniel.perez.populationapi

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PopulationList(
	@Json(name = "data") val list: List<Population>
)