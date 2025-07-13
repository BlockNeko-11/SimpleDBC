package io.github.blockneko11.simpledbc.impl.action.insert;

import io.github.blockneko11.simpledbc.api.Database;
import io.github.blockneko11.simpledbc.api.action.insert.InsertAction;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ColumnInsertAction extends AbstractInsertAction {
    private final Map<String, Object> values = new LinkedHashMap<>();

    public ColumnInsertAction(@NotNull Database executor, @NotNull String table) {
        super(executor, table);
    }

    @Override
    public InsertAction value(@NotNull String column, @NotNull Object value) {
        this.values.put(column, value);
        return this;
    }

    @Override
    public int execute() throws SQLException {
        String sql = buildSQL(this.ignore, getTable(), this.values.keySet(), this.values.values());
        return getExecutor().execute(sql, this.values.values().toArray());
    }
}
