package cn.gzsxt.edu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gzsxt.edu.mapper.AdminMapper;
import cn.gzsxt.edu.mapper.ClassMapper;
import cn.gzsxt.edu.mapper.StageMapper;
import cn.gzsxt.edu.mapper.SubjectMapper;
import cn.gzsxt.edu.service.StageService;
import cn.gzsxt.edu.utils.Page;
@Service
public class StageServiceImpl implements StageService{
	private static final Logger logger =LogManager.getLogger(StageServiceImpl.class);
	@Autowired
	private StageMapper stageMapper;
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private ClassMapper classMapper;
	@Autowired
	private SubjectMapper subjectMapper;
	/**
	 * 列出所有的阶段信息
	 */
	@Override
	public Page findStageToPage(Map<String, Object> condition, Integer index, int size) {
		logger.debug("分页列表");
		int start=size*index;
		//分页找出所有信息
		List<Map<String, Object>> stages = stageMapper.findAllByPage(start,size);
		int count = stageMapper.countByCondition(condition);
		for (Map<String, Object> map : stages) {
			//获取班级名字
			Long classId=Long.valueOf(map.get("class_id").toString())  ;
			Map<String, Object> class1=classMapper.findById(classId);
			map.put("class1", class1);
			//获取老师名字
			 Object adminId=map.get("class_stage_teacher");
			 Map<String, Object> admin=adminMapper.findById(adminId);
			 map.put("admin", admin);
			//获取阶段名字与课程名字
			Long stageId=Long.valueOf( map.get("stage_id").toString());
			Map<String, Object> stageSubject=stageMapper.findStageAndSubjectByStageId(stageId);
			map.put("stageSubject", stageSubject);
		}
		Page page=new Page(index, size, count, stages);
		return page;
	}
	/**
	 * 删除的阶段信息
	 */
	@Override
	public int deleteStage(Object... classStageIds) {
		return stageMapper.deleteById(classStageIds);
	}
	/**
	 * 通过id找阶段信息记录
	 */
	@Override
	public Map<String, Object> findClassStageById(Long stageId) {
		Map<String, Object> stage=stageMapper.findByClassStageId(stageId);
		return stage;
	}
	/**
	 * 跳转编辑阶段信息记录
	 */
	@Transactional
	@Override
	public Map<String, Object> toStageEdit(Long stageId) {
		Map<String, Object> map = this.findClassStageById(stageId);
		//获取班级名字
		List<Map<String, Object>> class1=classMapper.findAll();
		map.put("classlist", class1);
		for (Map<String, Object> map2 : class1) {
			List<Map<String, Object>> list = stageMapper.findByClassId(map2.get("class_id"));
			logger.debug(list);
			if(list==null){
				return null;
			}
		}
		//获取阶段名字与课程名字
		Object subjectStageId=map.get("stage_id");
		Map<String, Object> stageAndSubject = stageMapper.findStageAndSubjectByStageId(subjectStageId);
		Object subject_id = stageAndSubject.get("subject_id");
		List<Map<String, Object>> stage= stageMapper.findStageBySubjectId(subject_id);
		map.put("stages",stage);
		map.put("subject_name", stageAndSubject.get("subject_name"));
		//获取老师名字
		List<Map<String, Object>> admin=adminMapper.findAllTeacher();
		map.put("admins", admin);
		return map;
	}
	/**
	 * 编辑阶段信息记录
	 */
	@Transactional
	@Override
	public Map<String, Object> editStage(Map<String, Object> entity) {
		Long classStageId=Long.valueOf(entity.get("class_stage_id").toString());
		//更新阶段信息
		int i = stageMapper.updateClassStage(entity);
		int b =stageMapper.updateStageAndSubject(entity);
		if(i*b>0){
			return this.toStageEdit(classStageId);
		}
		return null;
	}
	/**
	 * 跳转增加阶段信息记录
	 */
	@Transactional
	@Override
	public Map<String, Object> toStageAdd() {
		Map<String, Object> map=new HashMap<>();
		//获取全部班级名称
		List<Map<String, Object>> class1=classMapper.findAll();
		map.put("classlist", class1);
		//获取全部阶段名字与课程名字
		List<Map<String, Object>> stageName=stageMapper.findStage();
		map.put("stage", stageName);
		List<Map<String, Object>> subject=subjectMapper.findAll();
		map.put("subjects", subject);
		List<Map<String, Object>> admin=adminMapper.findAllTeacher();
		map.put("admins", admin);
		return map;
	}
	/**
	 * 增加阶段信息记录
	 */
	@Transactional
	@Override
	public int addStage(Map<String, Object> entity) {
		int a=0;
		int i=0;
		int b=0;
		try {
			List<Map<String, Object>> findStage = stageMapper.findStage();
			for (Map<String, Object> map : findStage) {
				if(map.get("stage_name").equals(entity.get("stage_name"))){
					entity.put("stage_id", map.get("stage_id"));
					i++;
				}
			}
			if(i==0){
				a=stageMapper.insertToStage(entity);
			}
			b=stageMapper.insertToClassStage(entity);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(i+a+b==2){
			return 1;
		}
		return 0;
	}
	/**
	 * 更新时选择阶段与课程
	 */
	@Override
	public Map<String, Object> selectStageSubject(int mid) {
		Long classId = Long.valueOf(mid);
		List<Map<String, Object>> StageSubjects=stageMapper.findByClassId(classId);
		Map<String, Object> entity = new HashMap<String, Object>();
		List<Object> stage_id=new ArrayList<Object>();
		List<Object> stage_name=new ArrayList<Object>();
		List<Object> subject_id=new ArrayList<Object>();
		List<Object> subject_name=new ArrayList<Object>();
		for (Map<String, Object> map : StageSubjects) {
			subject_id.add(map.get("subject_id"));
			Map<String, Object> map2 = stageMapper.findStageAndSubjectByStageId(map.get("stage_id"));
			subject_name.add(map2.get("subject_name"));
		}
		List<Map<String, Object>> subject = stageMapper.findStageBySubjectId(StageSubjects.get(0).get("subject_id"));
		for (Map<String, Object> map3 : subject) {
			stage_id.add(map3.get("stage_id"));
			stage_name.add(map3.get("stage_name"));
		}
		entity.put("stage_id", stage_id);
		entity.put("stage_name", stage_name);
		entity.put("subject_id", subject_id);
		entity.put("subject_name", subject_name);
		return entity;
	}
	@Override
	public List<Map<String, Object>> selectById(Object... stageIds) {
		// TODO Auto-generated method stub
		return stageMapper.selectById(stageIds);
	}
	

}