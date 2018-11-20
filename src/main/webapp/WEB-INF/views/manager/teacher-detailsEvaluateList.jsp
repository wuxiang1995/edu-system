<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a>
						</li>

						<li><a href="#">教师模块</a></li>
						<li class="active">教学评测</li>
					</ul>
					<!-- /.breadcrumb -->

					<div class="nav-search" id="nav-search">
						<form class="form-search">
							<span class="input-icon"> <input type="text"
								placeholder="Search ..." class="nav-search-input"
								id="nav-search-input" autocomplete="off" /> <i
								class="ace-icon fa fa-search nav-search-icon"></i>
							</span>
						</form>
					</div>
					<!-- /.nav-search -->
				</div>
				<!-- 向导栏 end-->

				<!-- 内容页 start -->
				<div class="page-content">
					<div class="widget-box transparent" id="widget-box-13">
						<div class="widget-header">
							<h4 class="widget-title lighter">${requestScope.page.data[0].evaluate.evaluate_title}</h4>

							<div class="widget-toolbar no-border">
								<ul class="nav nav-tabs" id="myTab2">
									<li ><a data-toggle="tab"
										href="javascript:void(0)" onclick="window.location.href='${pageContext.request.contextPath }/evaluate/toEvaluate.do'">返回</a></li>
									<li class="active"><a data-toggle="tab"
										href="#evaluateList">列表</a></li>
								</ul>
							</div>
						</div>

						<div class="widget-body">
							<div
								class="widget-main padding-12 no-padding-left no-padding-right">
								<div class="tab-content padding-4">



									<div id="studentEvaluateList" class="tab-pane in active">
										<div class="col-xs-12">
											<table id="simple-table"
												class="table  table-bordered table-hover">
												<thead>
													<tr>
														<%-- <c:forEach items="${requestScope.page.data[0]}" var="studentEvaluate">
															<c:forTokens items="${studentEvaluate.evaluate.evaluate_chapters }" delims="," var="chapter">
															<th class="hidden-480">${chapter}</th>
															</c:forTokens>
													</c:forEach>  --%>
														<%-- <th class="hidden-480">${requestScope.page.data[0].evaluate.evaluate_chapters}</th> --%>
														<th class="hidden-480">学生姓名</th>
														<%-- <c:forTokens var="chapter"
															items="${requestScope.page.data[0].evaluate.field_id}"
															delims=",">
															<th class="hidden-480">${chapter}</th>
														</c:forTokens>  --%>
														<c:forEach items="${requestScope.page.data[0].field}"
															var="fields">
															<th class="hidden-480">${fields.field_name}</th>
														</c:forEach>
														<%-- <th>${requestScope.page.data[0].field[0].field_name}</th>  --%>
													</tr>
												</thead>

												<tbody>
													<tr>
														<c:forEach var="studentevaluate"
															items="${requestScope.page.data }">
															<tr>
																<td>${studentevaluate.student.student_name}</td>
																<c:forEach items="${requestScope.page.data[0].field}"
																	var="fields" varStatus="status">
																	<th>${fields.field_percent*100*studentevaluate.dicList[status.count-1].dic_percent}</th>
																</c:forEach>
															</tr>
														</c:forEach>
													</tr>
												</tbody>
											</table>





										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="col-xs-9">
					<div style="margin-left: 19px">
						<c:if test="${fn:length(requestScope.page.data[0].field)>0 }">
						<c:forEach begin="0"
							end="${fn:length(requestScope.page.data[0].field)-1 }"
							varStatus="status">
							<c:set var="average" value="0"></c:set>

							<c:forEach var="studentevaluate"
								items="${requestScope.page.data }">
								<c:set var="average"
									value="${studentevaluate.field[status.count-1].field_percent*100*studentevaluate.dicList[status.count-1].dic_percent+average}"></c:set>
							</c:forEach>
								
							<c:set var="total" value="${total+average }"></c:set>
							<span><font size="3"> ${requestScope.page.data[0].field[status.count-1].field_name}:<fmt:formatNumber value="${average/requestScope.page.data[0].evaluate.evaluate_student_total}" type="currency" pattern="0.00"/></font></span>
							<br />

						</c:forEach>
						</c:if>
						<%-- ${(total)/requestScope.page.data[0].evaluate.evaluate_student_total} /(requestScope.page.data.length)--%>
						<span id=""><font size="3">得分:<fmt:formatNumber value="${(total)/requestScope.page.data[0].evaluate.evaluate_student_total}" type="currency" pattern="0.00"/>&nbsp;&nbsp;(满分100分)</font></span>
						<br />
					
					</div>
					<hr />
					<div style="margin-left: 19px">
						<c:forEach var="studentEvaluate"
							items="${requestScope.page.data }">
							<c:choose>
								<c:when test="${studentEvaluate.student_evaluate_show==0}">
									<span><font size="3">${studentEvaluate.student.student_name}:${studentEvaluate.student_evaluate_comment}</font></span>
									<br />
								</c:when>
								<c:otherwise>
									<span><font size="3">***:${studentEvaluate.student_evaluate_comment}</font></span>
									<br />
								</c:otherwise>
							</c:choose>
						</c:forEach>
					
					</div>
				</div>
				<!-- /.page-content -->
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
	<script
		src="${pageContext.request.contextPath}/lib/ace-admin/js/jquery-2.1.4.min.js"></script>

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
	<script
		src="${pageContext.request.contextPath}/lib/ace-admin/js/bootstrap.min.js"></script>

	<!-- page specific plugin scripts -->

	<!-- ace scripts -->
	<script
		src="${pageContext.request.contextPath}/lib/ace-admin/js/ace-elements.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/lib/ace-admin/js/ace.min.js"></script>

	<!-- inline scripts related to this page -->

</body>
</html>
