package reflection.annotations.annotation_init_example.graph_example;

import reflection.annotations.annotation_init_example.annotations.Annotations;

import java.util.List;

public class SqlQueryBuilder {

    @Annotations.Input("ids")
    private List<String> ids;

    @Annotations.Input("limit")
    private Integer limit;


    @Annotations.Input("table")
    private String tableName;


    @Annotations.Input("columns")
    private List<String> columnNames;

    public SqlQueryBuilder(List<String> ids, Integer limit, String tableName, List<String> columnNames) {
        this.ids = ids;
        this.limit = limit;
        this.tableName = tableName;
        this.columnNames = columnNames;
    }

    @Annotations.Operation("SelectBuilder")
    public String selectStatementBuilder(@Annotations.Input("table") String tableName,
                                         @Annotations.Input("columns") List<String> columnNames) {
        String columnString = columnNames.isEmpty() ? "*" : String.join(",", columnNames);
        return String.format("SELECT %s FROM %s", columnString, tableName);
    }

    @Annotations.Operation("WhereClauseBuilder")
    public String addWhereClause(@Annotations.DependsOn("SelectBuilder") String query,
                                 @Annotations.Input("ids") List<String> ids) {
        if (ids.isEmpty()) {
            return query;
        }
        return String.format("%s WHERE id IN (%s)", query, String.join(",", ids));
    }

    @Annotations.FinalResult
    public String addLimitString(@Annotations.DependsOn("WhereClauseBuilder") String query,
                                 @Annotations.Input("limit") Integer limit) {
        if (limit == null || limit == 0) {
            return query;
        }
        if (limit < 0) {
            throw new RuntimeException("limit cannot be negative");
        }

        return String.format("%s LIMIT %d", query, limit);
    }
}
