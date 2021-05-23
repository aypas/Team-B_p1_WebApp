package com.revature.p1.util.datastructs.queue;

import com.revature.p1.util.collection.Collection;

/**
 *
 * Description: Custom queue interface
 *
 * @param <T>
 */
public interface Queue<T> extends Collection<T> {
    T poll();
}
