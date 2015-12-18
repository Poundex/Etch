<g:if test="${ ! buildIconsLoaded }">
	<script>
		var buildIcons = {
			FAILURE: "times",
			UNSTABLE: "exclamation",
			REBUILDING: "progress",
			BUILDING: "spinner",
			ABORTED: "ban",
			SUCCESS: "check",
			UNKNOWN: "question-circle",
			NOT_BUILT: "times"
		}
	</script>
</g:if>
<g:set var="buildIconsLoaded" value="${ true }" />