package cn.creater.dao.mapper;

import cn.creater.po.UserActive;
import cn.creater.po.UserActiveExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserActiveMapper {
    int countByExample(UserActiveExample example);

    int deleteByExample(UserActiveExample example);

    int deleteByPrimaryKey(String userId);

    int insert(UserActive record);

    int insertSelective(UserActive record);

    List<UserActive> selectByExample(UserActiveExample example);

    UserActive selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") UserActive record, @Param("example") UserActiveExample example);

    int updateByExample(@Param("record") UserActive record, @Param("example") UserActiveExample example);

    int updateByPrimaryKeySelective(UserActive record);

    int updateByPrimaryKey(UserActive record);
}