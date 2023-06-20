package com.jinzunyue.share.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinzunyue.share.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 在MyBatis中，#{}占位符用于引用单个值，而不是列表。
 * 如果你想引用一个列表，你应该使用${}占位符，并且在SQL语句中使用IN关键字。
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 这段代码使用了MyBatis的<script>和<foreach>元素来构建一个动态的SQL语句。
     * <foreach>元素会遍历roleIds列表，并为每个元素生成一个#{}占位符。
     * 结果SQL语句将类似于SELECT * FROM rc_menu WHERE id IN (SELECT menu_id FROM rc_privilege WHERE role_id IN (1, 2, 3))。
     *
     * 请注意，这个解决方案假设你的角色ID都是有效的整数。如果你的角色ID不是整数，你可能需要修改这个解决方案以适应你的应用。
     *
     *
     * 这个查询用DISTINCT关键字将只返回唯一的菜单，即使它们与多个角色关联
     *
     * @param roleIds
     * @return
     */
    @Select("<script>SELECT DISTINCT * FROM rc_menu WHERE id IN (SELECT menu_id FROM rc_privilege WHERE role_id IN <foreach item='item' index='index' collection='roleIds' open='(' separator=',' close=')'>#{item}</foreach>)</script>")
    List<Menu> selectMenusByRoleIds(@Param("roleIds") List<Integer> roleIds);
}
