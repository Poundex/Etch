package net.poundex.etch.ci

import net.poundex.etch.block.BlockRenderer
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by poundex on 26/11/15.
 */
class BuildStatusRenderer implements BlockRenderer<SingleBuildBlock>
{
	@Autowired
	CIDispatcherService ciDispatcherService

	@Override
	Map<String, BlockRenderer<SingleBuildBlock>> register()
	{
		['etch.ci.single': this]
	}

	@Override
	Closure render(SingleBuildBlock block)
	{
		return {
			etch.singleBuild(title: block.ciJob.name, ciJob: block.ciJob.id)
		}
	}
}
