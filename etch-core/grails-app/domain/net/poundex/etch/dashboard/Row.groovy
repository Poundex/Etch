package net.poundex.etch.dashboard

class Row
{
	List<Block> blocks = []

	static belongsTo = [dashboard: Dashboard]
	static hasMany = [blocks: Block]

    static constraints = {
    }
}
