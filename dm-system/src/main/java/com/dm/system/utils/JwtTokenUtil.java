package com.dm.system.utils;

import com.dm.common.util.RedisCache;
import com.dm.common.utils.StringUtils;
import com.dm.system.constants.SystemConstants;
import com.dm.system.vo.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <pre>
 * 其他说明：
 * </pre>
 * <p>作者：lizh</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2021年02月21日 15:20</p>
 * <p>类全名：com.dm.system.utils.JwtTokenUtil</p>
 * 查看帮助：<a href="" target="_blank"></a>
 */
@Component
public class JwtTokenUtil
{
	//私钥
	private static final String     SECRET_KEY      = "dm_security";
	// 过期时间 毫秒,设置默认1周的时间过期
	private static final long       EXPIRATION_TIME = 3600000L * 24 * 7;
	@Resource
	private              RedisCache redisCache;

	/**
	 * 生成令牌
	 * @param userDetails 用户
	 * @return 令牌
	 */
	public String generateToken(UserDetails userDetails)
	{
		Map<String,Object> claims = new HashMap<>(2);
		claims.put(Claims.SUBJECT, userDetails.getUsername());
		claims.put(Claims.ISSUED_AT, new Date());
		return generateToken(claims);
	}

	/**
	 * 从令牌中获取用户名
	 * @param token 令牌
	 * @return 用户名
	 */
	public String getUsernameFromToken(String token)
	{
		String username = null;
		try
		{
			Claims claims = getClaimsFromToken(token);
			System.out.println("claims = " + claims.toString());
			username = claims.getSubject();
		} catch (Exception e)
		{
			System.out.println("e = " + e.getMessage());
		}
		return username;
	}

	/**
	 * 获取用户身份信息
	 *
	 * @return 用户信息
	 */
	public LoginUser getLoginUser(HttpServletRequest request)
	{
		// 获取请求携带的令牌
		String token = getToken(request);
		if (StringUtils.isNotEmpty(token))
		{
			Claims claims = getClaimsFromToken(token);
			String username = claims.getSubject();
			// 解析对应的权限以及用户信息
			return redisCache.getCacheObject(username);
		}
		return null;
	}

	/**
	 * 获取请求token
	 * @param request 请求
	 * @return token
	 */
	private String getToken(HttpServletRequest request)
	{
		String token = request.getHeader(SystemConstants.TOKEN_HEADER);
		if (StringUtils.isNotEmpty(token) && token.startsWith(SystemConstants.TOKEN_PREFIX))
		{
			token = token.replace(SystemConstants.TOKEN_PREFIX, "");
		}
		return token;
	}

	/**
	 * 判断令牌是否过期
	 * @param token 令牌
	 * @return 是否过期
	 */
	public Boolean isTokenExpired(String token)
	{
		Claims claims = getClaimsFromToken(token);
		Date expiration = claims.getExpiration();
		return expiration.before(new Date());
	}

	/**
	 * 刷新令牌
	 * @param token 原令牌
	 * @return 新令牌
	 */
	public String refreshToken(String token)
	{
		String refreshedToken;
		try
		{
			Claims claims = getClaimsFromToken(token);
			claims.put(Claims.ISSUED_AT, new Date());
			refreshedToken = generateToken(claims);
		} catch (Exception e)
		{
			refreshedToken = null;
		}
		return refreshedToken;
	}

	/**
	 * 验证令牌
	 * @param loginUser 用户
	 * @return 是否有效
	 * @throws Exception
	 */
	public Boolean validateToken(LoginUser loginUser)
	{
		return true;
		/* TODO
		LoginUser user = (LoginUser) userDetails;
		String username = getUsernameFromToken(token);
		return (username.equals(user.getUsername()) && !isTokenExpired(token));
		*/
	}

	/**
	 * 从数据声明生成令牌
	 * @param claims 数据声明
	 * @return 令牌
	 */
	private String generateToken(Map<String,Object> claims)
	{
		Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
		return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
	}

	/**
	 * 从令牌中获取数据声明
	 * @param token 令牌
	 * @return 数据声明
	 */
	private Claims getClaimsFromToken(String token)
	{
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
}
