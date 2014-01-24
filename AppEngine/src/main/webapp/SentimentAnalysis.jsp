
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import="com.google.appengine.api.datastore.Query" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<%@ page import="com.google.appengine.api.datastore.FetchOptions" %>
<%@ page import="com.google.appengine.api.datastore.Key" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>

<head>

<link type="text/css" href="stylesheets/table.css" rel="stylesheet" />
<link type="text/css" href="stylesheets/datepicker.css" rel="stylesheet" />

<script type="text/javascript" src="datepickr.js"></script>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="jquery.tablesorter.js"></script>

<script type="text/javascript">
$(document).ready(function(){
    $("#tbmain").tablesorter();
 
});

function checkStatus(did){
	$.post( "SentimentCheckTaskStatus",{id:did} ,function( data ) {
		//alert( "" + data );
		window.location.reload();
		});
}

function refresh(){
	window.location.reload();
}

function sendRequest(_key,_since,_until){
	$.post( "/SentimentRequest", {key : _key, since : _since, until : _until} );
}

function batch(){
	sendRequest("jordan","2014-01-22","2014-01-24");
	sendRequest("obama","2014-01-22","2014-01-24");
	sendRequest("austria","2014-01-22","2014-01-24");
	sendRequest("europe","2014-01-22","2014-01-24");
	sendRequest("asia","2014-01-22","2014-01-24");
	//window.location.reload();
}
</script>

</head>

<body>

<div align="center">
<h1>AIC WS2013 Group 3</h1>
</div>

<div align="center">
<form action="/SentimentRequest" method="post">
    <div>
    <table>
    <tr>
        <td align="right">Key:</td>
        <td><input type="text" name="key"></td>
    </tr>
    <tr>
        <td align="right">Since:</td>
        <td>
        <input type="text" id="dp1" name="since">
        <script type='text/javascript'>
   
            new datepickr('dp1', {
    				'dateFormat': 'Y-m-d'
    		});
   
        </script>
        </td>
    </tr>
    <tr>
        <td align="right">Until:</td>
        <td>
        <input type="text" id="dp2" name="until">
        <script type='text/javascript'>
   
            new datepickr('dp2', {
    				'dateFormat': 'Y-m-d'
    		});
   
        </script>
        </td>
    </tr>
    <tr>
        <td></td>
        <td align="right"><input type="submit" value="Analyze"/></td>
    </tr>
    <tr>
        <td></td>
        <td align="right"><input type="button" value="batch data" onclick="batch()"/></td>
    </tr>
    
    </table>
    </div>
</form>
</div>
<br>
<div align="center">
<table id="tbmain" class="tablesorter">
<%
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    //TODO: use ancestor query?
    Query query = new Query(); //.addSort("key", Query.SortDirection.ASCENDING);
    List<Entity> requests = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(20));
    %>
    <thead>
    <tr>
        <th width="100">Key</th>      
        <th width="200">Since</th>      
        <th width="200">Until</th>      
        <th width="400">Result</th>      
    </tr>
    </thead>
    <tbody>
    <%
    for(int i = 0; i < requests.size(); i++)
    {
        Entity e = requests.get(i);
        if(e.getProperty("result")==null){%>
        	<script type="text/javascript">
        	   checkStatus("<%= e.getProperty("key") %>");
        	</script>
        	<%
        }
        %>
        <tr>
            <td><%= e.getProperty("key") %></td>
            <td><%= e.getProperty("since") %></td>
            <td><%= e.getProperty("until") %></td>
            <td><%= e.getProperty("result")==null?"computing...":e.getProperty("result") %></td>
        </tr>
        <% 
    }
    %>
    </tbody>
</table>

<br>
<input type="button" value="refresh" onclick="refresh()"/>

</div>      


      
</body>
</html>