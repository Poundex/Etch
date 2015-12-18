<g:set var="myUUID" value="${ UUID.randomUUID().toString() }" />

<etch:block title="${ title }">
	<h2 class="text-center"><i id="${ myUUID }-icon" class="fa fa-question-circle"></i> <span id="${ myUUID }-name"></span><br />
		<small><span id="${ myUUID }-lastBuilt"></span></small></h2>
</etch:block>

<g:render template="/ci/buildIcons" />

<script>

	var pm = dolphin.presentationModel('${ myUUID }', 'etch.ci.single.pm',
			dolphin.attribute('etch.ci.ciJob.attr', null, ${ ciJob }),
			dolphin.attribute('etch.ci.jobName.attr', null, '?'),
			dolphin.attribute('etch.ci.buildStatus.attr', null, '?'),
			dolphin.attribute('etch.ci.lastBuilt.attr', null, '?')
	);

	pm.getAt('etch.ci.jobName.attr').onValueChange(function(event) {
		$('#${ myUUID }-name').html(event.newValue);
	});

	pm.getAt('etch.ci.lastBuilt.attr').onValueChange(function(event) {
		$('#${ myUUID }-lastBuilt').html(moment(event.newValue).fromNow());
	});

	pm.getAt('etch.ci.buildStatus.attr').onValueChange(function(event) {
		$('#${ myUUID }-icon').removeClass();
		$('#${ myUUID }-icon').addClass("fa fa-" + buildIcons[event.newValue]);
	});

</script>