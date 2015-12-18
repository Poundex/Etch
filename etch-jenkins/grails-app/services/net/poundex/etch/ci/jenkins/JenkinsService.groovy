package net.poundex.etch.ci.jenkins

import com.offbytwo.jenkins.JenkinsServer
import com.offbytwo.jenkins.model.BuildWithDetails
import com.offbytwo.jenkins.model.Job
import net.poundex.etch.ci.CIService
import net.poundex.etch.ci.JenkinsJob
import net.poundex.etch.ci.JobStatus

class JenkinsService implements CIService<JenkinsJob>
{

	@Override
	JobStatus getJobStatusFor(JenkinsJob job)
	{
		JenkinsServer jenkinsServer = new JenkinsServer(job.jenkinsServer.endpointURL.toURI())
		Map<String, Job> jobs = jenkinsServer.jobs
		BuildWithDetails buildWithDetails = jobs.values().find { it.name == job.name }.details().builds.first().details()
		return new JobStatus(status: JobStatus.Status.valueOf(buildWithDetails.result.toString()), lastBuilt: buildWithDetails.timestamp)
	}

	@Override
	Class<JenkinsJob> getJobType()
	{
		return JenkinsJob
	}
}
