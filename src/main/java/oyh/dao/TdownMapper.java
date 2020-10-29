package oyh.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import oyh.pojo.Tdown;
import oyh.pojo.TdownExample;

public interface TdownMapper {
    long countByExample(TdownExample example);

    int deleteByExample(TdownExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tdown record);

    int insertSelective(Tdown record);

    List<Tdown> selectByExampleWithBLOBs(TdownExample example);

    List<Tdown> selectByExample(TdownExample example);

    Tdown selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tdown record, @Param("example") TdownExample example);

    int updateByExampleWithBLOBs(@Param("record") Tdown record, @Param("example") TdownExample example);

    int updateByExample(@Param("record") Tdown record, @Param("example") TdownExample example);

    int updateByPrimaryKeySelective(Tdown record);

    int updateByPrimaryKeyWithBLOBs(Tdown record);

    int updateByPrimaryKey(Tdown record);
}