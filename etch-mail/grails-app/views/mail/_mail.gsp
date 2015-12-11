<g:set var="myUUID" value="${ UUID.randomUUID().toString() }" />

<etch:block title="${ title }">
	<h2 class="text-center"><i class="fa fa-envelope"></i> <span id="${ myUUID }"></span></h2>
</etch:block>

<script>

	dolphin.presentationModel('${ myUUID }', 'etch.mail.mail.pm',
		dolphin.attribute('etch.mail.mailAccount.attr', null, ${ mailAccount }),
		dolphin.attribute('etch.mail.unread.attr', null, '?')
	).getAt('etch.mail.unread.attr').onValueChange(function(event) {
		$('#${ myUUID }').html(event.newValue);
	});

</script>