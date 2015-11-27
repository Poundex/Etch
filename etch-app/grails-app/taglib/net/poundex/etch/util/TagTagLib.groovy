package net.poundex.etch.util

class TagTagLib
{
	static defaultEncodeAs = [taglib: 'none']
	//static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

	static namespace = "util"

	/**
	 * @attr ns REQUIRED
	 * @attr tag REQUIRED
	 */
	def tag = { attrs, body ->
		out << this[attrs.ns].invokeMethod(attrs.tag, [attrs, body])
	}

}
