package ${package}.controller.${module};

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import ${package}.core.model.Constants;
import ${package}.model.${module}.${classname};
import ${package}.service.${module}.${classname}Service;

/**
 * ${moduleDescr}控制层
 *
 * @author ${author.name} (email:${author.email})
 * @since ${createDate}
 */
<#assign instance = "${classname?uncap_first}">
<#assign _id = "${id?cap_first}">
@Controller
@RequestMapping("/${module?replace('.', '/')}")
public class ${classname}Controller {

    private Logger LOG = LoggerFactory.getLogger(getClass());
	
    /** 列表表头+查询条件视图 **/
    private static final String VIEW_TO_LIST = "${module}/${instance}List";
    /** 列表内容视图 **/
    private static final String VIEW_TO_LIST_CONTENT = "${module}/${instance}ListContent";
    /** 新增和修改视图 **/
    private static final String VIEW_TO_INFO = "${module}/${instance}Info";
    /** 详情视图 **/
    private static final String VIEW_TO_DETAIL = "${module}/${instance}Detail";

    @Resource
    private ${classname}Service ${instance}Service;
    
    /**
     * 查询列表
     */
    @RequestMapping("/list")
    public String list(${classname} ${instance}, @RequestParam(value = "pageNo", defaultValue = Constants.DEFAULT_PAGE_NO) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE) int pageSize, Model model) {
        
		try {
			/**
			 * 执行业务查询
			 */
			PageHelper.startPage(pageNo, pageSize);
			//PageHelper.orderBy("ID ASC");
			List<${classname}> ${instance}List = ${instance}Service.select(${instance});
			
			/**
			 * 封装分页参数
			 */
			PageInfo<${classname}> page = new PageInfo<${classname}>(${instance}List);
			
			model.addAttribute("pageNo", pageNo);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("page", page);
		} catch (Exception e) {
			LOG.error("查询列表出错!", e);
		}
      	return VIEW_TO_LIST;
    }
    
    /**
	 * 更新列表内容
	 */
    @RequestMapping("/listContent")
    public String listContent(${classname} ${instance}, @RequestParam(value = "pageNo", defaultValue = Constants.DEFAULT_PAGE_NO) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE) int pageSize, Model model) {
        
		try {
			/**
			 * 执行业务查询
			 */
			PageHelper.startPage(pageNo, pageSize);
			//PageHelper.orderBy("ID ASC");
			List<${classname}> ${instance}List = ${instance}Service.select(${instance});
			
			/**
			 * 封装分页参数
			 */
			PageInfo<${classname}> page = new PageInfo<${classname}>(${instance}List);
			
			model.addAttribute("pageNo", pageNo);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("page", page);
		} catch (Exception e) {
			LOG.error("更新列表内容出错!", e);
		}
		return VIEW_TO_LIST_CONTENT;
    }
    
    
   	/**
     * 跳转到新增、修改信息页面
     */
    @RequestMapping("/info")
    public Object info(Object ${id}, Model model) throws Exception {
    	try {
			${classname} ${instance} = new ${classname}();
			
			/**
			 * 主键不为空时，查询出需修改的数据信息
			 */
			if (!ObjectUtils.isEmpty(${id})) {
				/**
				 * 根据主键获取记录
				 */
				${instance} = ${instance}Service.selectById(${id});
			}
			
			model.addAttribute("${instance}", ${instance});
		} catch (Exception e) {
			LOG.error("跳转到新增、修改信息页面出错!", e);
		}
		return VIEW_TO_INFO;
	}

	/**
	 * 保存信息
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Object save(${classname} ${instance}) {
		Map<String, Object> resMap = new HashMap<String, Object>();

		try {
			/**
	         * 根据主键是否存在，判断是新增还是更新记录
	         */
			if (ObjectUtils.isEmpty(${instance}.get${_id}())) {
				/**
				 * 新增记录
				 */
				//${instance}.setCreateTime(DateTime.now().toDate());
				${instance}Service.save(${instance});
			} else {
				/**
				  * 更新记录
				  */
				${instance}Service.update(${instance});
			}
			resMap.put("success", true);
		} catch (Exception e) {
			LOG.error("保存信息出错!", e);
			resMap.put("msg", e);
			resMap.put("success", false);
		}
		return resMap;
	}

    /**
     * 删除信息
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Object ${id}) throws Exception {
    	
		Map<String, Object> resMap = new HashMap<String, Object>();
			
		try {
			if (!ObjectUtils.isEmpty(${id})) {
				/**
				 * 删除记录
				 */
				${instance}Service.deleteById(${id});
			}
			resMap.put("success", true);
		} catch (Exception e) {
			LOG.error("删除信息出错!", e);
			resMap.put("msg", e);
			resMap.put("success", false);
		}
		return resMap;
    }
    
    /**
	 * 查询详情
	 */
	@RequestMapping("/detail/{${id}}")
	public String detail(@PathVariable Object ${id}, Model model) {
		try {
			if (ObjectUtils.isEmpty(${id})) {
				throw new IllegalArgumentException("请传入正确的${id}！");
			}
	
			/**
			 * 获取记录
			 */
			${classname} ${instance} = ${instance}Service.selectById(${id});
	
			model.addAttribute("${instance}", ${instance});
		} catch (Exception e) {
			LOG.error("查询详情出错！", e);
		}
		return VIEW_TO_DETAIL;
	}
}
