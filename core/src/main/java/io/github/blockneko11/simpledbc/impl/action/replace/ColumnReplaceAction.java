package io.github.blockneko11.simpledbc.impl.action.replace;

import io.github.blockneko11.simpledbc.api.Database;
import io.github.blockneko11.simpledbc.api.action.replace.ReplaceAction;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ColumnReplaceAction extends AbstractReplaceAction {
    private final Map<String, Object> values = new LinkedHashMap<>();

    public ColumnReplaceAction(@NotNull Database executor, @NotNull String table) {
        super(executor, table);
    }

    @Override
    public ReplaceAction value(@NotNull String key, @NotNull Object value) {
        this.values.put(key, value);
        return this;
    }

    @Override
    public int execute() throws SQLException {
        String sql = buildSQL(getTable(), this.values.keySet(), this.values.values());
        return getExecutor().execute(sql, this.values.values().toArray());
    }
}
