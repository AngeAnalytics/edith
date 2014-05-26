package dk.ange.edith.message;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.ImmutableTable.Builder;

import dk.ange.edith.dom.Transition;

/**
 * The BAPLIE 95B standard
 */
public class Baplie95BGrouper extends AbstractMessageGrouper {

    @Override
    protected ImmutableTable<String, Integer, Transition> stateTransitions() {
        final Builder<String, Integer, Transition> builder = new ImmutableTable.Builder<>();
        builder.put("TDT", 0, Transition.to(1));
        builder.put("TDT", -10, Transition.to(1).pop(1));
        builder.put("DTM", 1, Transition.to(-10));
        builder.put("LOC", -10, Transition.to(2).pop(1));
        builder.put("LOC", -20, Transition.to(-20).occurrence(2));
        builder.put("LOC", -21, Transition.to(2).pop(1));
        builder.put("LOC", 3, Transition.to(2).pop(2));
        builder.put("LOC", 4, Transition.to(2).pop(2));
        builder.put("MEA", 2, Transition.to(-20));
        builder.put("RFF", -20, Transition.to(-21));
        builder.put("EQD", -21, Transition.to(3));
        builder.put("EQD", 3, Transition.to(3).pop(1));
        builder.put("DGS", -21, Transition.to(4));
        builder.put("DGS", 3, Transition.to(4).pop(1));
        builder.put("DGS", 4, Transition.to(4).pop(1));
        return builder.build();
    }

}
