package daniel.perez.populationapi

import daniel.perez.core.StateKey
import daniel.perez.core.StateKey.*
import daniel.perez.populationapi.State.*


object StateMapper
{
	fun map(state: StateKey): State
	{
		return map.getValue(state)
	}

	private val map: Map<StateKey, State> = mapOf(
		AK to ALASKA,
		AL to ALABAMA,
		AR to ARKANSAS,
		AS to AMERICAN_SAMOA,
		AZ to ARIZONA,
		CA to CALIFORNIA,
		CO to COLORADO,
		CT to CONNECTICUT,
		DC to DISTRICT_OF_COLUMBIA,
		DE to DELAWARE,
		FL to FLORIDA,
		GA to GEORGIA,
		GU to GUAM,
		HI to HAWAII,
		IA to IOWA,
		ID to IDAHO,
		IL to ILLINOIS,
		IN to INDIANA,
		KS to KANSAS,
		KY to KENTUCKY,
		LA to LOUISIANA,
		MA to MASSACHUSETTS,
		MD to MARYLAND,
		ME to MAINE,
		MI to MICHIGAN,
		MN to MINNESOTA,
		MO to MISSOURI,
		MP to COMMONWEALTH_OF_THE_NORTH_MARIANA_ISLANDS,
		MS to MISSISSIPPI,
		MT to MONTANA,
		NC to NORTH_CAROLINA,
		ND to NORTH_DAKOTA,
		NE to NEBRASKA,
		NH to NEW_HAMPSHIRE,
		NJ to NEW_JERSEY,
		NM to NEW_MEXICO,
		NV to NEVADA,
		NY to NEW_YORK,
		OH to OHIO,
		OK to OKLAHOMA,
		OR to OREGON,
		PA to PENNSYLVANIA,
		PR to PUERTO_RICO,
		RI to RHODE_ISLAND,
		SC to SOUTH_CAROLINA,
		SD to SOUTH_DAKOTA,
		TN to TENNESSEE,
		TX to TEXAS,
		UT to UTAH,
		VA to VIRGINIA,
		VT to VERMONT,
		WA to WASHINGTON,
		WI to WISCONSIN,
		WV to WEST_VIRGINIA,
		WY to WYOMING

	)
}