package com.mall.common.utils;

import com.mall.common.bean.Token;
import com.mall.common.config.AppConfig;
import io.jsonwebtoken.*;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @Author: C-aranlism create by 2020/9/21.
 * @Version 1.0
 * @Parm request
 * @Description
 */
public class TokenUtils {

    /**
     * redis中 token有效时长
     */
    public final static int TOKEN_REDIS_EXPIRE_TIME = 360 * 60 * 60;


    private static SignatureAlgorithm HS256 = SignatureAlgorithm.HS256;

    /**
     * 过期时间
     */
    private static final long EXPIRE_TIME = TokenUtils.TOKEN_REDIS_EXPIRE_TIME * 1000;

    public static Token getToken(){
        String token = SecurityUtils.getSubject().getPrincipal().toString();
        Map<String , Object> map = getClaims(token);
        return new Token().
                setId(MapUtils.getString(map , Token.ID)).
                setUserId(MapUtils.getString(map, Token.USER_ID)).
                setStaffId(MapUtils.getString(map, Token.STAFF_ID)).
                setRoleId(MapUtils.getString(map, Token.ROLE_ID)).
                setClient(MapUtils.getString(map, Token.IS_CLIENT));
    }

    /**
     * 获取token转换的map对象
     *
     * @param token
     * @return
     */
    public static Map<String, Object> getClaims(String token){
        return verify(token);
    }

    /**
     * staff表ID，非staff_id
     *
     * @param token
     * @return
     */
    public static String getId(String token){
        return org.apache.commons.collections.MapUtils.getString(getClaims(token) , "id" , null);
    }

    /**
     * @param token
     * @return
     */
    public static String getValue(String token, String key) {
        return org.apache.commons.collections.MapUtils.getString(getClaims(token), key, null);
    }

    /**
     * token签名
     *
     * @param id     staff的主键
     * @param claims 附属信息  用户ID  岗位ID  角色ID
     * @return
     */
    public static String sign(String id, Map<String, Object> claims){
        Calendar calendar = Calendar.getInstance();
        return Jwts.builder().
                setId(id).
                setClaims(claims).
                //签发时间
                        setIssuedAt(calendar.getTime()).
                //过期时间
                        setExpiration(new Date(calendar.getTimeInMillis() + EXPIRE_TIME)).
                //过期时间不能小于当前时间
                        setNotBefore(new Date()).
                        signWith(HS256, getAppKey()).
                        compact();
    }

    /**
     * 数据对象获取
     *
     * @param token
     * @return
     */
    public static Map<String, Object> verify(String token){
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(getAppKey()).parseClaimsJws(token).getBody();
        }catch (SignatureException e) {
            throw new SignatureException("Token签名有误[03]。");
        } catch (ExpiredJwtException e) {
            throw new AuthenticationException("Token超时,请重新授权[01]。");
        } catch (MalformedJwtException e) {
            e.printStackTrace();
            throw new MalformedJwtException("Token解析失败，请重新授权[02]。");
        }
        return claims;
    }

    public static String getAppKey(){
        return SpringUtils.getBean(AppConfig.class).getAppKey();
    }

}
