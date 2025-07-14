package io.github.blockneko11.simpledbc.impl.action.replace;

import io.github.blockneko11.simpledbc.api.Database;
import io.github.blockneko11.simpledbc.api.action.replace.ReplaceAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ValueReplaceAction extends AbstractReplaceAction {
    private final List<Object> values = new ArrayList<>();

    public ValueReplaceAction(@NotNull Database executor, @NotNull String table) {
        super(executor, table);
    }

    @Override
    public ReplaceAction value(@Nullable String key, @NotNull Object value) {
        this.values.add(value);
        return this;
    }

    @Override
    public int execute() throws SQLException {
        String sql = buildSQL(getTable(), null, this.values);
        return getExecutor().execute(sql, this.values.toArray());
    }
}
