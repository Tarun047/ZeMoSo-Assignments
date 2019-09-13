package com.tarun.TalkBuddy.daos;

import java.util.List;

public interface GenericDao<T,K>
{
    List<T> list();

    T findById(K key);

    T removeById(K key);

    T remove(T entry);

    T createOrUpdate(T entry);

}
