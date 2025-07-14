package io.github.blockneko11.simpledbc.impl.action.insert;

import io.github.blockneko11.simpledbc.api.Database;
import io.github.blockneko11.simpledbc.api.action.insert.InsertAction;
import io.github.blockneko11.simpledbc.impl.action.AbstractTableAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public abstract class AbstractInsertAction extends AbstractTableAction implements InsertAction {
    protected boolean ignore = false;

    protected AbstractInsertAction(@NotNull Database executor, @NotNull String table) {
        super(executor, table);
    }

    @Override
    public InsertAction ignore(boolean ignore) {
        this.ignore = ignore;
        return this;
    }
    
    protected static String buildSQL(boolean ignore,
                                     @NotNull String table,
                                     @Nullable Collection<String> columns,
                                     @NotNull Collection<Object> values) {
        StringBuilder sqlBuilder = new StringBuilder("INSERT ");

        if (ignore) {
            sqlBuilder.append("IGNORE ");
        }

        sqlBuilder.append("INTO ")
                .append(table)
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
