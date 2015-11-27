import net.poundex.etch.dashboard.Block
import net.poundex.etch.dashboard.Dashboard
import net.poundex.etch.dashboard.Row
import net.poundex.etch.util.TextBlock

class BootStrap {

    def init = { servletContext ->
	    Block one = new TextBlock(title: "One", text: "Text one")
	    Block two = new TextBlock(title: "Two", text: "Text two")

	    Row row = new Row()
	    row.addToBlocks one
	    row.addToBlocks two

	    Dashboard dashboard = new Dashboard(name: "Dashboard")
	    dashboard.addToRows row
	    dashboard.save(flush:true, failOnError: true)

    }
    def destroy = {
    }
}
