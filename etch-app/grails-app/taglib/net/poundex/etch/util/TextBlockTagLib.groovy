package net.poundex.etch.util

class TextBlockTagLib
{
    static defaultEncodeAs = [taglib:'none']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    static namespace = "etch"

	/**
	 * @attr title REQUIRED
	 * @attr text REQUIRED
	 */
	def text = { attrs, body ->
		out << render(template: '/util/text', model: [title: attrs.title, text: attrs.text])
	}
}
