package io.github.blockneko11.simpledbc.api.table;

import io.github.blockneko11.simpledbc.impl.table.TableImpl;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface Table {
    @NotNull
    String getName();

    @NotNull
    List<Column> getColumns();

    static Table.Builder builder(@NotNull String name) {
        return new TableImpl.BuilderImpl(name);
    }

    interface Builder {
        Table.Builder column(Column column);

        Table build();
    }
}
