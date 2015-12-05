package net.poundex.etch.dashboard

import net.poundex.etch.util.EtchConfig

import static net.poundex.etch.util.EtchConfig.EtchConfigVar.DEFAULT_DASHBOARD
import static org.springframework.http.HttpStatus.*

class DashboardController
{
	def index()
	{
		Dashboard d

		Long defaultDashboard = EtchConfig.get(DEFAULT_DASHBOARD).toLong()
		if (defaultDashboard == 0 || ! (d = Dashboard.get(defaultDashboard)))
			d = Dashboard.list().find()

		if ( ! d)
			return render(status: NOT_FOUND)

		redirect action: 'dashboard', params: [name: d.name]
	}

    def dashboard()
    {
	    Dashboard d
	    if( ! params.name || ! (d = Dashboard.findByName(params.name)))
		    return render(status: NOT_FOUND)

	    render view: 'index', model: [dashboard: d]
    }
}
