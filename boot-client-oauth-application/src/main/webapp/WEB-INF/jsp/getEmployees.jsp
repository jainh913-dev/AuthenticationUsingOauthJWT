<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Get Employees</title>
</head>
<body>
    <h2 style="color: blue;">Get Employee Info</h2>

    <div id="getEmployees">
        <form:form action="http://localhost:8080/oauth/authorize"
            method="post" modelAttribute="emp">
              
                 <input type="text" name="response_type" value="code" /> <br/>
                <br/> <input type="text" name="client_id" value="javainuse" /> <br/>
                 <br/><input type="text" name="redirect_uri" value="http://localhost:8090/showEmployees" /> <br/>
                 <br/><input type="text" name="scope" value="read" /> <br/>
                 <br/><input type="SUBMIT" value="Get Employee info" />
        </form:form>
    </div>
</body>
</html>