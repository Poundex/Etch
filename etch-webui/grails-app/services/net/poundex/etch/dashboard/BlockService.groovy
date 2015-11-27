package net.poundex.etch.dashboard

import grails.transaction.Transactional
import net.poundex.etch.block.BlockRenderer
import org.springframework.beans.factory.annotation.Autowired

import javax.annotation.PostConstruct

@Transactional(readOnly = true)
class BlockService
{
	@Autowired
	private List<BlockRenderer> allBlockRenderers

	private Map<String, BlockRenderer> registry = [:]

	@PostConstruct
	void init()
	{
		allBlockRenderers.each {
			registry.putAll it.register()
		}
	}

	Closure render(Block block)
	{
		return registry[block.class.ID].render(block)
	}
}
