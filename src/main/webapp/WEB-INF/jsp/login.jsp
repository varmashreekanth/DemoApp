<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
	<head>

	<!-- Load CSS -->
	<link rel="stylesheet" type="text/css" href='<c:url value="/resources/css/styles.css"/>' />
	<link rel="stylesheet" href='<c:url value="/resources/css/bootstrap.min.css"/>' />
	
	
	<!-- Load Javasript -->
	<script src='<c:url value="/resources/js/jquery-1.10.2.min.js" />'></script>
	<script src='<c:url value="/resources/js/jquery-2.1.4.js" />'></script>
	<script src='<c:url value="/resources/js/jquery.min.js" />'></script>
	
	<style>
	.error {
		color: #ff0000;
	}
	
	.errorblock {
		color: #000;
		background-color: #ffEEEE;
		border: 3px solid #ff0000;
		padding: 8px;
		margin: 16px;
		text-align: left;
	}
</style>
	</head>
	<body style="background-color:#DCDCDC; ">
	<div id="header">
      <div id="logo">
        <div id="logo_text">
          <!-- class="logo_colour", allows you to change the colour of the text -->
          <h1><a href="load-home-page.do">Student Management System<span class="logo_colour">SMS</span></a></h1>
        </div>
      </div>
      <div id="menubar">
        <ul id="menu">
          <!-- put class="selected" in the li tag for the selected page - to highlight which page you're on -->
<!--           <li class="selected"><a href="load-home-page.do">Home</a></li> -->
<!--           <li><a href="examples.html">Examples</a></li> -->
<!--           <li><a href="page.html">A Page</a></li> -->
<!--           <li><a href="another_page.html">Another Page</a></li> -->
<!--           <li><a href="contact.html">Contact Us</a></li> -->
        </ul>
      </div>
    </div>
	<script>
		function validateUserName(){
			var userVar = $("#username").val();
			//alert("userVar-->"+userVar);
			if(userVar == ''){
				alert("UserName cannot be empty");
				return false;
			}
			else{
				return true;
			}
		}
		function validatepwd(){
			var pwdVar = $("#password").val();
			//alert("pwdVar-->"+pwdVar);
			if(pwdVar == ''){
				alert("Password cannot be empty");
				return false;
			}
			else{
				return true;
			}
		}
		function basicCheck(){
			//alert("inside basicCheck");
			 if(validateUserName()){
				if(validatePwd()){
					return true;
				}
				else{
					return false;
				}
			}
			else{
				return false;
			} 
			
		}
		function signUp(){
			url="register.do";
			window.location.assign(url);
		}
	</script>
		<h2 align="center">Login Form</h2><hr>
<%-- 		<h3>${userPwdError}</h3> --%>
		
		<div class="container">
		<form:form class="form-horizontal" action="loginSubmit.do" commandName="loginForm" method="post" onsubmit="return basicCheck()">
		<form:errors path="*" cssClass="errorblock" element="div" />
			<!--<table  bgcolor="#DCDCDC"  style="align:center;float:center;width:50%">
				<tr>
				<td colspan="20" class="error">${userPwdError}</td>
				</tr>
				<tr>
					<td>UserName</td>
					<td><form:input path="username" name="username" onchange="validateUserName()"/></td>
					<td><form:errors path="username" cssClass="error"/></td>
				</tr>
				<tr>
					<td>PassWord</td>
					<td><form:password path="password" onchange="validatepwd()"/></td>
					<td><form:errors path="password" cssClass="error"/></td>
				</tr>
				<tr>
					<td align="center"><input type="submit" value="Login"></td>
					<td colspan="2" align="right" style="text-align: right;"><input type="button" name="SignUp" id="SignUp" value="SignUp" onclick="signUp();"></td>
				</tr>
			</table>-->

					<div class="form-group">
                        <label for="username" class="col-sm-3 control-label" >UserName</label>
                        <div class="col-sm-9">
                            <form:input  path="username" cssClass="form-control" name="username" onchange="validateUserName()"/>
                            <form:errors path="username" cssClass="error"/>
                            <span class="help-block">User Name, eg.: Alpha</span>
                        </div>
                    </div>
                    
                	<div class="form-group">
                        <label for="password" class="col-sm-3 control-label" >Password</label>
                        <div class="col-sm-9">
                            <form:password  path="password" cssClass="form-control" name="password" onchange="validatepwd()"/>
                            <form:errors path="password" cssClass="error"/>
                            <span class="help-block">Password</span>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                            <input type="submit" value="Login" class="btn btn-primary btn-block"/>
                            <input type="button" name="SignUp" class="btn btn-primary btn-block" id="SignUp" value="SignUp" onclick="signUp();"/>
                        </div>
                    </div>

		</form:form>
		</div>
		
		<script>
			function checkErrors(){
				var errMsg= "${userPwdError}";
				alert("inside checkErrors--->"+"${userPwdError}");
				if("${userPwdError}"!=''){
					//alert("1");
					$("#userPwdErr").prop('display','block');
					document.getElementById("userPwdErr").style.display='block';
					//$("#userPwdErr").val(errMsg);
					
				}
				else{
					//alert("2");
					$("#userPwdErr").prop('display','none');
				}
			}
			//checkErrors();
		</script>
	</body>
</html>