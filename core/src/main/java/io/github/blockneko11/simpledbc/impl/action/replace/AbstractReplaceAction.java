package io.github.blockneko11.simpledbc.impl.action.replace;

import io.github.blockneko11.simpledbc.api.Database;
import io.github.blockneko11.simpledbc.api.action.replace.ReplaceAction;
import io.github.blockneko11.simpledbc.impl.action.AbstractTableAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public abstract class AbstractReplaceAction extends AbstractTableAction implements ReplaceAction {
    protected AbstractReplaceAction(@NotNull Database executor, @NotNull String table) {
        super(executor, table);
    }

    protected static String buildSQL(@NotNull String table,
                                     @Nullable Collection<String> columns,
                                     @NotNull Collection<Object> values) {
        StringBuilder sqlBuilder = new StringBuilder("REPLACE INTO ");

        sqlBuilder.append(table)
                .append(" ");

        if (columns != null) {
            sqlBuilder.append("(");

            String[] arr = columns.toArray(new String[0]);
            for (int i = 0; i < arr.length; i++) {
                sqlBuilder.append(arr[i]);

                if (i < arr.length - 1) {
                    sqlBuilder.append(", ");
                }
            }

            sqlBuilder.append(") ");
        }

        sqlBuilder.append("VALUES (");

        for (int i = 0; i < values.size(); i++) {
            sqlBuilder.append("?");

            if (i < values.size() - 1) {
                sqlBuilder.append(", ");
            }
        }

        sqlBuilder.append(");");

        return sqlBuilder.toString();
    }
}
