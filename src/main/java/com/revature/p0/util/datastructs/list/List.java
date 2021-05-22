package com.revature.p0.util.datastructs.list;

import com.revature.p0.util.collection.Collection;

/**
 *
 * Description: Custom List interface.
 *
 * @param <T>
 */

public interface List<T> extends Collection<T> {

    T get(int index);

    /*
        Interfaces
            - act as a contract for implementing classes (all concrete implementations must
              implement all abstract methods!)

            - do no have constructors

            - all fields declared within are implicitly public, static, and final

            - all method stubs declared within are implicitly public and abstract

            - methods with implementations are permitted in two ways: static and default methods
                + static methods cannot be overridden by implementing classes (but can be redeclared/shadowed)
                + default methods can be overridden by implementing classes (introduced in Java 8)
     */

}
