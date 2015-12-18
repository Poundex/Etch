package net.poundex.etch.ci

/**
 * Created by poundex on 18/12/15.
 */
interface CIService<T extends CIJob>
{
	JobStatus getJobStatusFor(T job)
	Class<T> getJobType()
}