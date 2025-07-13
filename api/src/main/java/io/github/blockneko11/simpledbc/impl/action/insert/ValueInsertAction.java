package io.github.blockneko11.simpledbc.impl.action.insert;

import io.github.blockneko11.simpledbc.api.Database;
import io.github.blockneko11.simpledbc.api.action.insert.InsertAction;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ValueInsertAction extends AbstractInsertAction {
    private final List<Object> values = new ArrayList<>();

    public ValueInsertAction(@NotNull Database executor, @NotNull String table) {
        super(executor, table);
    }

    @Override
    public InsertAction value(String key, Object value) {
        this.values.add(value);
        return this;
    }

    @Override
    public int execute() throws SQLException {
        String sql = buildSQL(this.ignore, getTable(), null, this.values);
        return getExecutor().execute(sql, this.values.toArray());
    }
}
