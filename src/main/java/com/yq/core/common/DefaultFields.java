/**
  *功能描述
  *当前版本
  *上一个版本
  *创建日期
  *修改日期
  *文件的描述
*/
package com.yq.core.common;

import java.util.ArrayList;
import java.util.List;

import com.yq.core.entity.Field;
import com.yq.core.entity.Table;
import com.yq.core.util.MongoTableUtil;

public class DefaultFields {
	
	/**
	 * 
	 *版本：
	 *功能描述：对象创建的默认的字段
	 *参数说明：@param table
	 *参数说明：@return
	 *返回值说明：
	 *更新日期：10:13:12 AM
	 *作者: GUO-QP
	 */
	public static List< Field>createDefaulFields(Table table){
		
		/*建表语句，默认创建的字段 使用
		创建时间createAt
		更新时间updateAt
		数据创建者creater
		数据更新者updater
		数据版本version 这个字段是为了保持数据排他使用
		 */
		List<Field> defaultFields = new ArrayList<Field>();
		
		//id字段
		Field idField = new Field(MongoTableUtil.getNextTableId("field"),
								"数据id",
								"dataId",
								FieldTypeConstant.NUMBER,
								table.getDataId(),table.getEnName());
		defaultFields.add(idField);
		
		//name字段
		Field nameField = new Field(MongoTableUtil.getNextTableId("field"),
								"名称","name",
								FieldTypeConstant.TEXT,
								table.getDataId(),
								table.getEnName());
		defaultFields.add(nameField);
		
		//创建时间
		Field createAtField = new Field(MongoTableUtil.getNextTableId("field"),
								"创建时间",
								"createAt",
								FieldTypeConstant.DATE_TIME,
								table.getDataId(),
								table.getEnName());
		defaultFields.add(createAtField);
		
		//更新时间
		Field updateAtField = new Field(MongoTableUtil.getNextTableId("field"),
								"更新时间","updateAt",
								FieldTypeConstant.DATE_TIME,
								table.getDataId(),
								table.getEnName());
		defaultFields.add(updateAtField);
		
		//创建者
		Field createrField = new Field(MongoTableUtil.getNextTableId("field"),
								"数据创建者",
								"creater",
								FieldTypeConstant.INNER_OBJCET,
								table.getDataId(),
								table.getEnName());
		defaultFields.add(createrField);
		
		//更新者
		Field updaterField = new Field(MongoTableUtil.getNextTableId("field"),
								"数据更新者",
								"updater",
								FieldTypeConstant.INNER_OBJCET,
								table.getDataId(),
								table.getEnName());
		defaultFields.add(updaterField);
		
		//版本
		Field versionField = new Field(MongoTableUtil.getNextTableId("field"),
								"版本",
								"version",
								FieldTypeConstant.NUMBER,
								table.getDataId(),
								table.getEnName());
		defaultFields.add(versionField);
		
		//pid
		if(table.getIfSupportParent()==1){
			Field pidField = new Field(MongoTableUtil.getNextTableId("field"),
								"父级",
								"pid",
								FieldTypeConstant.NUMBER,
								table.getDataId(),
								table.getEnName());
			defaultFields.add(pidField);
		}
		
		return defaultFields;
	}

}
