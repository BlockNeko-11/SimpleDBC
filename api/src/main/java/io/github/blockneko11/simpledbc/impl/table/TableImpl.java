package io.github.blockneko11.simpledbc.impl.table;

import io.github.blockneko11.simpledbc.api.table.Column;
import io.github.blockneko11.simpledbc.api.table.Table;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TableImpl implements Table {
    private final String name;
    private final List<Column> columns;

    private TableImpl(@NotNull String name, @NotNull List<Column> columns) {
        this.name = name;
        this.columns = columns;
    }

    @NotNull
    @Override
    public String getName() {
        return this.name;
    }

    @NotNull
    @Override
    public List<Column> getColumns() {
        return this.columns;
    }

    public static class BuilderImpl implements Table.Builder {
        private final String name;
        private final List<Column> columns = new ArrayList<>();

        public BuilderImpl(@NotNull String name) {
            this.name = name;
        }

        @Override
        public Table.Builder column(Column column) {
            this.columns.add(column);
            return this;
        }

        @Override
        public Table build() {
            return new TableImpl(this.name, this.columns);
        }
    }
}
