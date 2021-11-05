package com.company.repository;

import java.util.List;

/**
 * Default ICrud Repo interface
 */
public interface ICrudRepository<T> {

    /**
     * Create a new object
     * @param obj object
     * @return void
     */
    T create(T obj);

    /**
     * Show the list of the objects
     * @return list
     */
    List<T> getAll();

    /**
     * Update a certain object
     * @param obj object
     * @return void
     */
    T update(T obj);

    /**
     * Delete a object
     * @param obj object
     */
    void delete(T obj);
}
