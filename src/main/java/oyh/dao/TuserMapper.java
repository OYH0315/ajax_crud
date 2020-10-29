package oyh.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import oyh.pojo.Tuser;
import oyh.pojo.TuserExample;

public interface TuserMapper {
    long countByExample(TuserExample example);

    int deleteByExample(TuserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tuser record);

    int insertSelective(Tuser record);

    List<Tuser> selectByExample(TuserExample example);

    Tuser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tuser record, @Param("example") TuserExample example);

    int updateByExample(@Param("record") Tuser record, @Param("example") TuserExample example);

    int updateByPrimaryKeySelective(Tuser record);

    int updateByPrimaryKey(Tuser record);
}