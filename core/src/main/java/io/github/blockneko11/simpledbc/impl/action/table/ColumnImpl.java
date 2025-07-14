package io.github.blockneko11.simpledbc.impl.action.table;

import io.github.blockneko11.simpledbc.api.action.table.Attribute;
import io.github.blockneko11.simpledbc.api.action.table.Column;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashSet;
import java.util.Set;

public class ColumnImpl implements Column {
    private final String name;
    private final String type;
    private final Set<Attribute> attributes;

    private ColumnImpl(@NotNull String name, @NotNull String type, @NotNull Set<Attribute> attributes) {
        this.name = name;
        this.type = type;
        this.attributes = attributes;
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
    public Set<Attribute> getAttributes() {
        return this.attributes;
    }

    public static class BuilderImpl implements Column.Builder {
        private final String name;
        private final String type;
        private final Set<Attribute> attributes = new LinkedHashSet<>();

        public BuilderImpl(@NotNull String name, @NotNull String type) {
            this.name = name;
            this.type = type;
        }

        @Override
        public Builder attribute(Attribute attribute) {
            this.attributes.add(attribute);
            return this;
        }

        @Override
        public Column build() {
            return new ColumnImpl(this.name, this.type, this.attributes);
        }
    }
}
