package cn.gzsxt.edu.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.edu.annotation.TokenForm;
import cn.gzsxt.edu.service.StageService;
import cn.gzsxt.edu.utils.Global;
import cn.gzsxt.edu.utils.Page;

@Controller
@Scope(value="request")
@RequestMapping(value="/stage")
public class ClassStageController {
	private static final Logger logger =LogManager.getLogger(ClassStageController.class);
	@Autowired
	private StageService stageService;
	/**
	 * 列出所有的阶段信息
	 * @param condition
	 * @param index
	 * @param request
	 * @return
	 */
	@RequestMapping("/toStageList")
	public String toStageList(@RequestParam Map<String, Object> condition,Integer index,HttpServletRequest request) {
		logger.debug("-跳转到阶段列表-"+condition+"，索引："+index);
		try {
			if (index==null) {
				index=0;
			}
			Page page = stageService.findStageToPage(condition, index, Global.PAGE_SIZE);
			request.setAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/stageList";
	}
	/**
	 * 根据class——stage-id删除班级阶段记录
	 * @param stageId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/deleteClassStage")
	public String deletestage(Long stageId,HttpServletRequest request) {
		logger.debug("-删除班级阶段:"+stageId);
		stageService.deleteStage(stageId);
		return this.toStageList(null, 0, request);
	}
	/**
	 * 批量删除班级阶段记录
	 * @param stageId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/deleteClassStages")
	public String deletestages(Long[] stageId,HttpServletRequest request) {
		logger.debug("-批量删除阶段:"+stageId);
		stageService.deleteStage((Object[]) stageId);
		return this.toStageList(null, 0, request);
	}
	/**
	 * 跳转编辑阶段与班级阶段记录
	 * @param stageId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/toStageEdit")
	@TokenForm(create=true)
	public String toStageEdit(Long stageId,HttpServletRequest request ) {
		logger.debug("跳转到编辑阶段页面--"+stageId);
		//通过阶段编号查询阶段的信息
		try {
			Map<String, Object> stage = stageService.toStageEdit(stageId);
			request.setAttribute("stage", stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/stageEdit";
	}
	/**
	 * 编辑阶段与班级阶段记录
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/editStage")
	@TokenForm(remove=true)
	public String editStage(@RequestParam Map<String, Object> entity,HttpServletRequest request ) {
		logger.debug("-编辑阶段-"+entity);
		try {
			Map<String, Object> stage = stageService.editStage(entity);
			if (stage!=null) {
				request.setAttribute("stage_edit_msg", "更新阶段成功");
				//更新成功，重设表单值
				request.setAttribute("stage", stage);
			}else {
				request.setAttribute("stage_edit_msg", "更新阶段失败！！！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("stage_edit_msg", "更新阶段失败！！！");
		}
		Long classStageId=Long.valueOf((String)entity.get("class_stage_id"));
		return "forward:/stage/toStageEdit.do?stageId="+classStageId;
	}
	/**
	 * 跳转增加阶段与班级阶段记录
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/toStageAdd")
	@TokenForm(create=true)
	public String toStageAdd(HttpServletRequest request ) {
		logger.debug("跳转到增加阶段页面--");
		//通过阶段编号查询阶段的信息
		try {
			Map<String, Object> stages = stageService.toStageAdd();
			request.setAttribute("stages", stages);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager/stageAdd";
	}
	/**
	 * 增加阶段与班级阶段记录
	 * @param entity
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addStage")
	@TokenForm(remove=true)
	public String addStage(@RequestParam Map<String, Object> entity,HttpServletRequest request ) {
		logger.debug("-增加阶段-"+entity);
		try {
			if(entity.get("stage_name").equals("")||entity.get("stage_name")==null){
				request.setAttribute("stage_edit_msg", "阶段名称不能为空！！！");
			}
			int stage = stageService.addStage(entity);
			if (stage>0) {
				request.setAttribute("stage_add_msg", "增加阶段成功");
				return "forward:/stage/toStageAdd.do";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("stage_add_msg", "增加阶段失败！！！");
		return "forward:/stage/toStageAdd.do";
	}
	@RequestMapping(value="/selectStage")
    @ResponseBody
    public Map<String, Object> selectStageSubject(int mid){
        logger.debug(mid);
        Map<String, Object> stageSubjects=stageService.selectStageSubject(mid);
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("stageSubjects", stageSubjects);
        return stageSubjects;
    }

}
