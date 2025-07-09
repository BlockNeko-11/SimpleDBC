package io.github.blockneko11.simpledbc.api.table;

import io.github.blockneko11.simpledbc.impl.table.ColumnImpl;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public interface Column {
    @NotNull
    String getName();

    @NotNull
    String getType();

    @NotNull
    Set<Column.Feature> getFeatures();

    static Column.Builder builder(@NotNull String name, @NotNull String type) {
        return new ColumnImpl.BuilderImpl(name, type);
    }

    interface Builder {
        Column.Builder addFeature(Column.Feature feature);

        Column build();
    }

    enum Feature {
        PRIMARY_KEY,
        AUTO_INCREMENT,
        NOT_NULL,
        UNIQUE;

        @Override
        public String toString() {
            switch (this) {
                case PRIMARY_KEY:
                    return "PRIMARY KEY";
                case AUTO_INCREMENT:
                    return "AUTO_INCREMENT";
                case NOT_NULL:
                    return "NOT NULL";
                case UNIQUE:
                    return "UNIQUE";
                default:
                    throw new IllegalArgumentException("Unknown column feature: " + this);
            }
        }
    }
}
