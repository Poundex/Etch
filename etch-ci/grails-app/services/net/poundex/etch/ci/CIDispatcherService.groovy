package net.poundex.etch.ci

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware

import javax.annotation.PostConstruct

class CIDispatcherService implements CIService<CIJob>, ApplicationContextAware
{
	ApplicationContext applicationContext
	Map<Class<? extends CIJob>, CIService> registry = [:]

	@PostConstruct
	void init()
	{
		applicationContext.getBeansOfType(CIService).values().each { CIService s ->
			if(s in CIDispatcherService) return
			registry[s.jobType] = s
		}
	}

	@Override
	Class getJobType()
	{
		throw new UnsupportedOperationException()
	}

	@Override
	JobStatus getJobStatusFor(CIJob job)
	{
		return registry[job.class].getJobStatusFor(job)
	}
}
