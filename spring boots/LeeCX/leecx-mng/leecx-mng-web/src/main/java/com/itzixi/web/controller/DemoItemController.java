package com.itzixi.web.controller;

import com.itzixi.common.pojo.JqGridResult;
import com.itzixi.common.utils.LeeJSONResult;
import com.itzixi.pojo.DemoItem;
import com.itzixi.service.DemoItemService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/demoItem")
public class DemoItemController extends BaseController {
	
	final static Logger log = LoggerFactory.getLogger(DemoItemController.class);
	
	@Autowired
	private DemoItemService itemService;
	

	@RequestMapping("/showCreateItemPage")
	public String showCreateUserPage(HttpServletRequest request){
		
		return "item/createItem";
	}
	

	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public LeeJSONResult saveOrUpdate(DemoItem item){
		
		// 商品id不为空，则修改商品；商品id为空，则新建一个商品
		String itemId = item.getId();
		if (StringUtils.isEmpty(itemId)) {
			
			// 保存商品的操作
			itemService.saveItem(item);
		} else {
			
			item.setAmount(item.getAmount() * 100);
			itemService.updateItem(item);
		}
		
		return LeeJSONResult.ok();
	}
	

	@RequestMapping("/showQueryItemPage")
	public String showQueryItemPage(HttpServletRequest request){
		
		return "item/demoItemList";
	}
	

	@RequestMapping("/getItemInfoList")
	@ResponseBody
	public JqGridResult getItemInfoList(Integer page){
		
		if (page == null) {
			page = 1;
		}
		
		JqGridResult jqgridResult = itemService.queryItemList(page, pageSize);
		
		return jqgridResult;
	}
	

	@RequestMapping("/showItemInfoPage")
	public ModelAndView showItemInfoPage(String itemId, HttpServletRequest request){
		
		DemoItem item = itemService.queryItemById(itemId);
		
		ModelAndView mv = new ModelAndView("item/demoItemInfo");
		mv.addObject("item", item);
		
		return mv;
	}
	

	@RequestMapping("/showModifyItemPage")
	public ModelAndView showModifyItemPage(String itemId, HttpServletRequest request){
		
		DemoItem item = itemService.queryItemById(itemId);
		
		ModelAndView mv = new ModelAndView("item/modifyItem");
		mv.addObject("item", item);
		
		return mv;
	}
	

	@RequestMapping("/deleteItem")
	@ResponseBody
	public LeeJSONResult deleteItem(String itemId){
		
		if (StringUtils.isEmpty(itemId)) {
			return LeeJSONResult.errorMsg("商品id为空或者不存在...");
		}
		
		itemService.deleteItem(itemId);
		
		return LeeJSONResult.ok();
	}

}
