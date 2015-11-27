package net.poundex.etch.block

import groovy.transform.CompileStatic
import net.poundex.etch.dashboard.SingleBlock

@CompileStatic
public interface BlockRenderer<T extends SingleBlock>
{
	Map<String, BlockRenderer<T>> register()
	Closure render(T block)
}