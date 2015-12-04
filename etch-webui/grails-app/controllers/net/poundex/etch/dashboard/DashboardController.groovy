package net.poundex.etch.dashboard

import static org.springframework.http.HttpStatus.*

class DashboardController
{
    def dashboard() {
	    session.invalidate()

	    Dashboard d
	    if( ! params.name || ! (d = Dashboard.findByName(params.name)))
		    return render(status: NOT_FOUND)

	    render view: 'index', model: [dashboard: d]
    }
}
