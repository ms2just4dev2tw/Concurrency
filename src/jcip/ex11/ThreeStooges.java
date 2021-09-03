package jcip.ex11;

import java.util.*;

import net.jcip.annotations.*;

/**
 * <h6>CodeList 11-3 ThreeStooges</h6>
 * <i>Immutable class built out of mutable underlying objects,
 * demonstration of candidate for lock elision</i>
 * <p>
 *
 * @author Brian Goetz and Tim Peierls
 */
@Immutable
 public final class ThreeStooges {
    private final Set<String> stooges = new HashSet<String>();

    public ThreeStooges() {
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }

    public boolean isStooge(String name) {
        return stooges.contains(name);
    }

    public String getStoogeNames() {
        List<String> stooges = new Vector<String>();
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
        return stooges.toString();
    }
}
