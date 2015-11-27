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

	/**
	 * @attr top
	 * @attr bottom
	 */
	def doubleblock = { attrs, body ->
		out << render(template: 'doubleblock', model: [
		        top: attrs.top, bottom: attrs.bottom
		])
	}

	def renderBlock = { attrs, body ->
		Block block = attrs.block
		Closure c = blockService.render(block)
		c.setDelegate(this)
		out << c()
	}
}
