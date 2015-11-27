package net.poundex.etch.block

import groovy.transform.CompileStatic
import net.poundex.etch.dashboard.Block

@CompileStatic
public interface BlockRenderer<T extends Block>
{
	Map<String, BlockRenderer<T>> register()
	Closure render(T block)
}