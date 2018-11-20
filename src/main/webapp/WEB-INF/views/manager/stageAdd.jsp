<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				try{ace.settings.loadState('main-container')}catch(e){}
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
								<a href="#">首页</a>
							</li>

							<li>
								<a href="#">教务人员管理</a>
							</li>
							<li class="active">阶段管理-增加</li>
						</ul><!-- /.breadcrumb -->

						<div class="nav-search" id="nav-search">
							<form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="ace-icon fa fa-search nav-search-icon"></i>
								</span>
							</form>
						</div><!-- /.nav-search -->
					</div>
					<!-- 向导栏 end-->

					<!-- 内容页 start -->
					<div class="page-content">
				          			<div class="widget-box transparent" id="widget-box-13">
												<div class="widget-header">
													<h4 class="widget-title lighter">阶段管理</h4>

													<div class="widget-toolbar no-border">
														<ul class="nav nav-tabs" id="myTab2">
															<li  class="active">
																<a data-toggle="tab" href="#adminEdit">增加</a>
															</li>

													

															<li>
																<a data-toggle="tab" href="javascript:void(0)" onclick="window.location.href='${pageContext.request.contextPath }/stage/toStageList.do'">列表</a>
															</li>
														</ul>
													</div>
												</div>

												<div class="widget-body">
													<div class="widget-main padding-12 no-padding-left no-padding-right">
														<div class="tab-content padding-4">
														
															<div id="adminEdit" class="tab-pane in active ">
																<div class="scrollable-horizontal" data-size="800">
																    ${requestScope.stage_add_msg }
																	<form action="${pageContext.request.contextPath }/stage/addStage.do" method="post" class="form-horizontal" role="form">
																		<input name="token" type="hidden" value="${sessionScope.token }">
																		<input name="token.invoke" type="hidden" value="/stage/toStageList.do">
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1">班级名称 </label>
																			<div class="col-sm-9">
																			<select name="class_id">
																				   <c:forEach var="classlists" items="${requestScope.stages.classlist }">
																				     <option value="${classlists.class_id }">${classlists.class_name }</option>
																				   </c:forEach>
																				</select>
																				</div>
																		</div>
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1">阶段名称 </label>
																			<div class="col-sm-5">
																			<input type="text" list="itemlist" name="stage_name" autocomplete =off><span style="color:red;font-size: 15px">&nbsp;&nbsp;*必填项</span>
																				<datalist id="itemlist">
																				<c:forEach var="stageName" items="${requestScope.stages.stage }">
																				    <option>${stageName.stage_name}</option>
																				  </c:forEach>
																				</datalist>  
																				<!--<input name="stage_name"  type="text"  class="col-xs-10 col-sm-5" />  -->
																			</div>
																		</div>
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1">班级阶段时间 </label>
																			<div class="col-sm-5">
																				<input name="class_stage_date"  type="date"  class="col-xs-10 col-sm-5" />
																			</div>
																		</div>
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1">教师 </label>

																	<div class="col-sm-9">
																			<select name="class_stage_teacher">
																				   <c:forEach var="admin" items="${requestScope.stages.admins }">
																				     <option value="${admin.admin_id }">${admin.admin_name }</option>
																				   </c:forEach>
																				</select>
																				</div>
																		</div>
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1">课程类型 </label>
																				<div class="col-sm-9">
																			<select name="subject_id">
																				   <c:forEach var="subject" items="${requestScope.stages.subjects }">
																				     <option value="${subject.subject_id }">${subject.subject_name }</option>
																				   </c:forEach>
																				</select>
																				</div>
																		</div>
														

																		
																		<div class="form-group">
																			
																			<div class="col-sm-5 text-right">
																				<button type="submit" class="btn btn-primary">增加阶段</button>
																			</div>
																		</div>
																	</form>

																</div>
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

			 <!--页尾 start -->
	 <jsp:include page="../commons/footer.jsp"></jsp:include>

		
			 <!--页尾 end -->
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
	</body>
</html>
