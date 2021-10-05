/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EduSys.dao;

import java.util.List;

/**
 *
 * @author admin
 */
public abstract class EduSysDAO<E, K> {
    public abstract void insert(E entity);
    public abstract void update(E entity);
    public abstract void delete(K key);
    protected abstract List<E> select(String sql, Object...agrs);
    public abstract List<E> selectAll();
    public abstract E findByID(K key);
}
