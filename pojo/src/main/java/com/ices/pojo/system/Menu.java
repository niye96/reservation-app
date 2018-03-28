package com.ices.pojo.system;

import com.ices.reservation.common.sql.Column;
import com.ices.reservation.common.sql.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ny
 * @Date: Created in 13:46 2018/3/27 0027
 */
@Getter
@Setter
@Table(name = "menu")
public class Menu {
    @Column(column = "menu_id")
    public Long menuId;
    @Column(column = "menu_name")
    public String menuName;
    @Column(column = "parent_menu_id")
    public Long parentMenuId;
    @Column(column = "url")
    public String url;
    @Column(column = "menu_icon")
    public String menuIcon;
    @Column(column = "menu_index")
    public Integer menuIndex;
    @Column(column = "is_leaf")
    public Boolean isLeaf;
    @Column(column = "menu_level")
    public Integer menuLevel;
    public List children = new ArrayList<>();
}
