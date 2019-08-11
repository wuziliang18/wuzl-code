package org.wuzl.test.annotaion.db;

import org.wuzl.test.annotaion.Constraints;
import org.wuzl.test.annotaion.DBTable;
import org.wuzl.test.annotaion.IntSqlField;
import org.wuzl.test.annotaion.StringSqlField;

@DBTable("user")
public class UserInfo {
	@IntSqlField(name="id",constraints=@Constraints(primaryKey=true))
	public int id;
	@StringSqlField(name="name",value=20)
	public String name;
	@StringSqlField(value=100)
	public String add;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
}
