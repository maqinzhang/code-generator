package ${package}.service.${module};

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ${package}.model.${module}.${classname};
import ${package}.dao.${module}.${classname}Mapper;
import ${package}.core.service.BaseService;

/**
 * ${moduleDescr}业务层
 *
 * @author ${author.name} (email:${author.email})
 * @since ${createDate}
 */
<#assign instance = "${classname?uncap_first}">
@Service
public class ${classname}Service extends BaseService<${classname}> {

  	@Resource
	private ${classname}Mapper ${instance}Mapper;
}
