package com.dm.system.po;

import com.dm.po.BasePO;
/**
 * <p>标题：系统菜单</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2020年11月10日 9:51</p>
 * <p>类全名：com.dm.system.po.Menu</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class Menu extends BasePO
{
	private static final long    serialVersionUID = -2416400997446704646L;
	/**id*/
	private              Integer id;
	/**菜单名称*/
	private              String  name;
	/**菜单路由*/
	private              String  router;
	/**菜单图标*/
	private              String  icon_class;
	/**菜单分组*/
	private              String  group;
	/**排序号*/
	private              Integer idx;
	/**菜单级次*/
	private              Integer level;
	/**父菜单id*/
	private              Integer parent_id;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getRouter()
	{
		return router;
	}

	public void setRouter(String router)
	{
		this.router = router;
	}

	public String getIcon_class()
	{
		return icon_class;
	}

	public void setIcon_class(String icon_class)
	{
		this.icon_class = icon_class;
	}

	public String getGroup()
	{
		return group;
	}

	public void setGroup(String group)
	{
		this.group = group;
	}

	public Integer getIdx()
	{
		return idx;
	}

	public void setIdx(Integer idx)
	{
		this.idx = idx;
	}

	public Integer getLevel()
	{
		return level;
	}

	public void setLevel(Integer level)
	{
		this.level = level;
	}

	public Integer getParent_id()
	{
		return parent_id;
	}

	public void setParent_id(Integer parent_id)
	{
		this.parent_id = parent_id;
	}

	@Override
	public String toString()
	{
		return "Menu{" + "id=" + id + ", name='" + name + '\'' + ", router='" + router + '\'' + ", icon_class='" + icon_class + '\'' + ", group='" + group + '\'' + ", idx=" + idx + ", level=" + level + ", parent_id=" + parent_id + '}';
	}
}
