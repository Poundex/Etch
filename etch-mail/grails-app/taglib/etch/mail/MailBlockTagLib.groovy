package etch.mail

class MailBlockTagLib
{
    static defaultEncodeAs = [taglib:'none']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def mail = { attrs, body ->
	    out << render(template: '/mail/mail', model: [title: attrs.title, mailAccount: attrs.mailAccount])
    }
}
