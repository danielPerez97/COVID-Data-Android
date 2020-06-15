package daniel.perez.core

import daniel.perez.core.StateKey.*


object StateStringMapper
{
	fun map(key: StateKey): String
	{
		return map.getValue(key)
	}

	private val map: Map<StateKey, String> = mapOf(
		AK to "Alaska",
		AL to "Alabama",
		AR to "Arkansas",
		AS to "American Samoa",
		AZ to "Arizona",
		CA to "California",
		CO to "Colorado",
		CT to "Connecticut",
		DC to "District of Columbia",
		DE to "Delaware",
		FL to "Florida",
		GA to "Georgia",
		GU to "Guam",
		HI to "Hawaii",
		IA to "Iowa",
		ID to "Idaho",
		IL to "Illinois",
		IN to "Indiana",
		KS to "Kansas",
		KY to "Kentucky",
		LA to "Louisiana",
		MA to "Massachusetts",
		MD to "Maryland",
		ME to "Maine",
		MI to "Michigan",
		MN to "Minnesota",
		MO to "Missouri",
		MP to "Commonwealth Of The North Mariana Islands",
		MS to "Mississippi",
		MT to "Montana",
		NC to "North Carolina",
		ND to "North Dakota",
		NE to "Nebraska",
		NH to "New Hampshire",
		NJ to "New Jersey",
		NM to "New Mexico",
		NV to "Nevada",
		NY to "New York",
		OH to "Ohio",
		OK to "Oklahoma",
		OR to "Oregon",
		PA to "Pennsylvania",
		PR to "Puerto Rico",
		RI to "Rhode Island",
		SC to "South Carolina",
		SD to "South Dakota",
		TN to "Tennessee",
		TX to "Texas",
		UT to "Utah",
		VA to "Virginia",
		VT to "Vermont",
		WA to "Washington",
		WI to "Wisconsin",
		WV to "West Virginia",
		WY to "Wyoming"
	)
}