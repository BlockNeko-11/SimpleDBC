package io.github.blockneko11.simpledbc.impl.action.table;

import io.github.blockneko11.simpledbc.api.Database;
import io.github.blockneko11.simpledbc.api.action.table.Attribute;
import io.github.blockneko11.simpledbc.api.action.table.Column;
import io.github.blockneko11.simpledbc.api.action.table.TableCreateAction;
import io.github.blockneko11.simpledbc.impl.action.AbstractTableAction;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class TableCreateActionImpl extends AbstractTableAction implements TableCreateAction {
    private final Set<Column> columns = new LinkedHashSet<>();

    public TableCreateActionImpl(@NotNull Database executor, @NotNull String table) {
        super(executor, table);
    }

    @Override
    public TableCreateAction column(Column column) {
        this.columns.add(column);
        return this;
    }

    @Override
    public int execute() throws SQLException {
        return getExecutor().execute(this.buildSQL());
    }

    private String buildSQL() {
        StringBuilder sqlBuilder = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append(getTable())
                .append(" (");

        Iterator<Column> columnIterator = this.columns.iterator();
        while (columnIterator.hasNext()) {
            Column column = columnIterator.next();
            sqlBuilder.append(column.getName())
                    .append(" ")
                    .append(column.getType())
                    .append(" ");

            Iterator<Attribute> attributeIterator = column.getAttributes().iterator();
            while (attributeIterator.hasNext()) {
                Attribute attribute = attributeIterator.next();
                sqlBuilder.append(attribute);

                if (attributeIterator.hasNext()) {
                    sqlBuilder.append(" ");
                }
            }

            if (columnIterator.hasNext()) {
                sqlBuilder.append(", ");
            }
        }

        sqlBuilder.append(");");
        return sqlBuilder.toString();
    }
}
