package com.kyyc.generator.codegen;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kyyc.generator.config.JDBCConnectionConfiguration;
import com.kyyc.generator.util.FieldCodeUtil;

public final class SpyUtil {
	private SpyUtil() {
	}

	public static class TableMetaData {
		private String tableName = "";
		private String tableType = "";
		private String tableRemark = "";
		private int columnSize = 0;

		private List<String> primaryKeys = new ArrayList<String>();
		private List<ColumnMetaData> columns = new ArrayList<ColumnMetaData>();

		public String getTableName() {
			return tableName;
		}

		public void setTableName(String tableName) {
			this.tableName = tableName;
		}

		public String getTableType() {
			return tableType;
		}

		public void setTableType(String tableType) {
			this.tableType = tableType;
		}

		public String getTableRemark() {
			return tableRemark;
		}

		public void setTableRemark(String tableRemark) {
			this.tableRemark = tableRemark;
		}

		public int getColumnSize() {
			return columnSize;
		}

		public void setColumnSize(int columnSize) {
			this.columnSize = columnSize;
		}

		public void setPrimaryKeys(List<String> primaryKeys) {
			this.primaryKeys = primaryKeys;
		}

		public void setColumns(List<ColumnMetaData> columns) {
			this.columns = columns;
		}

		public List<ColumnMetaData> getColumns() {
			return columns;
		}

		public List<String> getPrimaryKeys() {
			return primaryKeys;
		}

		@Override
		public String toString() {
			return "TableMetaData [tableName=" + tableName + ", tableType=" + tableType + ", tableRemark="
					+ tableRemark + ", primaryKeys=" + primaryKeys + ", columns=" + columns + "]";
		}

	}

	public static class ColumnMetaData {
		private boolean primaryKey = false;
		private String columnName = "";
		private String fieldName = "";
		private String columnType = "";
		private String columnRemark = "";

		public boolean getPrimaryKey() {
			return primaryKey;
		}

		public void setPrimaryKey(boolean primaryKey) {
			this.primaryKey = primaryKey;
		}

		public String getColumnName() {
			return columnName;
		}

		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}

		public String getFieldName() {
			return this.fieldName;
		}

		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}

		public String getColumnType() {
			return columnType;
		}

		public void setColumnType(String columnType) {
			this.columnType = columnType;
		}

		public String getColumnRemark() {
			return columnRemark;
		}

		public void setColumnRemark(String columnRemark) {
			this.columnRemark = columnRemark;
		}

		@Override
		public String toString() {
			return "ColumnMetaData [primaryKey=" + primaryKey + ", columnName=" + columnName + ", columnType="
					+ columnType + ", columnRemark=" + columnRemark + "]";
		}
	}

	private String checkDBColumnType(int type) {
		String typeName = "";
		switch (type) {
		case Types.BIGINT:
		case Types.INTEGER:
		case Types.SMALLINT:
		case Types.TINYINT:
			typeName = "Integer";
			break;
		case Types.DECIMAL:
			typeName = "Long";
			break;
		case Types.DOUBLE:
		case Types.FLOAT:
		case Types.NUMERIC:
			typeName = "Double";
			break;
		case Types.CHAR:
		case Types.CLOB:
		case Types.VARCHAR:
			typeName = "String";
			break;
		case Types.BLOB:
			typeName = "byte[]";
			break;
		default:
			typeName = "String";
			break;
		}
		return typeName;
	}

	public static TableMetaData spyDB(JDBCConnectionConfiguration jdbc, String tableName) throws Exception {

		/***
		 * 查询数据开始，请稍等！！！
		 */
		Properties props = new Properties();

		String driver = jdbc.getDriverClass();
		String url = jdbc.getConnectionURL();

		props.put("user", jdbc.getUsername());
		props.put("password", jdbc.getPassword());
		props.put("remarksReporting", "true");// 取出common设置

		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, props);
		DatabaseMetaData metaData = conn.getMetaData();
		String[] tableTypes = { "TABLE", "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS",
				"SYSNONYM" };
		ResultSet tRs = metaData.getTables(null, "%", tableName, tableTypes);
		TableMetaData tableMetaData = new TableMetaData();

		while (tRs.next()) {
			tableMetaData.setTableName(tRs.getString("TABLE_NAME").toLowerCase());
			tableMetaData.setTableType(tRs.getString("TABLE_TYPE"));
			tableMetaData.setTableRemark(tRs.getString("REMARKS"));
		}

		ResultSet cRs = metaData.getColumns(null, "%", tableName, "%");
		SpyUtil spyDB = new SpyUtil();

		ResultSet pRs = metaData.getPrimaryKeys(null, "%", tableName);
		while (pRs.next()) {
			tableMetaData.getPrimaryKeys().add(pRs.getString("COLUMN_NAME").toLowerCase());
		}

		while (cRs.next()) {
			String columnType = "";
			String columnName = cRs.getString("COLUMN_NAME");
			String remark = cRs.getString("REMARKS");
			int dataType = cRs.getInt("DATA_TYPE");
			columnType = spyDB.checkDBColumnType(dataType);
			ColumnMetaData columnMetaData = new ColumnMetaData();

			for (String primaryKeyName : tableMetaData.getPrimaryKeys()) {
				if (primaryKeyName.equalsIgnoreCase(columnName)) {
					columnMetaData.setPrimaryKey(true);
				}
			}
			columnMetaData.setColumnName(columnName.toUpperCase());
			columnMetaData.setFieldName(FieldCodeUtil.getFieldName(columnName.toLowerCase()));
			columnMetaData.setColumnType(columnType);
			columnMetaData.setColumnRemark(remark);
			tableMetaData.getColumns().add(columnMetaData);
		}
		tableMetaData.setColumnSize(tableMetaData.getColumns().size());

		tRs.close();
		cRs.close();
		pRs.close();
		conn.close();

		return tableMetaData;
	}
}
