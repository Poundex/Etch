import net.poundex.etch.dashboard.Block
import net.poundex.etch.dashboard.Dashboard
import net.poundex.etch.dashboard.DoubleBlock
import net.poundex.etch.dashboard.Row
import net.poundex.etch.util.TextBlock

class BootStrap {

    def init = { servletContext ->
	    Block one = new TextBlock(title: "One", text: "Text one")
	    Block two = new TextBlock(title: "Two", text: "Text two")
	    Block four = new TextBlock(title: "Four", text: "Text four")

	    Block lone = new TextBlock(title: "L One", text: "L Text one")
	    Block ltwo = new TextBlock(title: "L Two", text: "L Text two")
	    [lone, ltwo]*.save(flush: true, failOnError: true)

	    Block dbl = new DoubleBlock(top: lone, bottom: ltwo)


	    Row row = new Row()
	    row.addToBlocks one
	    row.addToBlocks two
	    row.addToBlocks dbl
	    row.addToBlocks four

	    Dashboard dashboard = new Dashboard(name: "Dashboard")
	    dashboard.addToRows row
	    dashboard.save(flush:true, failOnError: true)

    }
    def destroy = {
    }
}
