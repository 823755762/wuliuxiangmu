package com.hz.service;

import com.hz.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author UserG
 * @since 2022-04-26
 */
public interface MenuService extends IService<Menu> {
    /**
     *
     * @param userId
     * @return
     */
     String findMenuListByUserid(Long userId);
     List<Menu> getClildren(String menuId);
     List<String> findMenuIdsByroleId(Long roleId);
}
