package cn.gzsxt.edu.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.edu.service.AdminService;
import cn.gzsxt.edu.service.ChapterService;
import cn.gzsxt.edu.service.ClassService;
import cn.gzsxt.edu.service.ClassStageService;
import cn.gzsxt.edu.service.DictionaryService;
import cn.gzsxt.edu.service.StageService;
import cn.gzsxt.edu.service.SubjectService;
import cn.gzsxt.edu.utils.Global;
import cn.gzsxt.edu.utils.ListAllSZ;
import cn.gzsxt.edu.utils.Page;

@Controller
@Scope(value="request")
@RequestMapping(value="/class")
public class ClassController {
	private static final Logger logger =LogManager.getLogger(AdminController.class);
	
	@Autowired
	private ClassService classService;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private ClassStageService classStageService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private StageService stageSevice;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private  ChapterService  ChapterService;
	
	@RequestMapping(value="toClassList")
	public String toClassList(@RequestParam Map<String, Object> entity,Integer index,HttpServletRequest request) {
		try {
			if(index==null) {
				index=0;
			}
			Page page= classService.findClassToPage(entity, index, Global.PAGE_SIZE);
			request.setAttribute("page", page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "manager/classList";
		
	}
	@RequestMapping(value="toClassAdd")
	public String toClassAdd(HttpServletRequest request) {
		List<Map<String, Object>> status = dictionaryService.findDictionaryByTypeCode(1004);
		
		request.setAttribute("statuses", status);
		return "manager/classAdd";
		
	}
	@RequestMapping(value="addClass")
	public String addClass(@RequestParam Map<String, Object> entity,HttpServletRequest request,HttpSession session) {
		
		try {
			Map<String, Object> classs=classService.addClass(entity);
			if(classs==null) {
				request.setAttribute("class_add_msg", "添加失败");
			}
			request.setAttribute("class_add_msg", "添加成功");
			List<Map<String, Object>> status = dictionaryService.findDictionaryByTypeCode(1004);
			request.setAttribute("statuses", status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "manager/classAdd";
		
	}
	@RequestMapping(value="deleteClass")
	public String deleteClass(Long classId,HttpServletRequest request) {
		classService.deleteClassByIds(classId);
		return this.toClassList(null, 0, request);
	}
	
	@RequestMapping(value="deleteClasss")
	public String deleteClasss(Long[] classId,HttpServletRequest request) {
		classService.deleteClassByIds((Object[])classId);
		return this.toClassList(null, 0, request);
	}
	
	/**
	 * 跳转到班级页面 同时通过班级ID查找班级信息
	 * @param classId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="toClassEdit")
	public String toClassEdit(Long classId, HttpServletRequest request) {
		try {
			Map<String, Object> classs= classService.findClassById(classId);
			request.setAttribute("classs", classs);
			List<Map<String, Object>> status = dictionaryService.findDictionaryByTypeCode(1004);
			request.setAttribute("statuses", status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "manager/classEdit";
		
	}
	
	@RequestMapping(value="editClass")
	public String editClass(@RequestParam Map<String, Object> entity,HttpServletRequest request) {
		try {
			Map<String, Object> classs=classService.editClass(entity);
			if(classs==null) {
				request.setAttribute("class_edit_msg", "更新失败！");
			}else {
				request.setAttribute("class_edit_msg", "更新成功！");
			}
			List<Map<String, Object>> status = dictionaryService.findDictionaryByTypeCode(1004);
			request.setAttribute("statuses", status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "manager/classEdit";
		
	}
	/**
	 * /class/selectClass.do
	 * @param classId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="selectClass")
	public ListAllSZ[] selectClass(HttpServletRequest request) {
		Object classId=request.getParameter("classId");
		logger.debug("IDaaaaaaa:"+classId);
		List<Map<String, Object>> calssStageAll=classStageService.calssIdAll(classId);
		Object [] classStageId =new Object[calssStageAll.size()];
		Object [] stageIds=new Object[calssStageAll.size()];
		Object subjectId=calssStageAll.get(0).get("subject_id");
		for (int i=0; i<calssStageAll.size();i++) {
		Map<String, Object> classMap=calssStageAll.get(i);
			
			
			classStageId[i]= classMap.get("class_stage_teacher");
			logger.debug(classMap.get("stage_id"));
			stageIds[i]=classMap.get("stage_id");
		}
		List<Map<String, Object>> admin=adminService.selectById(classStageId);
		List<Map<String, Object>> stage=stageSevice.selectById(stageIds);
		Map<String, Object> subjectMap=subjectService.findSubjectById(subjectId);
		List<Map<String, Object>>findChapterById = ChapterService.findByChapterId(subjectMap.get("subject_id"));
		//subjectMap.put("dates", new Date().toString());
		
				
		List<Map<String, Object>> subject=new ArrayList<>();
		subject.add(subjectMap);
		
		ListAllSZ [] data=new ListAllSZ[4];
		data[0]=new ListAllSZ(admin);
		data[1]=new ListAllSZ(stage);
		data[2]=new ListAllSZ(subject);
		data[3]=new ListAllSZ(findChapterById);
		
		
		return data;
		
	}

}
