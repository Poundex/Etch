package net.poundex.etch.dashboard

import grails.transaction.Transactional
import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode
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

	Closure render(SingleBlock block)
	{
		return registry[block.class.ID].render(block)
	}
}
