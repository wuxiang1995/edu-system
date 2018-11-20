package cn.gzsxt.edu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import cn.gzsxt.edu.mapper.provider.StudentEvaluateProvider;

@Mapper
public interface StudentEvaluateMapper {
	
	@SelectProvider(type=StudentEvaluateProvider.class,method="countByCondition")
	int countByCondition(Map<String, Object> condition);
	
	@SelectProvider(type=StudentEvaluateProvider.class,method="findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity")Map<String, Object> entity,@Param("start")int start, @Param("size")int size);

}
