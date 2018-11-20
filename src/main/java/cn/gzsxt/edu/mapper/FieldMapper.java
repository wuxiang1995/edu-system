
package cn.gzsxt.edu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.SelectProvider;
import cn.gzsxt.edu.mapper.provider.*;

import cn.gzsxt.edu.mapper.provider.FieldProvider;

@Mapper
public interface FieldMapper {
	
	 @SelectProvider(type=FieldProvider.class,method="findByIds")
	 List<Map<String, Object>> findByIds(Object fieldId);
		
	//@Select(value="SELECT * FROM tb_field WHERE field_id IN (#{fieldId})")
	@SelectProvider(type=FieldProvider.class,method="fieldListAll")
	List<Map<String, Object>> fieldListAll(Object... fieldId);
	

}
