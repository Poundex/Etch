package etch.ci

class SingleBuildTagLib
{
    static defaultEncodeAs = [taglib:'none']
	static namespace = "etch"

	def singleBuild = { attrs, body ->
		out << render(template: '/ci/singleBuild', model: [title: attrs.title, ciJob: attrs.ciJob])
	}
}
