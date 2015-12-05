import net.poundex.etch.core.Credentials
import net.poundex.etch.dashboard.Block
import net.poundex.etch.dashboard.Dashboard
import net.poundex.etch.dashboard.DoubleBlock
import net.poundex.etch.dashboard.Row
import net.poundex.etch.gmail.GmailAccount
import net.poundex.etch.google.GoogleAccount
import net.poundex.etch.mail.MailAccount
import net.poundex.etch.mail.MailBlock
import net.poundex.etch.util.EtchConfig
import net.poundex.etch.util.TextBlock

import static net.poundex.etch.util.EtchConfig.EtchConfigVar.TEST_DATA_INSTALLED

class BootStrap {

    def init = { servletContext ->

	    if (EtchConfig.get(TEST_DATA_INSTALLED) == "false") installTestData()
    }

	void installTestData()
	{
	    Block one = new TextBlock(title: "One", text: "Text one")
	    Block two = new TextBlock(title: "Two", text: "Text two")
	    Block four = new TextBlock(title: "Four", text: "Text four")

	    Block lone = new TextBlock(title: "L One", text: "L Text one")
	    Block ltwo = new TextBlock(title: "L Two", text: "L Text two")
	    save lone
	    save ltwo
	    Block dbl = new DoubleBlock(top: lone, bottom: ltwo)

	    Block five = new TextBlock(title: "next row", text: "blah")

	    Credentials googleAccount = new GoogleAccount(username: "mail@poundex.net")
	    save googleAccount
	    MailAccount mailAccount = new GmailAccount(name: 'mail@poundex.net', googleAccount: googleAccount)
	    save mailAccount
	    Block six = new MailBlock(mailAccount: mailAccount)

	    Row row = new Row()
	    row.addToBlocks one
	    row.addToBlocks two
	    row.addToBlocks dbl
	    row.addToBlocks four

	    Row row2 = new Row()
	    row.addToBlocks five
	    row.addToBlocks six

	    Dashboard dashboard = new Dashboard(name: "Dashboard")
	    dashboard.addToRows row
	    dashboard.addToRows row2
	    dashboard.save(flush:true, failOnError: true)

		EtchConfig.set(TEST_DATA_INSTALLED, "true")
    }

    def destroy = {
    }

	static <T> T save(T obj)
	{
		return obj.save(flush: true, failOnError: true)
	}
}
