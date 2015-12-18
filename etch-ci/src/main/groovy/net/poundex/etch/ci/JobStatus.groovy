package net.poundex.etch.ci

import groovy.transform.Canonical
import groovy.transform.Immutable

import java.time.LocalDate

/**
 * Created by poundex on 18/12/15.
 */
@Canonical
class JobStatus
{
	Status status
//	LocalDate lastBuilt
	def lastBuilt

	public static enum Status {
		FAILURE,
		UNSTABLE,
		REBUILDING,
		BUILDING,
		ABORTED,
		SUCCESS,
		UNKNOWN,
		NOT_BUILT
	}
}
