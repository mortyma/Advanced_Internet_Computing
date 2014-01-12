
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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

  </body>
</html>