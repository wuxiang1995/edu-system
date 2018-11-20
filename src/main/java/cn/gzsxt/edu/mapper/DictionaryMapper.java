package cn.gzsxt.edu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.gzsxt.edu.mapper.provider.DictionaryProvider;

@Mapper
public interface DictionaryMapper {
	
	/**
	 * 通过类型编码获得数据字典的值
	 * @param code
	 * @return
	 */
	 @Select(value="SELECT * FROM tb_dictionary WHERE dic_type_code=#{code}")
	 List<Map<String,Object>> findByTypeCode(Object code);
	 
	 /**
	   *  通过字典类型编码与值确定唯一的记录
	  * @param value
	  * @param code
	  * @return
	  */
	 @Select(value="SELECT * FROM tb_dictionary WHERE dic_type_code=#{code} AND dic_value=#{value}")
	 Map<String,Object> findByTypeCodeAndValue(@Param("value") Object value,@Param("code") Object code);
	 
	 /**
		 * 条件分页的的实现
		 * 注意事项：如果多个参数，那么必须要所有的参数加上注解
		 * @param entity
		 * @param start
		 * @param size
		 * @return
		 */
		@SelectProvider(type = DictionaryProvider.class, method = "findByConditionToPage")
		List<Map<String, Object>> findByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") int start,@Param("size") int size);
		
		/**
		 * 通过条件统计记录数
		 * @param entity
		 * @return
		 */
		@SelectProvider(type = DictionaryProvider.class, method = "countByCondition")
		int countByCondition(Map<String, Object> entity);
		
		/**
		 *  增加数据字典
		 * @param entity
		 * @return
		 */
		@Insert(value="INSERT INTO tb_dictionary (dic_name, dic_value, dic_type_code, dic_type_name)	VALUES (#{dic_name},#{dic_value},#{dic_type_code},#{dic_type_name})")
		@Options(useGeneratedKeys=true,keyProperty="dic_id",keyColumn="dic_id")
		int insert(Map<String, Object> entity);
		
		
		/**
		 * 通过数据字典编号查询数据字典记录
		 * @param dictionaryId
		 * @return
		 */
		@Select(value="SELECT *	FROM tb_dictionary WHERE dic_id=#{dicId}")
		Map<String, Object> findById(Object dicId);
		
		/**
		 * 更新数据字典非空的字段
		 * @param entity
		 * @return
		 */
		@UpdateProvider(type=DictionaryProvider.class,method="updateForNotnull")
		int updateForNotnull(Map<String, Object> entity);
		
		/**
		 * 通过编号删除数据字典
		 * @param dictionaryId
		 * @return
		 */
		@DeleteProvider(type=DictionaryProvider.class,method="deleteById")
		int deleteById(Object... dictionaryIds);
		
		/**
		 * 查询所有数据字典的数据
		 * @return
		 */
		@Select(value="SELECT *	FROM tb_dictionary")
		List<Map<String, Object>> findAll();


}
