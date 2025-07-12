package io.github.blockneko11.simpledbc.impl.action.insert;

import io.github.blockneko11.simpledbc.api.Database;
import io.github.blockneko11.simpledbc.api.action.insert.InsertAction;
import io.github.blockneko11.simpledbc.impl.action.AbstractAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractInsertAction extends AbstractAction implements InsertAction {
    private final String table;
    protected boolean ignore = false;

    public AbstractInsertAction(@NotNull Database executor, @NotNull String table) {
        super(executor);
        this.table = table;
    }

    @NotNull
    @Override
    public String getTable() {
        return this.table;
    }

    @Override
    public InsertAction ignore(boolean ignore) {
        this.ignore = ignore;
        return this;
    }

//    @Override
//    public int execute() throws SQLException {
//        return getExecutor().update(this.buildSQL(), this.values.values().toArray());
//    }
//
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

            Iterator<String> columnIterator = columns.iterator();
            while (columnIterator.hasNext()) {
                sqlBuilder.append(columnIterator.next());

                if (columnIterator.hasNext()) {
                    sqlBuilder.append(", ");
                }
            }

            sqlBuilder.append(") ");
        }

        sqlBuilder.append("VALUES (");

        Iterator<Object> valueIterator = values.iterator();
        while (valueIterator.hasNext()) {
            sqlBuilder.append("?");

            if (valueIterator.hasNext()) {
                sqlBuilder.append(", ");
            }
        }

        sqlBuilder.append(");");

        return sqlBuilder.toString();
    }
}
