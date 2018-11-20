package cn.gzsxt.edu.mapper;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import com.mysql.fabric.xmlrpc.base.Data;

import cn.gzsxt.edu.mapper.provider.FbProvider;

@Mapper
public interface FbMapper {
     //List<Map<String, List<Map<String, Object>>>> aa=null;
	/**
	 * 查询反馈表所有的信息    按时间降序排列   大到小
	 * select * from tb_edu_feedback order by feedback_create_date desc
	 */
	@Select(value="select * from tb_edu_feedback order by feedback_create_date desc")
	List<Map<String, Object>> findAll();
	
	@Select(value="select count(*) from tb_edu_feedback")
	int findCount();
	
	//@Select(value="select * from tb_edu_feedback order by feedback_create_date desc limit #{start},#{size}")
	//@Results(value=@org.apache.ibatis.annotations.Result(property="class_name",many=@Many(fetchType=FetchType.EAGER,select="cn.gzsxt.edu.mapper.FbMapper.findClassName"),column="class_stage_id"))
	@Select(value="select t1.*,t2.class_name,t2.class_student_number from tb_edu_feedback t1,(select t3.class_name,t3.class_student_number,t3.class_id,t4.class_stage_id from tb_edu_class t3,tb_class_stage t4 where t3.class_id=t4.class_id) t2 where t1.class_stage_id=t2.class_stage_id order by feedback_create_date desc limit #{start},#{size}")
	List<Map<String, Object>> findByToPage(@Param("start") int start,@Param("size") int size);
	
	/*@Select(value="select class_name  from tb_edu_class where class_id= (select class_id from tb_class_stage  where class_stage_id = #{class_stage_id})")
	Map<String, Object> findClassName(int class_stage_id);*/
	
	@DeleteProvider(type=FbProvider.class,method="deleteByIds")
	int deleteByIds(Object... fbIds);
	
	/**
	 * 为什么设置为Integer  因为你传递null过去反馈结果就可以为null
	 * @param feedbackId
	 * @return
	 */
	@Select(value="select feedback_visit from tb_edu_feedback  where feedback_id=#{feedbackId}") 
	Integer findVisit(Long feedbackId);
	
	/**
	 * 因为是发起状态   使用默认为0     1 未发起
	 * @param feedbackId
	 * @return
	 */
	@Update(value="UPDATE tb_edu_feedback SET feedback_visit=0 ,feedback_start_date=#{feedback_start_date} WHERE feedback_id=#{feedback_id}")
	int updateVisitById(Map<String,Object> entity);
	@Update(value="UPDATE tb_edu_feedback SET feedback_visit=1,feedback_close_date=#{feedback_close_date} WHERE feedback_id=#{feedback_id}")
	int updateCloseVisitById(Map<String,Object> entity);

	/**
	 *  增加每天反馈
	 * @param entity
	 * @return
	 */
	@Insert(value="INSERT INTO tb_edu_feedback(feedback_title, feedback_admin_id, feedback_class_id, feedback_chapters,class_stage_id,feedback_student_total,feedback_visit,feedback_create_date) VALUES (#{feedback_title},#{feedback_admin_id},#{feedback_class_id},#{feedback_chapters},#{class_stage_id},#{feedback_student_total},#{feedback_visit},#{feedback_create_date})")
	@Options(useGeneratedKeys=true,keyProperty="feedback_id",keyColumn="feedback_id")
	int insert(Map<String, Object> entity);
	
	//select count(*) from tb_student_feedback where feedback_id=7 and student_feedback_date='2018-11-13'
	@Select(value="select count(*) from tb_student_feedback where feedback_id=#{feedbackId} and student_feedback_date=#{feedbackCreateDate}")
	int submitNumber(@Param("feedbackId") Long feedbackId,@Param("feedbackCreateDate") Date feedbackCreateDate);
	@Select(value="select * from tb_edu_feedback where feedback_id=#{feedbackId}")
	Map<String, Object> findById(Long feedbackId);
	@Update(value="UPDATE tb_edu_feedback SET feedback_title=#{feedback_title} ,feedback_create_date=#{feedback_create_date},feedback_admin_id=#{feedback_admin_id},feedback_class_id=#{feedback_class_id},feedback_chapters=#{feedback_chapters},feedback_student_total=#{feedback_student_total},class_stage_id=#{class_stage_id} WHERE feedback_id=#{feedback_id}")
	int update(Map<String, Object> entity);


}







