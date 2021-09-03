package jcip.ex05;

import java.util.*;

/**
 * <h6>CodeList 5-2 SafeVectorHelpers</h6>
 * <i>Compound actions on Vector using client-side locking</i>
 * <p>
 *
 * @author Brian Goetz and Tim Peierls
 */
public class SafeVectorHelpers {
	
    public static Object getLast(Vector<?> list) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            return list.get(lastIndex);
        }
    }

    public static void deleteLast(Vector<?> list) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            list.remove(lastIndex);
        }
    }
}
