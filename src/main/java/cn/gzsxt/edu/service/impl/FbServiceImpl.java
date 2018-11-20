package cn.gzsxt.edu.service.impl;

import java.sql.Date;
import java.text.DateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import cn.gzsxt.edu.mapper.AdminMapper;
import cn.gzsxt.edu.mapper.ChapterMapper;
import cn.gzsxt.edu.mapper.ClassMapper;

import com.mysql.fabric.xmlrpc.base.Data;


import cn.gzsxt.edu.mapper.FbMapper;
import cn.gzsxt.edu.mapper.StageMapper;
import cn.gzsxt.edu.service.FbService;
import cn.gzsxt.edu.utils.Global;
import cn.gzsxt.edu.utils.Page;

@Service
public class FbServiceImpl implements FbService {
	
	private static final Logger logger=LogManager.getLogger(FbServiceImpl.class);
	
	@Autowired
	private FbMapper fbMapper;
	@Autowired
	private StageMapper stageMapper;
	@Autowired
	private  AdminMapper  adminMapper;
	@Autowired
	private  ChapterMapper chapterMapper;
	@Autowired
	private  ClassMapper classMapper;

	/**
	 * 跳转到反馈列表
	 */
	@Override
	public List<Map<String, Object>> findAllFb() {
		
		
		return fbMapper.findAll();
	}

	@Override
	public Page findFbToPage(int index,int size) {
		//limit 0,5;    开始位置等于下标*每页记录数   (规定每页记录数为5)
		int start=index*size;
		//查询总记录数   select count(*) from tb_edu_feedback
		int count=fbMapper.findCount();
		
		//查询分页数据    select * from tb_edu_feedback order by feedback_create_date desc limit #{start},#{size};
		List<Map<String, Object>> data=fbMapper.findByToPage(start, size);
	
		//select count(*) from tb_student_feedback where feedback_id=7 and student_feedback_date='2018-11-13'
		for(Map<String,Object> da:data){
			Long feedbackId=(Long) da.get("feedback_id");
			Date feedbackCreateDate=(Date) da.get("feedback_create_date");
		
			int number=fbMapper.submitNumber(feedbackId, feedbackCreateDate);
			da.put("number", number);
		}
		System.out.println(data+"OK");
		
		//将查询到的总记录数  还有当前索引需要的反馈记录信息  存到 Page
		Page page=new Page(index, size, count, data);
			
		// TODO Auto-generated method stub
		return page;
	}

	@Override
	public int deleteFbByIds(Object... ids) {
		return fbMapper.deleteByIds(ids);
	}

	
	@Override
	public Integer findVisitFbById(Long feedbackId) {
		return fbMapper.findVisit(feedbackId);
	}
	
	//将数据库字段 feedback_visit 该为0     发起状态
	@Override
	public int updateVisit(Map<String,Object> entity) {
		return fbMapper.updateVisitById(entity);
	}
	

	//将数据库字段 feedback_visit 该为1     关闭状态
	@Override
	public int updateCloseVisit(Map<String,Object> entity) {
		return fbMapper.updateCloseVisitById(entity);
	}



	@Override
	public List<Map<String, Object>> findChapters() {
		return chapterMapper.findAll();
	}


	@Transactional
	@Override
	public Map<String, Object> addFb(Map<String, Object> entity) {
		int count = fbMapper.insert(entity);
		if(count>0) {
			return entity;
		}
		return null;
	}

	@Override
	public Map<String, Object> toFbEdit(Long feedbackId) {
		Map<String, Object> map = fbMapper.findById(feedbackId);
		Object adminId = map.get("feedback_admin_id");
		Map<String, Object> map2 = adminMapper.findById(adminId);
		map.put("teacher", map2.get("admin_name"));
		Object classId = map.get("feedback_class_id");
		Object classStageId = map.get("class_stage_id");
		Map<String, Object> classStage = stageMapper.findByClassStageId(classStageId);
		Object stage_id = classStage.get("stage_id");
		Map<String, Object> stage = stageMapper.findStageAndSubjectByStageId(stage_id);
		map.put("classStage", stage);
		Object subject_id = stage.get("subject_id");
//		List<Map<String, Object>> chapters=chapterMapper.findBySubjectId(subject_id);
//		map.put("chapters", chapters);
		List<Map<String, Object>> list = chapterMapper.findAll();
		map.put("allChapters", list);
//		classMapper.findById(classId);
		return map;
	}

	@Override
	public int editFb(Map<String, Object> entity) {
		Object feedback_class_id = entity.get("feedback_class_id");
		Map<String, Object> map = classMapper.findById(feedback_class_id);
		entity.put("feedback_student_total", map.get("class_student_number"));
		Object stageId = entity.get("feedback_stage");
		Map<String, Object> map2 = stageMapper.findStageAndSubjectByStageId(stageId);
		String stage_name = map2.get("stage_name").toString();
		Object adminId = entity.get("feedback_admin_id");
		Map<String, Object> map3 = adminMapper.findById(adminId);
		entity.put("feedback_title", stage_name+"-每日反馈-"+map3.get("admin_name").toString());
		Map<String, Object> map4 = stageMapper.findStageClassId(entity);
		entity.put("class_stage_id", map4.get("class_stage_id"));
		int i=fbMapper.update(entity);
		return i;
	}


}
