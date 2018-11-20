package cn.gzsxt.edu.mapper;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import cn.gzsxt.edu.mapper.provider.EvaluateProvider;
import cn.gzsxt.edu.mapper.provider.FbProvider;

@Mapper
public interface EvaluateMapper {

	@Select(value = "SELECT * FROM tb_edu_evaluate WHERE evaluate_id=#{evaluateId}")
	Map<String, Object> selectAll(Object evaluateId);

	@SelectProvider(type = EvaluateProvider.class, method = "countByCondition")
	int countByCondition(Map<String, Object> entity);

	@SelectProvider(type = EvaluateProvider.class, method = "findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity") Map<String, Object> entity,
			@Param("start") int start, @Param("size") int size);

	@Select(value = "SELECT * FROM tb_edu_evaluate WHERE evaluate_id=#{evaluateId}")
	Map<String, Object> findById(Object evaluateId);

	/**
	 * 查询评测表所有的信息 按时间降序排列 大到小 并分页 select * from tb_edu_feedback order by
	 * feedback_create_date desc
	 */
	@Select(value = "select t1.*,t2.class_name,t2.class_student_number from tb_edu_evaluate t1,(select t3.class_name,t3.class_student_number,t3.class_id,t4.class_stage_id from tb_edu_class t3,tb_class_stage t4 where t3.class_id=t4.class_id) t2 where t1.class_stage_id=t2.class_stage_id order by evaluate_create_date desc limit #{start},#{size}")
	List<Map<String, Object>> findByToPage(@Param("start") int start, @Param("size") int size);

	/**
	 * 查询总记录数
	 */
	@Select(value = "select count(*) from tb_edu_evaluate")
	int findCount();

	/**
	 * 多条删除或者单条删除
	 */
	@DeleteProvider(type = EvaluateProvider.class, method = "deleteByIds")
	int deleteByIds(Object... evaluateIds);

	/**
	 * 因为是发起状态 使用默认为0 1 未发起
	 * 
	 * @param feedbackId
	 * @return
	 */
	@Update(value = "UPDATE tb_edu_evaluate SET evaluate_visit=0,evaluate_start_date=#{evaluate_start_date} WHERE evaluate_id=#{evaluate_id}")
	int updateVisitById(Map<String,Object> entity);

	@Update(value = "UPDATE tb_edu_evaluate SET evaluate_visit=1,evaluate_close_date=#{evaluate_close_date} WHERE evaluate_id=#{evaluate_id}")
	int updateCloseVisitById(Map<String,Object> entity);

	/**
	 * 为什么设置为Integer 因为你传递null过去反馈结果就可以为null
	 * 
	 * @param feedbackId
	 * @return
	 */
	@Select(value = "select evaluate_visit from tb_edu_evaluate  where evaluate_id=#{evaluateId}")
	Integer findVisit(Long evaluateId);

	// select count(*) from tb_student_feedback where feedback_id=7 and
	// student_feedback_date='2018-11-13'
	@Select(value = "select count(*) from tb_student_evaluate where evaluate_id=#{evaluate_id} and student_evaluate_date=#{studentEvaluateDate}")
	int submitNumber(@Param("evaluate_id") Long feedbackId, @Param("studentEvaluateDate") Date feedbackCreateDate);

}
