package ${package}.model.${module};

import ${package}.core.model.BaseModel;

/**
 * ${moduleDescr}对象
 *
 * @author ${author.name} (email:${author.email})
 * @since ${createDate}
 */
public class ${classname} extends BaseModel {

    private static final long serialVersionUID = 1L;
    <#list tableMetaData.columns as columnMetaData>
    
    /**
     * ${columnMetaData.columnRemark} 
     */
    <#if columnMetaData.primaryKey>
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    </#if>
    private ${columnMetaData.columnType} ${columnMetaData.fieldName};
    </#list>
    <#list tableMetaData.columns as columnMetaData>
    
    /**
     * 设置${columnMetaData.columnRemark} 
     *
     * @param ${columnMetaData.fieldName}
     *            ${columnMetaData.columnRemark} 
     */
    public void set${columnMetaData.fieldName?cap_first}(${columnMetaData.columnType} ${columnMetaData.fieldName}) {
        this.${columnMetaData.fieldName} = ${columnMetaData.fieldName};
    }

    /**
     * 获取${columnMetaData.columnRemark} 
     *
     * @return ${columnMetaData.fieldName} - ${columnMetaData.columnRemark}
     */
    public ${columnMetaData.columnType} get${columnMetaData.fieldName?cap_first}() {
        return ${columnMetaData.fieldName};
    }
    </#list>
}