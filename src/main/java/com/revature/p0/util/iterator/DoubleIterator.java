package com.revature.p0.util.iterator;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/1/2021
 * Time: 2:18 PM
 * Description: Interface extending the regular iterator and adds
 *              backwards iteration capabilities.
 */
public interface DoubleIterator<T> extends Iterator<T> {

    boolean hasPrev();
    T prev();

}
