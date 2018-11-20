<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
	<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>后台系统-后台用户管理-列表</title>
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
<script src="${pageContext.request.contextPath}/lib/ace-admin/js/jquery-2.1.4.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/ace-admin/js/ace-extra.min.js"></script>
<style type="text/css">
li{
    list-style: none;margin-left: 40px;margin-top: 5px} 
ul{clear: both;font-size: 15.5px;}
</style>
<script type="text/javascript">


</script>
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
												<div id="evaluateTitle" class="widget-header">
														<h4 class='widget-title lighter'>${requestScope.evaluate.evaluate_title}</h4>
									
												</div>
												<!-- 标题ending -->
												<div class="widget-body">
													<div class="widget-main padding-12 no-padding-left no-padding-right">
														<div class="tab-content padding-4" >
															<div  class="tab-pane in active" >
															   <%-- ${requestScope.student_name_age } --%>
															   <form action="${pageContext.request.contextPath }/feedback/feedbackFrom.do" method="post" class="form-horizontal" role="form">
																	<h4 class="red" id="id-company-text">${requestScope.student_name_age}</h4>
																	<input name="evaluate_id" type="hidden" value="${requestScope.evaluate.evaluate_id}">
																	<div style="font-size: 15.5px;margin-left: 18px"> 
																		学生名：<input  type="text" name="student_name" />
																	</div>
																	<br/><br/>
																	<table class="table" id="field" >
																	
																		<c:forEach var="field" items="${requestScope.field}">
																			<tr>
																				<td><h5> ${field.field_id}</h5> </td>
																				<td colspan='5'><h5>${field.field_name}</h5></td>	
																			</tr>
																			<tr>
																				<td></td>
																				<c:forEach var="dictionary" items="${requestScope.dictionary}">
																					<td ><input name="dic_value${field.field_id}" type='checkbox' value="${dictionary.dic_value}"/>${dictionary.dic_name}</td>	
																				</c:forEach>
																			</tr>
																		</c:forEach>
																	
																    </table>
																    <br/><br/>
																    
																    <div style="font-size: 15.5px;margin-left: 18px"> 
																		<input name="student_evaluate_show" type="checkbox" value="1">匿名提交
																	</div>
																	
																	<div>
																		<textarea  name="student_evaluate_comment" class="autosize-transition form-control" style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 112px;" ></textarea>
																	</div>
																    <br/><br/>
																	<div class="col-sm-7 text-right">
																		<button type="submit" class="btn btn-primary" style="width: 120px">提交</button>
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
		/* $(function(){
		    /*鼠标点击下去的时候，决定是否选中*/
		  /*   $("#myradio").bind("mousedown",function(event){
		         var radioChecked = $(this).prop("checked");
		         $(this).prop('checked', !radioChecked);
		        return false;
		    }); */

		    /*阻止click事件默认行为*/
		  /*   $("#myradio").click(function(event){
		        return false;
		    });

			})
		}) */
		
	/* 	$(function() {
			$("#feedbackId").click(function() {
					var feedback=$(this).val();
					console.log(feedback);
				
			})
		}) */ 
		</script>
	</body>
</html>
