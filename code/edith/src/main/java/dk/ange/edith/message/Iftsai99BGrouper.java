package dk.ange.edith.message;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.ImmutableTable.Builder;

import dk.ange.edith.dom.Transition;

/**
 * The IFTSAI 99B standard
 */
public class Iftsai99BGrouper extends AbstractMessageGrouper {

    @Override
    protected ImmutableTable<String, Integer, Transition> stateTransitions() {
        final Builder<String, Integer, Transition> builder = new ImmutableTable.Builder<>();
        builder.put("RFF", 0, Transition.to(1));
        builder.put("RFF", 1, Transition.to(1).pop(1));
        builder.put("LOC", 0, Transition.to(2));
        builder.put("LOC", 1, Transition.to(2).pop(1));
        builder.put("LOC", 2, Transition.to(2).pop(1));
        builder.put("EQD", 0, Transition.to(3));
        builder.put("EQD", 1, Transition.to(3).pop(1));
        builder.put("EQD", 2, Transition.to(3).pop(1));
        builder.put("EQD", 3, Transition.to(3).pop(1));
        builder.put("TDT", 0, Transition.to(4));
        builder.put("TDT", 1, Transition.to(4).pop(1));
        builder.put("TDT", 2, Transition.to(4).pop(1));
        builder.put("TDT", 3, Transition.to(4).pop(1));
        builder.put("TDT", 4, Transition.to(4).pop(1));
        builder.put("TDT", 5, Transition.to(4).pop(2));
        builder.put("LOC", 4, Transition.to(5));
        builder.put("LOC", 5, Transition.to(5).pop(1));
        builder.put("NAD", 0, Transition.to(6));
        builder.put("NAD", 1, Transition.to(6).pop(1));
        builder.put("NAD", 2, Transition.to(6).pop(1));
        builder.put("NAD", 3, Transition.to(6).pop(1));
        builder.put("NAD", 4, Transition.to(6).pop(1));
        builder.put("NAD", 5, Transition.to(6).pop(2));
        builder.put("NAD", 6, Transition.to(6).pop(1));
        builder.put("NAD", 7, Transition.to(6).pop(2));
        builder.put("CTA", 6, Transition.to(7));
        builder.put("CTA", 7, Transition.to(7).pop(1));
        builder.put("GID", 0, Transition.to(8));
        builder.put("GID", 1, Transition.to(8).pop(1));
        builder.put("GID", 2, Transition.to(8).pop(1));
        builder.put("GID", 3, Transition.to(8).pop(1));
        builder.put("GID", 4, Transition.to(8).pop(1));
        builder.put("GID", 5, Transition.to(8).pop(2));
        builder.put("GID", 6, Transition.to(8).pop(1));
        builder.put("GID", 7, Transition.to(8).pop(2));
        builder.put("GID", 8, Transition.to(8).pop(1));
        builder.put("GDS", 8, Transition.to(9));
        builder.put("GDS", 9, Transition.to(9).pop(1));
        builder.put("MEA", 8, Transition.to(10));
        builder.put("MEA", 9, Transition.to(10).pop(1));
        builder.put("MEA", 10, Transition.to(10).pop(1));
        builder.put("DIM", 8, Transition.to(11));
        builder.put("DIM", 9, Transition.to(11).pop(1));
        builder.put("DIM", 10, Transition.to(11).pop(1));
        builder.put("DIM", 11, Transition.to(11).pop(1));
        builder.put("DGS", 8, Transition.to(12));
        builder.put("DGS", 9, Transition.to(12).pop(1));
        builder.put("DGS", 10, Transition.to(12).pop(1));
        builder.put("DGS", 11, Transition.to(12).pop(1));
        builder.put("DGS", 12, Transition.to(12).pop(1));
        return builder.build();
    }

}
