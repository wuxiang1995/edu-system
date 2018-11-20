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
								<a href="#">后台用户管理</a>
							</li>
							<li class="active">后台用户-列表</li>
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
													<h4 class="widget-title lighter">后台用户管理</h4>

													<div class="widget-toolbar no-border">
														<ul class="nav nav-tabs" id="myTab2">
															<li  class="active">
																<a data-toggle="tab" href="#adminAdd">增加</a>
															</li>

													

															<li>
																<a data-toggle="tab" href="javascript:void(0)" onclick="window.location.href='${pageContext.request.contextPath }/student/toStudentList.do'">列表</a>
															</li>
														</ul>
													</div>
												</div>

												<div class="widget-body">
													<div class="widget-main padding-12 no-padding-left no-padding-right">
														<div class="tab-content padding-4">
														
															<div id="adminAdd" class="tab-pane in active ">
																<div class="scrollable-horizontal" data-size="800">
																	 ${requestScope.student_edit_msg }
																	<form action="${pageContext.request.contextPath }/student/editStudent.do" method="post" class="form-horizontal" role="form">
																		<input name="student_id" type="hidden" value="${requestScope.student.student_id }">
																		<input name="token.invoke" type="hidden" value="/admin/toAdminList.do">
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 姓名 </label>
																			<div class="col-sm-9">
																				<input name="student_name" type="text" id="form-field-1" value="${requestScope.student.student_name }" class="col-xs-10 col-sm-5" />
																			</div>
																		</div>
																		
																		
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 性别 </label>
																			<div class="col-sm-9">
																			<c:choose>
																				<c:when test="${requestScope.student.student_sex =='M' }">
																					<label class="radio-inline">
																					  <input type="radio" name="student_sex" id="inlineRadio1" value="M" checked="checked"> 男
																					</label>
																					<label class="radio-inline">
																					  <input type="radio" name="student_sex" id="inlineRadio2" value="N"> 女
																					</label>
																				</c:when>
																				<c:otherwise>
																					<label class="radio-inline">
																						  <input type="radio" name="student_sex" id="inlineRadio1" value="M" > 男
																						</label>
																						<label class="radio-inline">
																						  <input type="radio" name="student_sex" id="inlineRadio2" value="N" checked="checked"> 女
																						</label>
																				
																				</c:otherwise>
																			
																			</c:choose>
																			
																			</div>
																		</div>
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 年龄 </label>
																			<div class="col-sm-9">
																				<input name="student_age" type="text" id="form-field-1" value="${requestScope.student.student_age }" class="col-xs-10 col-sm-5" />
																			</div>
																		</div>
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 电话 </label>
																			<div class="col-sm-9">
																				<input name="student_phone" type="text" id="form-field-1" value="${requestScope.student.student_phone }" class="col-xs-10 col-sm-5" />
																			</div>
																		</div>
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 班级 </label>
																			<div class="col-sm-9">
																				<select name="class_id" >
																				    <c:forEach var="data" items="${requestScope.data }">
																				       <option value="${data.class_id }">${data.class_name }</option>
																				    </c:forEach>
																				</select>
																			</div>
																		</div>

																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 用户状态 </label>
																			<div class="col-sm-9">
																				<select name="student_status" >
																				     <c:forEach var="status" items="${requestScope.statuses }">
																				       <option value="${status.dic_value }">${status.dic_name }</option>
																				    </c:forEach>
																				    
																				</select>
																			</div>
																		</div>
																		<div class="form-group">
																			<div class="col-sm-7 text-right">
																				<button type="submit" class="btn btn-primary">增加学生</button>
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