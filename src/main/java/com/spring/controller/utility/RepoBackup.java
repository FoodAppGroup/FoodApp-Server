package com.spring.controller.utility;

import com.spring.database.backup.ExcelManagement;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.IOException;
import java.util.List;

/**
 * @param <P> Table Entity
 * @param <T> Repository of the Table Entity
 * @param <S> ExcelManagement of the Table Entity
 */
@AllArgsConstructor
public class RepoBackup<P, T extends JpaRepository<P, ?>, S extends ExcelManagement<P>> {

    private final T repo;
    private final S excel;

    /**
     * Saves all table rows to an excel file.
     *
     * @return log message from the database table with the changed size
     * @throws IOException conversion issues
     */
    public String saveBackup() throws IOException {
        List<P> list = repo.findAll();
        excel.writeTable(list);
        return list.size() + " rows saved";
    }

    /**
     * Loads all table rows from an excel file.
     *
     * @return log message from the database table with the changed size
     * @throws IOException conversion issues
     */
    public String loadBackup() throws IOException {
        RepoLog<T> repoLog = new RepoLog<>(repo);
        List<P> list = excel.readTable();
        repo.saveAll(list);
        return repoLog.getChangedSize();
    }
}
