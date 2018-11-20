<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
	<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>每日反馈-学生</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/ace-admin/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/ace-admin/font-awesome/4.5.0/css/font-awesome.min.css" />
<!-- page specific plugin styles -->

<!-- text fonts -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/ace-admin/css/fonts.googleapis.com.css" />

<!-- ace styles -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/ace-admin/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/ace-admin/css/ace-skins.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/ace-admin/css/ace-rtl.min.css" />
<script src="${pageContext.request.contextPath}/lib/ace-admin/js/ace-extra.min.js"></script>
<style type="text/css">
li{
    list-style: none;margin-left: 40px;margin-top: 5px} 
ul{clear: both;font-size: 15.5px;}
</style>
	</head>

	<body class="no-skin" >
	    

		<div class="main-container ace-save-state" id="main-container">
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>
            <!--- 左边栏-菜单 start -->

			<!--- 内容主体 start -->
			<div class="main-content">
				<div class="main-content-inner">
				   

					<!-- 内容页 start -->
					<!-- 标题start -->
					<div class="page-content">
				          			<div class="widget-box transparent" id="widget-box-13">
												<div class="widget-header">
													<h3 class="widget-title lighter">${feedback.feedback_create_date }-${feedback.feedback_title }</h3>

													<div class="widget-toolbar no-border" >
														<ul class="nav nav-tabs" id="myTab2" style="list-style-type:none">
															<li class="active" >
																<a data-toggle="tab" href="#setPassword">反馈问卷</a>
															</li>
														</ul>
													</div>
												</div>
												<!-- 标题ending -->
												<div class="widget-body">
													<div class="widget-main padding-12 no-padding-left no-padding-right">
														<div class="tab-content padding-4" >
															

													        
															<div  class="tab-pane in active" >
																<h3 style="text-align: center;">${add_msg }</h3>
															   <%-- ${requestScope.admin_edit_password_msg } --%>
															   <c:set value="${fn:split(feedback.feedback_chapters, ',') }" var="str" />
															   <form action="${pageContext.request.contextPath }/studentfeedback/addStudentFeedback.do" method="post" id="myFrom" >
																<div style="font-size: 15.5px;margin-left: 18px"> 学生名：<input type="text" name="student_name" /></div>
																<input type="hidden" name="feedback_id" value="${feedback.feedback_id }"/>
																<input type="hidden" name="length" value="${feedback.length }"/>
																 <div class="form-group">
																 <br>
																 <c:forEach items="${requestScope.feedback.chapters}" var="chapter" varStatus="a">
																 <ul style="list-style-type:none">
																    <p>${a.count } .${chapter.chapter_title}(${chapter.chapter_summary })</p>
																    <li>
																     <input type="radio" name="feedbackGrade${a.count }" value="A" />
																     <span class="votechoicename">完全理解</span></li>
																    <li>
																     <input type="radio" name="feedbackGrade${a.count }" value="B" />
																     <span class="votechoicename">大部分理解</span></li>
																    <li>
																     <input type="radio" name="feedbackGrade${a.count }" value="C" />
																     <span class="votechoicename">基本理解</span></li>
																    <li>
																     <input type="radio" name="feedbackGrade${a.count }" value="D" />
																     <span class="votechoicename">不太理解</span></li>
																    <li>
																     <input type="radio" name="feedbackGrade${a.count }" value="E" />
																     <span class="votechoicename">完全不懂</span></li>
																   </ul><hr class="simple" color="#6f5499" /></c:forEach></div>
																   
																 
																   <div style="font-size: 15.5px;margin-left: 18px"> 
																 <input type="radio" name="niming" value="1" id="myradio"/>匿名评论<br>
																 <textarea  name="comment" rows="5" cols="60"  placeholder="请写出不理解的内容"></textarea></div>
																			
																 <div class="form-group">
																			<div class="col-sm-7 text-right">
																				<button type="submit" class="btn btn-primary" style="width: 120px" id="commit">提交</button>
																			</div>
																		</div>
																</form>
															</div>
														</div>
													</div>
												</div>
											</div>
					</div><!-- /.page-content -->
					<!-- 内容页 end -->
				</div>
			</div><!-- /.main-content -->
			<!--- 内容主体 end -->

			
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="${pageContext.request.contextPath}/lib/ace-admin/js/jquery-2.1.4.min.js"></script>

		<!-- <![endif]-->

		<!--[if IE]>
<script src="${pageContext.request.contextPath}/lib/ace-admin/js/jquery-1.11.3.min.js"></script>
<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='${pageContext.request.contextPath}/lib/ace-admin/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="${pageContext.request.contextPath}/lib/ace-admin/js/bootstrap.min.js"></script>

		<!-- page specific plugin scripts -->

		<!-- ace scripts -->
		<script src="${pageContext.request.contextPath}/lib/ace-admin/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/lib/ace-admin/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
		$(function(){
		    /*鼠标点击下去的时候，决定是否选中*/
		    $("#myradio").bind("mousedown",function(event){
		         var radioChecked = $(this).prop("checked");
		         $(this).prop('checked', !radioChecked);
		        return false;
		    });

		    /*阻止click事件默认行为*/
		    $("#myradio").click(function(event){
		        return false;
		    });
		})
		/* $(document).ready(function() { 
			var options = {
     			   success: showResponse,     //提交后的回调函数
     			   type: "POST",               //默认是form的method（get or post），如果申明，则会覆盖
     			   timeout: 3000               //限制请求的时间，当请求大于3秒后，跳出请求
     			}
		    $("#myForm").ajaxForm(options);
	    	function showResponse(responseText, statusText){
	    		 alert("1");
	    	}
		}) */
	</script>
	</body>
</html>