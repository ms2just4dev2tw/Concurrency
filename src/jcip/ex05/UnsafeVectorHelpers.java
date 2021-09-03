package jcip.ex05;

import java.util.*;

/**
 * <h6>CodeList 5-1 UnsafeVectorHelpers</h6>
 * <i>Compound actions on a Vector that may produce confusing results</i>
 * <p>
 *
 * @author Brian Goetz and Tim Peierls
 */
public class UnsafeVectorHelpers {
	
    public static Object getLast(Vector<?> list) {
        int lastIndex = list.size() - 1;
        return list.get(lastIndex);
    }

    public static void deleteLast(Vector<?> list) {
        int lastIndex = list.size() - 1;
        list.remove(lastIndex);
    }
}
