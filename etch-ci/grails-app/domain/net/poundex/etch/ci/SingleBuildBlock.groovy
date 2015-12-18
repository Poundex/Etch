package net.poundex.etch.ci

import net.poundex.etch.dashboard.SingleBlock

class SingleBuildBlock extends SingleBlock
{
	public static final String ID = "etch.ci.single"

	CIJob ciJob

    static constraints = {
    }
}
