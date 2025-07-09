package io.github.blockneko11.simpledbc.impl.table;

import io.github.blockneko11.simpledbc.api.table.Column;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class ColumnImpl implements Column {
    private final String name;
    private final String type;
    private final Set<Column.Feature> features;

    public ColumnImpl(@NotNull String name, @NotNull String type, @NotNull Set<Column.Feature> features) {
        this.name = name;
        this.type = type;
        this.features = features;
    }

    @NotNull
    @Override
    public String getName() {
        return this.name;
    }

    @NotNull
    @Override
    public String getType() {
        return this.type;
    }

    @NotNull
    @Override
    public Set<Column.Feature> getFeatures() {
        return this.features;
    }

    public static class BuilderImpl implements Column.Builder {
        private final String name;
        private final String type;
        private final Set<Column.Feature> features = new HashSet<>();

        public BuilderImpl(@NotNull String name, @NotNull String type) {
            this.name = name;
            this.type = type;
        }

        @Override
        public Column.Builder addFeature(Column.Feature feature) {
            this.features.add(feature);
            return this;
        }

        @Override
        public Column build() {
            return new ColumnImpl(this.name, this.type, this.features);
        }
    }
}
