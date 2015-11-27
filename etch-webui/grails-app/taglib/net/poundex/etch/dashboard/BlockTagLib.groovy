package net.poundex.etch.dashboard

import org.springframework.beans.factory.annotation.Autowired

class BlockTagLib {
    static defaultEncodeAs = [taglib:'none']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
	static namespace = "etch"

	@Autowired
	BlockService blockService

	/**
	 * @attr title REQUIRED
	 */
    def block = { attrs, body ->
	    out << render(template: 'block', model: [
	            blockTitle: attrs['title'] ?: '???',
			    blockBody: body()
	    ])
    }

	def doubleBlock = { attrs, body ->
		out << body(halfBlock: true, bix: 1)
	}

	def renderBlock = { attrs, body ->
		Block block = attrs.block
		Closure c = blockService.render(block)
		c.setDelegate(this)
//		c.setResolveStrategy(DELEGATE_FIRST)
		out << c()
	}
}
