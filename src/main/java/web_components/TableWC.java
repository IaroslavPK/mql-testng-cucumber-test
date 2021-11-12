package web_components;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import dnl.utils.text.table.TextTable;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import web_components.interfaces.Table;
import web_components.interfaces.TableRecord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$;

@Slf4j
public class TableWC<T extends TableRecord> implements Table {

    @Getter private SelenideElement root;
    private final By rowLocator;
    private final Class<T> rowClass;
    private ElementsCollection rowsRaw;
    @Getter protected final List<T> rows = new ArrayList<>();

    public TableWC(By rowLocator, Class<T> rowClass) {
        this.rowLocator = rowLocator;
        this.rowClass = rowClass;
    }

    public void refresh() {
        if (rowsRaw == null) rowsRaw = $$(rowLocator);
        rows.clear();
        try {
            for (SelenideElement row : rowsRaw) {
                rows.add(RowFactory.getRow(rowClass, row));
            }
        } catch (StaleElementReferenceException e) {
            log.warn("table is not completely refreshed, retrying");
            refresh();
        }
    }

    public void should(CollectionCondition... conditions) {
        Arrays.stream(conditions).forEach(condition -> rowsRaw = rowsRaw.should(condition));
        refresh();
    }

    public void filter(Condition... conditions) {
        Arrays.stream(conditions).forEach(condition -> rowsRaw = rowsRaw.filter(condition));
        refresh();
    }

    public T getFirst() {
        refresh();
        return rows.stream().findFirst().get();
    }

    @Override
    @Step("Selecting record '{id}'")
    public void selectRow(String id) {
        refresh();
        rows.stream().filter(row -> row.getId().equals(id)).findFirst().get().select();
    }

    @Override
    public void dump() {
        log.debug("visible records: " + rows.size());
        for (T row : rows) {
            row.dump();
        }
    }

    public TextTable logRecords(List<T> records) {
        List<Object[]> recordsData = new ArrayList<>();
        records.forEach(row -> recordsData.add(row.getRecordData()));
        Object[][] rd = new Object[recordsData.size()][];
        TextTable tt = new TextTable(getFirst().getColumns().toArray(new String[0]), recordsData.toArray(rd));
        tt.printTable();
        return tt;
    }
}
