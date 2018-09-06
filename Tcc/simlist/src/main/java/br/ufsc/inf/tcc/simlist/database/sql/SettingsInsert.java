package br.ufsc.inf.tcc.simlist.database.sql;

import java.util.HashMap;
import java.util.Map;

import com.mysema.query.sql.RelationalPath;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.NumberPath;

public class SettingsInsert {

	private RelationalPath<?> table = null;
	private NumberPath<Long> primaryKey = null;
	private Map<Path<Object>, Object> values;

	public SettingsInsert() {
		super();
		values = new HashMap<Path<Object>, Object>();
	}

	public SettingsInsert table(RelationalPath<?> table) {
		this.table = table;
		return this;
	}

	public SettingsInsert primaryKey(NumberPath<Long> primaryKey) {
		this.primaryKey = primaryKey;
		return this;
	}

	@SuppressWarnings("unchecked")
	public SettingsInsert value(Path<?> key, Object value) {
		values.put((Path<Object>) key, value);
		return this;
	}

	public RelationalPath<?> getTable() {
		return table;
	}

	public NumberPath<Long> getPrimaryKey() {
		return primaryKey;
	}

	public Map<Path<Object>, Object> getValues() {
		return values;
	}

	public Long insert() {
		SQLInsertClause insertClause = new SQLContext().insert(table);

		for (Map.Entry<Path<Object>, Object> entry : values.entrySet()) {
			Path<Object> key = entry.getKey();
			Object value = entry.getValue();

			insertClause.set(key, value);
		}
		if (primaryKey != null) {
			return insertClause.executeWithKey(primaryKey);
		}
		return insertClause.execute();
	}

}
