<g:if test="${ ! bix || ( bix && bix == 1 ) }">
	<div class="col-sm-3 col-lg-3">
</g:if>
	<div class="${ halfBlock ? "half-unit" : "dash-unit" }">
		<dtitle>${blockTitle}</dtitle>
		<hr>
		${ raw(blockBody) }
	</div>
<g:if test="${ ! bix || ( bix && bix == 2 ) }">
	</div>
</g:if>

<%
    if(bix) bix++
%>