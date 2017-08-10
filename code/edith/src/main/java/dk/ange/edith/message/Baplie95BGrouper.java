package dk.ange.edith.message;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.ImmutableTable.Builder;

import dk.ange.edith.dom.Transition;

/**
 * Implements grouping of Baplie based on D95B as defined in
 *
 * <pre>
 * SMDG User Manual ( Implementation Guide )
 * BAPLIE Bayplan Message
 * Version 2.1.1
 * UN/EDIFACT D95B
 * </pre>
 */
public class Baplie95BGrouper extends AbstractMessageGrouper {

    @Override
    protected ImmutableTable<String, Integer, Transition> stateTransitions() {
        final Builder<String, Integer, Transition> builder = new ImmutableTable.Builder<>();
        // SG0 : UNH-BGM-DTM-(RFF)-(NAD)-SG1-SG2-UNT
        // SG1 : TDT-LOC-DTM(state-10)-RFF-FTX (M1)
        // SG2 : LOC-GID-GDS-FTX-MEA-DIM-TMP-RNG-LOC(state -20)-RFF(state -21)-SG3-SG4 (C9999)
        // SG3 : EQD-EQA-NAD (C9)
        // SG4 : DGS-FTX (C999)
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
