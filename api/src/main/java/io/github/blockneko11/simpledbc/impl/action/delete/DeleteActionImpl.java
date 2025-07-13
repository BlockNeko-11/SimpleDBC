package io.github.blockneko11.simpledbc.impl.action.delete;

import io.github.blockneko11.simpledbc.api.Database;
import io.github.blockneko11.simpledbc.api.action.delete.DeleteAction;
import io.github.blockneko11.simpledbc.impl.action.AbstractTableAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;

public class DeleteActionImpl extends AbstractTableAction implements DeleteAction {
    private String condition = null;

    public DeleteActionImpl(@NotNull Database executor, @NotNull String table) {
        super(executor, table);
    }

    @Override
    public DeleteAction condition(@Nullable String condition) {
        this.condition = condition;
        return this;
    }

    @Override
    public int execute() throws SQLException {
        StringBuilder sqlBuilder = new StringBuilder("DELETE FROM ");
        sqlBuilder.append(getTable());

        if (this.condition != null) {
            sqlBuilder.append(" WHERE ").append(this.condition);
        }

        sqlBuilder.append(";");
        return getExecutor().execute(sqlBuilder.toString());
    }
}
