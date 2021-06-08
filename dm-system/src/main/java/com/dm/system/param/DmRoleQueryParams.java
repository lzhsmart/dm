package com.dm.system.param;

import com.dm.common.vo.QueryParams;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年06月08日 19:15</p>
 * <p>类全名：com.dm.system.param.DmRoleQueryParams</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
public class DmRoleQueryParams extends QueryParams
{
	private static final long    serialVersionUID = 2651784472236127127L;
	/** 角色名称 */
	private              String  roleName;
	/** 用户名 */
	private              String  username;
	/** 状态 */
	private              Integer status;

	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}
}
