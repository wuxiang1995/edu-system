<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a>
						</li>

						<li><a href="#">每日反馈管理</a></li>
						<li class="active">每日反馈-编辑</li>
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
							<h4 class="widget-title lighter">每日反馈管理-编辑</h4>

							<div class="widget-toolbar no-border">
								<ul class="nav nav-tabs" id="myTab2">
									<li class="active"><a data-toggle="tab" href="#fbEdit">编辑</a>
									</li>



									<li><a data-toggle="tab" href="javascript:void(0)"
										onclick="window.location.href='${pageContext.request.contextPath }/fb/toFbList.do'">列表</a>
									</li>
								</ul>
							</div>
						</div>

						<div class="widget-body">
							<div
								class="widget-main padding-12 no-padding-left no-padding-right">
								<div class="tab-content padding-4">

									<div id="roleAdd" class="tab-pane in active ">
										<div class="scrollable-horizontal" data-size="800">
											${requestScope.fb_edit_msg }
											<form
												action="${pageContext.request.contextPath }/fb/editFb.do"
												method="post" class="form-horizontal" role="form">
												<input name="token" type="hidden"
													value="${sessionScope.token }"> 
												<input name="token.invoke" type="hidden" value="/fb/toFbList.do">
												<input name="feedback_id" type="hidden" value="${requestScope.fb.feedback_id }">
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 班级: </label>
													<div class="col-sm-9">
													<select name="feedback_class_id" >
													<c:forEach var="classlists" items="${requestScope.stages.classlist}">
													<c:choose>
													<c:when test="${requestScope.fb.feedback_class_id==classlists.class_id}">
												     <option value="${classlists.class_id}" selected="selected">${classlists.class_name}</option>
												     </c:when>
												     <c:otherwise>
												     <option value="${classlists.class_id}">${classlists.class_name}</option>
												     </c:otherwise>
												     </c:choose>
													</c:forEach>
													</select>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right"
														for="form-field-1"> 反馈创建时间: </label>

													<div class="col-sm-8" >
														<input name="feedback_create_date" value="${requestScope.fb.feedback_create_date}" type="date" "/> 
													
													</div>
													</div>
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right"
														for="form-field-1"> 反馈标题: </label>

													<div class="col-sm-8" id="newDate">
													 
													<select name="feedback_stage" id="stageAll">
													<%-- ${requestScope.stages.fbdate} --%>
													<c:forEach var="stageName" items="${requestScope.stages.stage}">
													<c:choose>
													<c:when test="${requestScope.fb.classStage.stage_name==stageName.stage_name  }">
												     <option value="${stageName.stage_id}" selected="selected">${stageName.stage_name}-每天反馈-</option>
												     </c:when>
												     <c:otherwise>
												     <option value="${stageName.stage_id}">${stageName.stage_name}-每天反馈-</option>
												     </c:otherwise>
												     </c:choose>
													</c:forEach>
													</select>
													<select name="feedback_admin_id" id="stageAll">
													<c:forEach var="admin" items="${requestScope.stages.admins}">
													<c:choose>
													<c:when test="${requestScope.fb.teacher==admin.admin_name }">
												     <option value="${admin.admin_id }"   selected="selected">${admin.admin_name }</option>
												     </c:when>
												     <c:otherwise>
												     <option value="${admin.admin_id }" >${admin.admin_name }</option>
												     </c:otherwise>
												     </c:choose> 
													</c:forEach>
													</select>
													</div>
													
													
													
												</div>
												
											
												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right"
														for="form-field-1"> 反馈课程: </label>

													<div class="col-sm-3">
													<input name="class_stage_id" value="${requestScope.fb.classStage.subject_name}"  type="text" disabled="disabled""/> 
													</div>
												</div>

												<div class="form-group">
													<label class="col-sm-3 control-label no-padding-right"
														for="form-field-1"> 全部课程内容 <input id="chkAllChapters"
														type="checkbox" /></label>

												</div> 
													<div class="form-group" id="chapter">
												
																<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> ${subject.subject_name }
														 
																</label>
																<c:set var="count" value="0">
																</c:set>
																<div class="col-sm-8">
																<table class="table"><tr>
																		<c:forEach var="allchapter" items="${requestScope.fb.allChapters }">
																	    <c:set var="flag" value="false"></c:set>
																		<c:forTokens var="chapters" items="${requestScope.fb.feedback_chapters}" delims=",">
																			      	             <c:if test="${fn:trim(chapters)==allchapter.chapter_id }">
																			      	                 <c:set var="flag" value="true"></c:set>
																			      	                
																			      	             </c:if>
																			      	       </c:forTokens>
																			<c:if test="${count %4==0 }">
																				<tr>
																			</c:if>
																			<c:choose>
																			<c:when test="${flag==true}">
																				<td><input name="feedbackChapter"
																					value="${allchapter.chapter_id }" type="checkbox" checked="checked"/>${ allchapter.chapter_title}</td>
																			</c:when>
																			<c:otherwise>
																			<td><input name="feedbackChapter"
																					value="${allchapter.chapter_id }" type="checkbox" />${ allchapter.chapter_title}</td>
																			</c:otherwise>
																			</c:choose>
																				
																				 <c:set var="count" value="${count+1}"></c:set>
		
																			<c:if test="${count%4==0 }">
																			<c:set var="count" value="0"></c:set>
																				</tr>
																			</c:if>
																		<%-- </c:forEach> --%>
																		</c:forEach>
		
																	</table>
																	</div>
		
		 														
																
													</div>
												<div class="form-group">

													<div class="col-sm-7 text-right">
														<button type="submit" class="btn btn-primary">增加每天反馈</button>
													</div>
												</div>
											</form>

										</div>
									</div>


								</div>
							</div>
						</div>
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
			if('ontouchstart' in document.documentElement) document.write("<script src='${pageContext.request.contextPath}/lib/ace-admin/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
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
	<script type="text/javascript">
		
			   //1.实现全选以及返回所有的权限
			   $("#chkAllChapters").click(function(){
				   if($(this).prop("checked")==true){
					   $("input[type='checkbox']").prop("checked",true);
				   }else{
					   $("input[type='checkbox']").prop("checked",false);
				   }
			   });
			   
			   //2.勾选模块，仅仅只选中模块的权限。
			   var chkSubjectChapter=function(obj){
				  // alert("-test-"+$(obj));
				   //ojb是一个Document对象，如果转成jQuery对象,$(obj);
				  var parentDiv=  $(obj).parents(".form-group");
				 
				  var chkObject=parentDiv.find("input[type='checkbox']");
				  if($(obj).prop("checked")==true){
					  chkObject.prop("checked",true);
				  }else{
					  chkObject.prop("checked",false);
				  }
				  
			   }
			<%-- $("#classE").click(function() {
				var classId=$(this).val();
				$("#adminAll").empty();
				$("#stageAll").empty();
				$("#subjectAll").empty();
				$("#chapter").html("");
				//$("#chapterDiv").html("");
				$.ajax({
					url:"${pageContext.request.contextPath }/class/selectClass.do?classId="+classId,
					type:"post",
					dataType:"json",
					success: function (data) {
						data1=data[0].data;
						data2=data[1].data;
						data3=data[2].data;
						data4=data[3].data;
						
						var admin="";
						for(var i=0;i<data1.length;i++ ){
							admin+="<option value='"+data1[i].admin_id +"'>"+data1[i].admin_name+"</option>"
						}
						$("#adminAll").append(admin);
						 var stage="";
						for(var i=0;i<data2.length;i++ ){
							stage+="<option value='"+data2[i].stage_id +"'>"+data2[i].stage_name+"</option>"
						}
						$("#stageAll").append(stage);
						//var dataInput="";
						//for(var i=0;i<data3.length;i++ ){
							//	dataInput+="<input type='text' name='dateFb' value='"+data3[i].dates+"'>"
						//}
						//$("dateInput").append(dataInput);
						 var subject="";
						 var chapterHtml="";
						 var num=0;
						for(var i=0;i<data3.length;i++ ){
							subject+="<option value='"+data3[i].subject_name +"'>"+data3[i].subject_name+"</option>"
							$("#chapter").append("<label class='col-sm-3 control-label no-padding-right' for='form-field-1'> "+data3[i].subject_name+" <input onclick='chkSubjectChapter(this)' type='checkbox' /></label>");
							 
							for(var i=0;i<data4.length;i++){
								num++;
								if(num%5==0){
									chapterHtml="<tr>"+chapterHtml+"<tr>"
								}
								chapterHtml+="<td>"+data4[i].chapter_title+"("+data4[i].chapter_summary+")<input name='subjectChapter' value='"+data4[i].chapter_title+"("+data4[i].chapter_summary+") ' type='checkbox' /></td>"
								
								
							}
							
							$("#chapter").append(" <div class='col-sm-8' ><table class='table'>"+chapterHtml+"</table></div>");
						}
						$("#subjectAll").append(subject);
						$("#chapter").append("<label class='col-sm-3 control-label no-padding-right' for='form-field-1'> "+subjectName+"${subject.subject_name }<input onclick='chkSubjectChapter(this)' type='checkbox' /></label>")


						
						<table class="table">

						每四条就换行
						思路：声明一个计数，从0开始，每次被4整除，增加一个 <tr/> <tr>
						
						<c:forEach var="chapter" items="${requestScope.stages.chapters }">
							<c:if test="${count%4==0 }">
								<tr>
							</c:if>
							<c:if test="${subject.subject_id==chapter.subject_id}">
								<td>${ chapter.chapter_title}<input name="subjectChapter"
									value="${chapter.chapter_id }" type="checkbox" /></td>
								<c:set var="count" value="${count+1}"></c:set>

							</c:if>
							<c:if test="${count%4==0 }">
								</tr>
							</c:if>
						</c:forEach>

					</table>
						
					}
					
			
					
					
					
				})
				 
					
			})--%>
		   
		</script>
</body>
</html>
