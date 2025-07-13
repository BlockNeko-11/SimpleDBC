package io.github.blockneko11.simpledbc.api.action.table;

public enum Attribute {
    PRIMARY_KEY,
    NOT_NULL,
    UNIQUE;

    @Override
    public String toString() {
        switch (this) {
            case PRIMARY_KEY:
                return "PRIMARY KEY";
            case NOT_NULL:
                return "NOT NULL";
            case UNIQUE:
                return "UNIQUE";
            default:
                return "";
        }
    }
}
