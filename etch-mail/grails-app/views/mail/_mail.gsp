<etch:block title="${ title }">
	<div id="thing"></div>
</etch:block>

<script>

	dolphin.presentationModel('<%= UUID.randomUUID().toString() %>', 'etch.mail.mail.pm',
		dolphin.attribute('etch.mail.mailAccount.attr', null, ${ mailAccount }),
		dolphin.attribute('etch.mail.unread.attr', null, '?')
	).getAt('etch.mail.unread.attr').onValueChange(function(event) {
		$('#thing').html(event.newValue);
	});

</script>