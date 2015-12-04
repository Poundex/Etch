package net.poundex.etch.dashboard

import javax.persistence.MappedSuperclass

//@MappedSuperclass
abstract class Block
{
	public static final String ID = "undefined"

	Row row
	static belongsTo = [row: Row]

    static constraints = {
	    row nullable: true
    }

    static mapping = {
	    tablePerHierarchy false
    }
}
