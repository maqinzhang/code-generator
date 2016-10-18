package ${package}.dao.${module};

import ${package}.model.${module}.${classname};
import ${package}.core.dao.BaseMapper;

/**
 * ${moduleDescr}持久层
 *
 * @author ${author.name} (email:${author.email})
 * @since ${createDate}
 */
<#assign instance = "${classname?uncap_first}">
public interface ${classname}Mapper extends BaseMapper<${classname}> {
}
