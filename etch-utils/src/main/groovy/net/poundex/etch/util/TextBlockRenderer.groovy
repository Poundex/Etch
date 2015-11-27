package net.poundex.etch.util

import net.poundex.etch.block.BlockRenderer

/**
 * Created by poundex on 26/11/15.
 */
class TextBlockRenderer implements BlockRenderer<TextBlock>
{
	@Override
	Map<String, BlockRenderer<TextBlock>> register()
	{
		['etch.text': this]
	}

	@Override
	Closure render(TextBlock block)
	{
		return {
			etch.text([title: block.text, text: block.text])
		}
	}
}
