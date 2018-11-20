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

import cn.gzsxt.edu.mapper.provider.ChapterProvider;

@Mapper
public interface ChapterMapper {

	/**
	 * 条件分页的的实现
	 * 注意事项：如果多个参数，那么必须要所有的参数加上注解
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	@SelectProvider(type = ChapterProvider.class, method = "findByConditionToPage")
	List<Map<String, Object>> findByConditionToPage(@Param("entity") Map<String, Object> entity,@Param("start") int start,@Param("size") int size);
	
	/**
	 * 通过条件统计记录数
	 * @param entity
	 * @return
	 */
	@SelectProvider(type = ChapterProvider.class, method = "countByCondition")
	int countByCondition(Map<String, Object> entity);
	
	/**
	 *  增加课程
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO  tb_edu_chapter  (chapter_id,chapter_title,chapter_summary,subject_id)	VALUES (#{chapter_id},#{chapter_title},#{chapter_summary},#{subject_id})")
	@Options(useGeneratedKeys=true,keyProperty="chapter_id",keyColumn="chapter_id")
	int insert(Map<String, Object> entity);
	
	
	/**
	 * 通过课程编号查询课程记录
	 * @param chapterId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_edu_chapter WHERE chapter_id=#{chapterId}")
	Map<String, Object> findById(Object chapterId);
	
	/**
	 * 更新课程非空的字段
	 * @param entity
	 * @return
	 */
	@UpdateProvider(type=ChapterProvider.class,method="updateForNotnull")
	int updateForNotnull(Map<String, Object> entity);
	
	/**
	 * 通过编号删除课程
	 * @param chapterId
	 * @return
	 */
	@DeleteProvider(type=ChapterProvider.class,method="deleteById")
	int deleteById(Object... chapterIds);
	
	/**
	 * 查询所有课程的数据
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_edu_chapter")
	List<Map<String, Object>> findAll();
	
	@Select(value="SELECT *	FROM tb_edu_chapter WHERE subject_id=#{subject_id}")
	List<Map<String, Object>> findBySubjectId(Object subject_id);
//	@Select(value="SELECT *	FROM tb_edu_chapter WHERE chapter_id in (#{chapterIds})")
	@SelectProvider(type=ChapterProvider.class,method="findChapters")
	List<Map<String, Object>> findChapters(Object chapterIds);
	
}	

