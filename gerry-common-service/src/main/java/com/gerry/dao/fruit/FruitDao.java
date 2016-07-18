package com.gerry.dao.fruit;

import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.ReturnGeneratedId;
import org.jfaster.mango.annotation.SQL;

import com.gerry.service.dao.entity.Fruit;

@DB(table = "fruit")
public interface FruitDao {
	String COLUMNS = "id, name, num";

	@ReturnGeneratedId
	@SQL("insert into #table(" + COLUMNS + ") values(:id, :name, :num)")
	int addFruit(Fruit fruit);

	@SQL("select " + COLUMNS + " from #table where id = :1")
	Fruit getFruit(int id);

	@SQL("update #table set name=:name, num=:num where id = :id")
	boolean updateFruit(Fruit fruit);

	@SQL("delete from #table where id = :1")
	boolean deleteFruit(int id);
}
