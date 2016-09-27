<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <title>Add Post</title>
        <meta name="generator" content="Bootply" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link href='<c:url value="/resources/css/bootstrap.min.css" />' rel="stylesheet">
        <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css" rel="stylesheet">
        <!--[if lt IE 9]>
            <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <link href='<c:url value="/resources/css/styles.css" />' rel="stylesheet">

    <!-- script references -->
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
        <script src='<c:url value="/resources/js/bootstrap.min.js" />'></script>
        <script src='<c:url value="/resources/js/scripts.js" />'></script>
        
	</head>
	<body>
	
		<br>
		
		<nav class="navbar navbar-static">
           <div class="container">
            <div class="navbar-header">
              <a class="navbar-brand" href="http://www.bootply.com" target="ext"><b>Bootply</b></a>
              <a class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="glyphicon glyphicon-chevron-down"></span>
              </a>
            </div>
              <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">  
                  <li><a href="#">Link</a></li>
                  <li><a href="#">Link</a></li>
                  <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Channels</a>
                    <ul class="dropdown-menu">
                      <li><a href="#">Sub-link</a></li>
                      <li><a href="#">Sub-link</a></li>
                      <li><a href="#">Sub-link</a></li>
                      <li><a href="#">Sub-link</a></li>
                      
                    </ul>
                  </li>
                </ul>
                <ul class="nav navbar-right navbar-nav">
                  <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-search"></i></a>
                    <ul class="dropdown-menu" style="padding:12px;">
                        <form class="form-inline">
                            <button type="submit" class="btn btn-default pull-right"><i class="glyphicon glyphicon-search"></i></button><input type="text" class="form-control pull-left" placeholder="Search">
                        </form>
                     </ul>
                  </li>
                  <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-user"></i> <i class="glyphicon glyphicon-chevron-down"></i></a>
                    <ul class="dropdown-menu">
                      <li><a href="#">Login</a></li>
                      <li><a href="#">Profile</a></li>
                      <li class="divider"></li>
                      <li><a href="#">About</a></li>
                     </ul>
                  </li>
                </ul>
              </div>
            </div>
        </nav><!-- /.navbar -->

        <header class="masthead">
              <div class="container">
                <div class="row">
                  <div class="col-md-6">
                    <h1><a href="#" title="Scroll down for your viewing pleasure">Bootable Template</a>
                      <p class="lead">3-column Theme + Layout for Bootstrap 3.</p></h1>
                  </div>
                  <div class="col-md-6">
                    <div class="well pull-right">
                      <img src="//placehold.it/280x100/E7E7E7">        
                    </div>
                  </div>
                </div>
              </div>
            </header>

	<!--<h2 align="center">Add Post</h2><hr>-->

			<form:form class="form-horizontal" action="add-post.do" commandName="post" method="post" enctype="multipart/form-data">
                    <h2>Add Post</h2>
                    <div class="form-group">
                        <label for="nameOfPost" class="col-sm-3 control-label" >Name of the Post</label>
                        <div class="col-sm-9">
                            <form:input  path="nameOfPost" cssClass="form-control"/>
                            <span class="help-block">Name of the Post, eg.: My First Post</span>
                        </div>
                    </div>
                    
                
                    <div class="form-group">
                        <label for="postType" class="col-sm-3 control-label">Post Type</label>
                        <div class="col-sm-9">
                            <form:select path="postType" cssClass="form-control">
                                <form:option value="Private"/>
								<form:option value="Public"/>          
                            </form:select>
                            <span class="help-block">Type of the post ,eg.: Public,Private</span>
                        </div>
                    </div> <!-- /.form-group -->
                    

                    <div class="form-group">
                        <label for="postDescription" class="col-sm-3 control-label">Post Description</label>
                        <div class="col-sm-9">
                            <form:textarea path="postDescription"  cssClass="form-control" rows="4"/>
                            <span class="help-block">Last Name, First Name, eg.: Smith, Harry</span>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="imagePost" class="col-sm-3 control-label" >Image file</label>
                        <div class="col-sm-9">
                            <input  type="file" name="imagePost" id="imagePost" class="form-control"/>
                            <span class="help-block">Image File, eg.: My First Post</span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="videoPost" class="col-sm-3 control-label" >Video file</label>
                        <div class="col-sm-9">
                            <input  type="file" name="videoPost" id="videoPost" class="form-control"/>
                            <span class="help-block">Video File, eg.: My First Post</span>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                            <input type="submit" value="Post" class="btn btn-primary btn-block"/>
                        </div>
                    </div>
                </form:form> <!-- /form -->
			
	
	<body>
</html>