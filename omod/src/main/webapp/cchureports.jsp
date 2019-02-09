<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<openmrs:htmlInclude file="/moduleResources/cchureports/jquery.js" />
<!-- <script type="text/javascript">
	var $j = jQuery.noConflict(); 
</script> -->
<script type="text/javascript">
function msgreg(){
document.getElementById('msg').innerHTML="<div id='openmrs_msg'>Registering...</div>";
exit();
}
function msgrem(){
	document.getElementById('msg').innerHTML="<div id='openmrs_msg'>Removing...</div>";
	exit();
	}
</script>
<style>
table.reports {
	border-collapse: collapse;
	border: 1px solid blue;
	width: 100%;
}

.reports td {
	border-collapse: collapse;
	border: 1px solid blue;
}

.reports .tableheaders {
	font-weight: bold;
	background-color: #B0C4DE;
}

.reports .tabletd {
	font-weight: bold;
	background-color: #EEE;
}

.reports .alt {
	background-color: #B0C4DE;
}

.reports .altodd {
	background-color: #EEE;
}

.reports .hover {
	background-color: #DED;
}

.reports .althover {
	background-color: #EFE;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$('tr:even').addClass('alt');
	$('tr:even').hover(
			function(){$(this).addClass('hover')},
			function(){$(this).removeClass('hover')}
	);	
	$('tr:odd').addClass('altodd');
	$('tr:odd').hover(
			function(){$(this).addClass('althover')},
			function(){$(this).removeClass('althover')}
	);
});
</script>
<div id="msg"></div>
<h2>Register/Remove CCHU Reports</h2>

<br />
<br />

<table class="reports" style="width: 100%;">
	<tr class="tableheaders">
		<td>Categories</td>
		<td>Report Name</td>
		<td>Run</td>
		<td colspan="2"><center>Action</center></td>
	</tr>
	<tr>
		<td rowspan="3" class="tabletd">Log Book Reports</td>
		<td>OR Log Book Report</td>
		<td>Central</td>
		<td><a
			href="${pageContext.request.contextPath}/module/cchureports/register_ORLogBook.form"
			onclick=msgreg(this)>(Re) register</a></td>
		<td><a
			href="${pageContext.request.contextPath}/module/cchureports/remove_ORLogBook.form"
			onclick=msgrem(this)>Remove</a></td>
	</tr>
	<tr>
		<td>Plastic Surgery Log Book Report</td>
		<td>Central</td>
		<td><a
			href="${pageContext.request.contextPath}/module/cchureports/register_PSLoggBook.form"
			onclick=msgreg(this)>(Re) register</a></td>
		<td><a
			href="${pageContext.request.contextPath}/module/cchureports/remove_PSLoggBook.form"
			onclick=msgrem(this)>Remove</a></td>
	</tr>
	<tr>
		<td>Summary Values Report</td>
		<td>Central</td>
		<td><a
			href="${pageContext.request.contextPath}/module/cchureports/register_SVReport.form"
			onclick=msgreg(this)>(Re) register</a></td>
		<td><a
			href="${pageContext.request.contextPath}/module/cchureports/remove_SVReport.form"
			onclick=msgrem(this)>Remove</a></td>
	</tr>

	<tr>
		<td rowspan="4" class="tabletd">General Reports</td>
		<td>Follow-up Report</td>
		<td>Central</td>
		<td><a
			href="${pageContext.request.contextPath}/module/cchureports/register_followupsReport.form"
			onclick=msgreg(this)>(Re) register</a></td>
		<td><a
			href="${pageContext.request.contextPath}/module/cchureports/remove_followupsReport.form"
			onclick=msgrem(this)>Remove</a></td>
	</tr>
	<tr>
		<td>Late Visit and Lost to Follow Up Report</td>
		<td>Central</td>
		<td><a
			href="${pageContext.request.contextPath}/module/cchureports/register_lateVisitandLTFUReport.form"
			onclick=msgreg(this)>(Re) register</a></td>
		<td><a
			href="${pageContext.request.contextPath}/module/cchureports/remove_lateVisitandLTFUReport.form"
			onclick=msgrem(this)>Remove</a></td>
	</tr>

	<tr>
		<td>Surgical Procedures Report</td>
		<td>Central</td>
		<td><a
			href="${pageContext.request.contextPath}/module/cchureports/register_surgicalProceduresReport.form"
			onclick=msgreg(this)>(Re) register</a></td>
		<td><a
			href="${pageContext.request.contextPath}/module/cchureports/remove_surgicalProceduresReport.form"
			onclick=msgrem(this)>Remove</a></td>
	</tr>

	<tr>
		<td>IF Interim Report</td>
		<td>Central</td>
		<td><a
			href="${pageContext.request.contextPath}/module/cchureports/register_ifInterimReport.form"
			onclick=msgreg(this)>(Re) register</a></td>
		<td><a
			href="${pageContext.request.contextPath}/module/cchureports/remove_ifInterimReport.form"
			onclick=msgrem(this)>Remove</a></td>
	</tr>


	<tr class="tableheaders">
		<td colspan="2">All Reports</td>
		<td>Central</td>
		<td><a
			href="${pageContext.request.contextPath}/module/cchureports/register_allReports.form"
			onclick=msgreg(this)>Register All</a></td>
		<td><a
			href="${pageContext.request.contextPath}/module/cchureports/remove_allReports.form"
			onclick=msgrem(this)>Remove All</a></td>
	</tr>



</table>
<%@ include file="/WEB-INF/template/footer.jsp"%>