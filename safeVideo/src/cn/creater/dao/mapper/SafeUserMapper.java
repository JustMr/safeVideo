package cn.creater.dao.mapper;

import cn.creater.po.SafeUser;
import cn.creater.po.SafeUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SafeUserMapper {
    int countByExample(SafeUserExample example);

    int deleteByExample(SafeUserExample example);

    int deleteByPrimaryKey(String userId);

    int insert(SafeUser record);

    int insertSelective(SafeUser record);

    List<SafeUser> selectByExample(SafeUserExample example);

    SafeUser selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") SafeUser record, @Param("example") SafeUserExample example);

    int updateByExample(@Param("record") SafeUser record, @Param("example") SafeUserExample example);

    int updateByPrimaryKeySelective(SafeUser record);

    int updateByPrimaryKey(SafeUser record);
}