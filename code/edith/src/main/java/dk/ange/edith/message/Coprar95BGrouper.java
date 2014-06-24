package dk.ange.edith.message;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.ImmutableTable.Builder;

import dk.ange.edith.dom.Transition;

/**
 * SMDG
 * User Manual ( Implementation Guide ) UN/EDIFACT MESSAGE
 * COPRAR (Container discharge/loading order)
 * Version 1.2
 * D95B
 */
public class Coprar95BGrouper extends AbstractMessageGrouper {

    @Override
    protected ImmutableTable<String, Integer, Transition> stateTransitions() {
        final Builder<String, Integer, Transition> builder = new ImmutableTable.Builder<>();
        // Segment group 0: UNH-BGM-FTX-SG1-SG2-SG3-NAD-CNT-UNT
        // closed by mandatory CNT - UNT
        // Segment Group 1: TDT-RFF-LOC-DTM-FTX (M1) - mandatory TDT
        builder.put("TDT", 0, Transition.to(1));
        // Segment Group 2: NAD-CTA (M9) - mandatory NAD
        builder.put("NAD", 1, Transition.to(2).pop(1));
        builder.put("NAD", 2, Transition.to(2).pop(1));
        // Segment Group 3: EQD-RFF-EQN-TMD-DTM-LOC-MEA-DIM-TMP-RNG-SEL-FTX-DGS-EQA-SG4-NAD (M9999) - mandatory EQD
        builder.put("EQD", 2, Transition.to(3).pop(1));
        builder.put("EQD", 3, Transition.to(3).pop(1));
        builder.put("EQD", 4, Transition.to(3).pop(2));
        // Segment Group 4: TDT-RFF-LOC-DTM (C1) - mandatory TDT
        builder.put("TDT", 3, Transition.to(4));
        // Segment Group 3: EQD-RFF-EQN-TMD-DTM-LOC-MEA-DIM-TMP-RNG-SEL-FTX-DGS-EQA-SG4-NAD (M9999) - conditional NAD
        builder.put("NAD", 4, Transition.to(3).pop(1));
        // Segment group 0: UNH-BGM-FTX-SG1-SG2-SG3-CNT-UNT (M1) - mandatory CNT
        builder.put("CNT", 3, Transition.to(0).pop(1));
        builder.put("CNT", 4, Transition.to(0).pop(2));
        // Segment group 0: UNH-BGM-FTX-SG1-SG2-SG3-CNT-UNT (M1) - mandatory UNT
        builder.put("UNT", 0, Transition.to(0).pop(1));
        return builder.build();
    }

}
