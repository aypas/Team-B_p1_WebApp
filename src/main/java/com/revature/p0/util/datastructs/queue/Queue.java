package com.revature.p0.util.datastructs.queue;

import com.revature.p0.util.collection.Collection;

/**
 *
 * Description: Custom queue interface
 *
 * @param <T>
 */
public interface Queue<T> extends Collection<T> {
    T poll();
}
