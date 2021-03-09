package com.spring.api.utility;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @param <T> repository for the logging
 */
public class RepoLog<T extends JpaRepository<?, ?>> {

    private final T repo;
    private final int repoSizeBeforeRequest;

    public RepoLog(T repo) {
        this.repo = repo;
        this.repoSizeBeforeRequest = repo.findAll().size();
    }

    /**
     * @return message with the changed size of the repository from object creation to this point.
     */
    public String getChangedSize() {
        return repo.getClass().getName() + "-Table-Size: " + repoSizeBeforeRequest + " -> " + repo.findAll().size();
    }
}
