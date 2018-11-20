package cn.gzsxt.edu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import cn.gzsxt.edu.mapper.provider.FeedbackProvider;

@Mapper
public  interface FeedbackMapper {
	
	@SelectProvider(type=FeedbackProvider.class,method="countByCondition")
	int countByCondition(Map<String, Object> entity);
	
	@SelectProvider(type=FeedbackProvider.class,method="findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity")Map<String, Object> entity,@Param("start")int start, @Param("size")int size);
	
	@Select(value="SELECT * FROM tb_edu_feedback WHERE feedback_id=#{feedback_id}")
	Map<String, Object> findById(Object feedbackId);
	
	@Select(value="Select * FROM tb_edu_feedback ")
	List<Map<String, Object>> feedbackList();
	

	


		
}
