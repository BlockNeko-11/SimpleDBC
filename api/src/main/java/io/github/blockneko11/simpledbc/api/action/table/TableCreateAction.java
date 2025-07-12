package io.github.blockneko11.simpledbc.api.action.table;

import io.github.blockneko11.simpledbc.api.action.TableAction;

public interface TableCreateAction extends TableAction {
    default TableCreateAction column(String name, String type) {
        return column(Column.builder(name, type).build());
    }

    default TableCreateAction column(String name, String type, Attribute... attributes) {
        Column.Builder builder = Column.builder(name, type);
        for (Attribute attribute : attributes) {
            builder.attribute(attribute);
        }

        return column(builder.build());
    }

    TableCreateAction column(Column column);
}
