package net.poundex.etch.dashboard

class Dashboard
{
	String name

	List<Row> rows = []
	static hasMany = [rows: Row]

    static constraints = {
    }
}
