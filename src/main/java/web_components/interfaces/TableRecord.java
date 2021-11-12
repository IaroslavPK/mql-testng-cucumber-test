package web_components.interfaces;

import web_components.annotations.TableColumn;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class TableRecord implements WebComponent {

    public abstract String getId();

    public abstract void select();

    public abstract Object[] getRecordData();

    public List<String> getColumns() {
        List<String> columns = new ArrayList<>();
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(TableColumn.class)) {
                TableColumn tableColumn = field.getAnnotation(TableColumn.class);
                field.setAccessible(true);
                columns.add(tableColumn.value());
            }
        }
        return columns;
    }

}
