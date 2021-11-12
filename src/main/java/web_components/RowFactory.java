package web_components;

import com.codeborne.selenide.SelenideElement;
import web_components.interfaces.TableRecord;

public class RowFactory {

    @SuppressWarnings("unchecked")
    public static <T extends TableRecord> T getRow(Class<T> clazz, SelenideElement rowElement) {
        T row = null;
        if (clazz.equals(EventTableRecord.class)) {
            row = (T) new EventTableRecord(rowElement);
        } else  if (clazz.equals(HistoryTableRecord.class)) {
            row = (T) new HistoryTableRecord(rowElement);
        }
        if (row != null) return row;
        throw new IllegalArgumentException("Cannot instantiate " + clazz.getTypeName() + " row");
    }
}
