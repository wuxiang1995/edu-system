<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../commons/header.jsp"></jsp:include>
</head>

<body class="no-skin">
	<!-- 导航栏 start		-->
	<jsp:include page="../commons/navbar.jsp"></jsp:include>
	<!-- 导航栏 end -->

	<div class="main-container ace-save-state" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.loadState('main-container')
			} catch (e) {
			}
		</script>
		<!--- 左边栏-菜单 start -->
		<jsp:include page="../commons/sidebar.jsp"></jsp:include>
		<!--- 左边栏-菜单 end -->

		<!--- 内容主体 start -->
		<div class="main-content">
			<div class="main-content-inner">
				<!-- 向导栏 start-->
				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
					<ul class="breadcrumb">
						<li>
							<i class="ace-icon fa fa-home home-icon"></i>
							<a href="${pageContext.request.contextPath }/admin/toIndex.do">首页</a>
						</li>

						<li>
							<a href="${pageContext.request.contextPath }/evaluate/toEvaluate.do">每天评测管理</a>
						</li>
						<li class="active">每天评测-列表</li>
					</ul>
					<!-- /.breadcrumb -->

					<div class="nav-search" id="nav-search">
						<form class="form-search">
							<span class="input-icon"> <input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" /> <i
									class="ace-icon fa fa-search nav-search-icon"></i>
							</span>
						</form>
					</div>
					<!-- /.nav-search -->
				</div>
				<!-- 向导栏 end-->

				<!-- 内容页 start -->
				<div class="widget-box transparent" id="widget-box-13">

					<div class="widget-header">
						<h4 class="widget-title lighter">每天评测管理</h4>

						<div class="widget-toolbar no-border">
							<ul class="nav nav-tabs" id="myTab2">
								<li>
								</li>



								<li class="active">
									<a data-toggle="tab" href="#adminList">列表</a>
								</li>
							</ul>
						</div>
					</div>



					<div id="adminList" class="tab-pane in active">
						<button id="btnDeleteChkEvaluate" class="btn btn-primary" style="width: 150px">批量删除</button>
						<table class="table table-striped table-bordered align-center ">
							<tr class="bolder">
								<td><input id="chkAllEvaluate" type="checkbox"></td>
								<td>日期</td>
								<td>班别</td>
								<td>标题</td>
								<td>操作</td>
							</tr>

							<c:forEach var="data" items="${requestScope.page.data }">
								<tr>
									<td><input name="evaluateId" value="${data.evaluate_id }" type="checkbox"></td>
									<td>${data.evaluate_create_date }</td>
									<td>${data.class_name}</td>
									<td>${data.evaluate_title }</td>
									<td><a onclick="launch('${data.evaluate_id }')">发起[<span>${data.number}</span>/${data.class_student_number }]</a> <a onclick="closes('${data.evaluate_id }')">关闭</a><a
											href="${pageContext.request.contextPath }/evaluate/deleteEvaluate.do?evaluateId=${data.evaluate_id }&index=${requestScope.page.index }">删除</a> <input id="copy" type="hidden"
											value="http://192.168.7.187:8080/edu-system/studentfeedback/${data.evaluate_id }/student.do"> 
											<textarea style="opacity: 0; position: absolute; pointer-events: none;" cols="60" rows="2" id="biao${data.evaluate_id }">http://localhost:8080${pageContext.request.contextPath }/feedback/${data.evaluate_id }/toFeedbackListAll.do</textarea>
										<a onclick="copyUrl(${data.evaluate_id })">复制链接</a> 
										<a onclick="window.location.href='${pageContext.request.contextPath }/studentevaluate/toDetailsEvaluateList.do?evaluateId=${data.evaluate_id}'">详情</a></td>


								</tr>

							</c:forEach>

						</table>

						<div class="text-right">
							<nav>
								<ul class="pagination">
									<c:choose>
										<c:when test="${requestScope.page.previous}">
											<li>
												<a href="${pageContext.request.contextPath }/evaluate/toEvaluate.do?index=${requestScope.page.index-1 }"  aria-label="Previous">
																	       			 <span aria-hidden="true">&laquo;</span></a>
											</li>
										</c:when>
										<c:otherwise>
											<li class="disabled">
									            <a href="#" aria-label="Previous">
									       			 <span aria-hidden="true">&laquo;</span>
									      		 </a>
								      		 </li>
										</c:otherwise>
									</c:choose>

									<c:forEach varStatus="status" begin="1" end="${requestScope.page.pageNum }">
										<li>
											<a href="${pageContext.request.contextPath }/evaluate/toEvaluate.do?index=${status.count-1 }">${status.count }</a>
										</li>
									</c:forEach>

									<c:choose>
										<c:when test="${requestScope.page.next}">
											<li>
												<a href="${pageContext.request.contextPath }/evaluate/toEvaluate.do?index=${requestScope.page.index+1 }" aria-label="Next">
																	        	<span aria-hidden="true">&raquo;</span></a>
											</li>
										</c:when>
										<c:otherwise>
											<li class="disabled">
									      		<a href="#" aria-label="Next">
									        	<span aria-hidden="true">&raquo;</span>
									      		</a>
									    	</li>
										</c:otherwise>


									</c:choose>


								</ul>
							</nav>
						</div>

					</div>

				</div>



				<!-- 内容页 end -->
			</div>
		</div>
		<!-- /.main-content -->
		<!--- 内容主体 end -->

		<!--页尾 start -->
		<jsp:include page="../commons/footer.jsp"></jsp:include>

		<!--页尾 end -->
	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->

	<!--[if !IE]> -->
	<script src="${pageContext.request.contextPath}/lib/ace-admin/js/jquery-2.1.4.min.js"></script>

	<!-- <![endif]-->

	<!--[if IE]>
<script src="${pageContext.request.contextPath}/lib/ace-admin/js/jquery-1.11.3.min.js"></script>
<![endif]-->
	<script type="text/javascript">
		if ('ontouchstart' in document.documentElement)
			document
					.write("<script src='${pageContext.request.contextPath}/lib/ace-admin/js/jquery.mobile.custom.min.js'>"
							+ "<"+"/script>");
	</script>
	<script src="${pageContext.request.contextPath}/lib/ace-admin/js/bootstrap.min.js"></script>

	<!-- page specific plugin scripts -->

	<!-- ace scripts -->
	<script src="${pageContext.request.contextPath}/lib/ace-admin/js/ace-elements.min.js"></script>
	<script src="${pageContext.request.contextPath}/lib/ace-admin/js/ace.min.js"></script>

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		$("#chkAllEvaluate").click(function() {
			if ($(this).prop("checked") == true) {
				$("input[name='evaluateId']").prop("checked", true);
			} else {
				$("input[name='evaluateId']").prop("checked", false);
			}

		})

		$("#btnDeleteChkEvaluate")
				.click(
						function() {

							var chkEvaluates = $("input[name='evaluateId']:checked");

							var params = chkEvaluates.serialize();

							window.location.href = "${pageContext.request.contextPath }/evaluate/deleteEvaluates.do?index=${requestScope.page.index }&"
									+ params;
						})

		function launch(evaluateId) {
			$
					.ajax({
						url : "${pageContext.request.contextPath }/evaluate/evaluateLaunch.do?evaluateId="
								+ evaluateId,
						success : function(rsData) {
							alert("发起成功！！！");
						}
					})
		}

		function closes(evaluateId) {

			$.ajax({
				url : "${pageContext.request.contextPath }/evaluate/evaluateClose.do?evaluateId="
						+ evaluateId,
				success : function(rsData) {
					alert("关闭成功！！！");
				}
			})
		}
		
	</script>

	<script type="text/javascript">
		function copyUrl(a) {
			var Url = document.getElementById("biao"+a);
			Url.select();     // 选择对象
			document.execCommand("Copy"); // 执行浏览器复制命令
			alert("复制链接成功!!!");
		}
		
		 /* setInterval(function(){
			$.ajax({
				url:"${pageContext.request.contextPath}/evaluate/evaluateAsynchronous.do",
				success:function(rsData){
					
				}
			})
			
		},3000);  */
	</script>



















</body>
</html>
