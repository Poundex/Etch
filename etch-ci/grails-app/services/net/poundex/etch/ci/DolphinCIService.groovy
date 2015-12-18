package net.poundex.etch.ci

import net.poundex.etch.dolphin.DolphinListener
import net.poundex.etch.dolphin.DolphinService
import org.opendolphin.core.ModelStoreEvent
import org.opendolphin.core.PresentationModel
import org.opendolphin.core.server.ServerDolphin
import org.springframework.beans.factory.annotation.Autowired

class DolphinCIService implements DolphinService
{
	static scope = 'session'

	@Autowired
	ServerDolphin serverDolphin

	@Autowired
	CIDispatcherService ciDispatcherService

	@DolphinListener('etch.ci.single.pm')
	void jobStatus(ModelStoreEvent e)
	{
		if (e.type != ModelStoreEvent.Type.ADDED) return

		PresentationModel pm = e.presentationModel
		Long jobID = pm['etch.ci.ciJob.attr'].value
		CIJob job
		if (!jobID || !(job = CIJob.get(jobID)))
			throw new RuntimeException("no job id")

		JobStatus jobStatus = ciDispatcherService.getJobStatusFor(job)
		pm['etch.ci.jobName.attr'].setValue(job.name)
		pm['etch.ci.buildStatus.attr'].setValue(jobStatus.status.toString())
		pm['etch.ci.lastBuilt.attr'].setValue(jobStatus.lastBuilt)
	}
}
