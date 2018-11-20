package cn.gzsxt.edu.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.gzsxt.edu.service.ClassService;
import cn.gzsxt.edu.service.DictionaryService;
import cn.gzsxt.edu.service.StudentService;
import cn.gzsxt.edu.utils.Global;
import cn.gzsxt.edu.utils.Page;

@Controller
@Scope(value="request")
@RequestMapping(value="/student")
public class StudentController {
	
	private static final Logger logger =LogManager.getLogger(AdminController.class);
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ClassService classService;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	
	@RequestMapping(value="toStudentList")
	public String studentList(@RequestParam Map<String,Object> entity, Integer index,HttpServletRequest request) {
		System.out.println("aaa");
			try {
				if(index==null) {
					index=0;
				}
				Page page=studentService.findStudentToPage(entity, index,Global.PAGE_SIZE );
				request.setAttribute("page", page);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return "manager/studentList";
		
	}
	
	
	@RequestMapping(value="toStudentAdd")
	public String toStudentAdd(HttpServletRequest request) {
		
		try {
			List<Map<String, Object>> data=classService.findAllClass();
			request.setAttribute("data", data);
			List<Map<String, Object>> status = dictionaryService.findDictionaryByTypeCode(1000);
			request.setAttribute("statuses", status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "manager/studentAdd";
		
	}
	
	@RequestMapping(value="addStudent")
	public String addStudent(@RequestParam Map<String, Object> entity,HttpServletRequest request) {
		try {
			Map<String, Object> student=studentService.addStudent(entity);
			if(student!=null) {
				request.setAttribute("student_add_msg", "添加成功！");
			}else {
				request.setAttribute("student_add_msg", "添加失败！");
			}
			List<Map<String, Object>> data=classService.findAllClass();
			request.setAttribute("data", data);
			List<Map<String, Object>> status = dictionaryService.findDictionaryByTypeCode(1000);
			request.setAttribute("statuses", status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "manager/studentAdd";
	}
	
	@RequestMapping(value="toStudentEdit")
	public String toStudentEdit(Long studentId,HttpServletRequest request) {
		try {
			Map<String, Object> student=studentService.findClassById(studentId);
			List<Map<String, Object>> data=classService.findAllClass();
			
			List<Map<String, Object>> status = dictionaryService.findDictionaryByTypeCode(1000);
			request.setAttribute("statuses", status);
			request.setAttribute("data", data);
			request.setAttribute("student",student );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "manager/studentEdit";
		
	}
	
	@RequestMapping(value="editStudent")
	public String editStudent(@RequestParam Map<String, Object> entity,HttpServletRequest request) {
		try {
			Map<String, Object> student=studentService.editStudent(entity);
			if(student!=null) {
				request.setAttribute("student_edit_msg", "编辑成功！");
			}else {
				request.setAttribute("student_edit_msg", "编辑失败！");
			}
			List<Map<String, Object>> data=classService.findAllClass();
			request.setAttribute("data", data);
			List<Map<String, Object>> status = dictionaryService.findDictionaryByTypeCode(1000);
			request.setAttribute("statuses", status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "manager/studentEdit";
	}
	@RequestMapping(value="deleteStudent")
	public String deleteStudent(Long studentId,HttpServletRequest request) {
		studentService.deleteStudentByIds(studentId);
		return this.studentList(null, 0, request);
		
	}
	
	@RequestMapping(value="deleteStudents")
	public String deleteStudents(Long[] studentId,HttpServletRequest request) {
		studentService.deleteStudentByIds((Object[])studentId);
		return this.studentList(null, 0, request);
		
	}

	
}
