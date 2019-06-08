package com.debug.mooc.dubbo.one.server.service.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.debug.mooc.dubbo.one.api.enums.StatusCode;
import com.debug.mooc.dubbo.one.api.response.BaseResponse;
import com.debug.mooc.dubbo.one.api.service.IDubboItemService;
import com.debug.mooc.dubbo.one.model.entity.ItemInfo;
import com.debug.mooc.dubbo.one.model.mapper.ItemInfoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * @author coderpwh
 * @create 2019-06-03 22:19
 * @desc ${DESCRIPTION}
 **/
@Service(protocol = {"dubbo","rest"},validation = "true",version = "1.0",timeout = 3000)
@Path("moocOne")
public class DubboItemService implements IDubboItemService {

    private static final Logger log= LoggerFactory.getLogger(DubboItemService.class);

    @Autowired
    private ItemInfoMapper itemInfoMapper;


    /**
     * 列表查询服务-实际的业务实现逻辑
     * @return
     */
    @Path("item/list")
    public BaseResponse listItems() {
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            List<ItemInfo> infos=itemInfoMapper.selectAll();
            log.info("查询到的商品列表数据：{} ",infos);
            response.setData(infos);

        }catch (Exception e){
            log.error("列表查询服务-实际的业务实现逻辑-发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }




    /**
     * 列表查询-分页查询
     * @return
     */
    @Path("item/page/list")
    public BaseResponse listPageItems(@QueryParam("pageNo") Integer pageNo,
                                      @QueryParam("PageSize") Integer PageSize) {
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            //TODO:分页组件-第pageNo页，pageSize条数目数据
            PageHelper.startPage(pageNo,PageSize);
            PageInfo info=new PageInfo<ItemInfo>(itemInfoMapper.selectAll());
            response.setData(info);

        }catch (Exception e){
            log.error("列表查询-分页查询服务-实际的业务实现逻辑-发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }

    /**
     * 列表查询-分页查询-模糊查询
     * @return
     */
    @Path("item/page/list/params")
    public BaseResponse listPageItemsParams(@QueryParam("pageNo") Integer pageNo,
                                            @QueryParam("PageSize") Integer PageSize,
                                            @QueryParam("search") String search) {
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            //TODO:分页组件-第pageNo页，pageSize条数目数据
            PageHelper.startPage(pageNo,PageSize);
            PageInfo info=new PageInfo<ItemInfo>(itemInfoMapper.selectAllByParams(search));
            response.setData(info);

        }catch (Exception e){
            log.error("列表查询-分页查询模糊查询服务-实际的业务实现逻辑-发生异常：",e.fillInStackTrace());
            response=new BaseResponse(StatusCode.Fail);
        }
        return response;
    }
}
