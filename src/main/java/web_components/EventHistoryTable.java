package web_components;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class EventHistoryTable extends TableWC<HistoryTableRecord> {

    public EventHistoryTable(By rowLocator, Class<HistoryTableRecord> rowClass) {
        super(rowLocator, rowClass);
    }

    public List<HistoryTableRecord> filterLastYears(int years) {
        LocalDateTime currentTime = getFirst().getDateTime().minusYears(years);
        List<HistoryTableRecord> targetRecords = rows.stream()
                .filter(row -> row.getDateTime().isAfter(currentTime)).collect(Collectors.toList());
        targetRecords.forEach(row -> log.debug(row.getDateTime().toString()));
        return targetRecords;
    }

//    public void logRecords(List<HistoryTableRecord> records) {
//        List<Object[]> recordsData = new ArrayList<>();
//        records.forEach(row -> recordsData.add(row.getRecordData()));
//        Object[][] rd = new Object[recordsData.size()][];
//        TextTable tt = new TextTable(getFirst().getColumns().toArray(new String[0]), recordsData.toArray(rd));
//        tt.printTable();
//    }
}
