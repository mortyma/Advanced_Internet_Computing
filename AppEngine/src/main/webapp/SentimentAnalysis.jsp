
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

  <body>

<form action="/SentimentRequest" method="post">
    <div>
    Key: <input type="text" name="key"><br>
    Since: <input type="text" name="since"><br>
    Until: <input type="text" name="until"><br>
    </div>
    <div><input type="submit" value="Analyze" /></div>
</form>

<table width="100%" border="1">
<%
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    //TODO: use ancestor query?
    Query query = new Query(); //.addSort("key", Query.SortDirection.ASCENDING);
    List<Entity> requests = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(5));
    %>
    <tr>
        <td>Key</td>      
        <td>Since</td>      
        <td>Until</td>      
        <td>Result</td>      
    </tr>
    
    
    <%
    for(int i = 0; i < requests.size(); i++)
    {
        Entity e = requests.get(i);
        %>
        <tr>
            <td><%= e.getProperty("key") %></td>
            <td><%= e.getProperty("since") %></td>
            <td><%= e.getProperty("until") %></td>
            <td><%= e.getProperty("result") %></td>
        </tr>
        <% 
    }
%>
</table>
      
      
  </body>
</html>