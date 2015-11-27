package net.poundex.etch.block

import net.poundex.etch.dashboard.BlockService
import net.poundex.etch.dashboard.DoubleBlock
import org.springframework.beans.factory.annotation.Autowired

class DoubleBlockRenderer implements BlockRenderer<DoubleBlock>
{
	@Autowired
	BlockService blockService

	@Override
	Map<String, BlockRenderer<DoubleBlock>> register()
	{
		["etch.doubleblock": this]
	}

	@Override
	Closure render(DoubleBlock block)
	{
		return {
			etch.doubleblock([top: block.top, bottom: block.bottom])
		}
	}
}
