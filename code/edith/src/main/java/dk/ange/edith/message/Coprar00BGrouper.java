package dk.ange.edith.message;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.ImmutableTable.Builder;

import dk.ange.edith.dom.Transition;

/**
 * SMDG
 * User Manual ( Implementation Guide ) UN/EDIFACT MESSAGE
 * COPRAR (Container discharge/loading order)
 * Version 2.0
 * D00B
 */
public class Coprar00BGrouper extends AbstractMessageGrouper {

    @Override
    protected ImmutableTable<String, Integer, Transition> stateTransitions() {
        final Builder<String, Integer, Transition> builder = new ImmutableTable.Builder<>();
        // Segment group 0: UNH - BGM -DTM - SG2 - SG4 - SG6 - CNT - UNT
        // closed by mandatory UNT
        // Segment Group 1: RFF
        builder.put("RFF", 0, Transition.to(1));
        builder.put("RFF", 1, Transition.to(1).pop(1));
        // Segment Group 2: TDT-RFF-SG3
        builder.put("TDT", 0, Transition.to(2));
        builder.put("TDT", 1, Transition.to(2).pop(1));
        // Segment Group 3: LOC-DTM
        builder.put("LOC", 2, Transition.to(3));
        builder.put("LOC", 3, Transition.to(3).pop(1));
        // Segment Group 4: NAD-SG5
        builder.put("NAD", 2, Transition.to(4).pop(1));
        builder.put("NAD", 3, Transition.to(4).pop(2));
        builder.put("NAD", 4, Transition.to(4).pop(1));
        builder.put("NAD", 5, Transition.to(4).pop(2));
        // Segment Group 5: CTA-COM
        builder.put("CTA", 4, Transition.to(5));
        builder.put("CTA", 5, Transition.to(5).pop(1));
        // Segment Group 6: EQD-RFF-EQN-TMD-DTM-LOC-MEA-DIM-SG7-SEL-FTX-SG8-EQA-HAN-SG10-NAD
        builder.put("EQD", 4, Transition.to(6).pop(1));
        builder.put("EQD", 5, Transition.to(6).pop(2));
        builder.put("EQD", 6, Transition.to(6).pop(1));
        builder.put("EQD", 7, Transition.to(6).pop(2));
        builder.put("EQD", 8, Transition.to(6).pop(2));
        builder.put("EQD", 9, Transition.to(6).pop(3));
        builder.put("EQD", 10, Transition.to(6).pop(2));
        builder.put("EQD", 11, Transition.to(6).pop(3));
        // Segment Group 7: TMP-RNG
        builder.put("TMP", 6, Transition.to(7));
        builder.put("TMP", 7, Transition.to(7).pop(1));
        // Segment Group 6: EQD-RFF-EQN-TMD-DTM-LOC-MEA-DIM-SG7-SEL-FTX-SG8-EQA-HAN-SG10-NAD
        builder.put("SEL", 7, Transition.to(6).pop(1));
        builder.put("FTX", 7, Transition.to(6).pop(1));
        // Segment Group 8: DGS-FTX-SG9
        builder.put("DGS", 6, Transition.to(8));
        builder.put("DGS", 7, Transition.to(8).pop(1));
        builder.put("DGS", 8, Transition.to(8).pop(1));
        builder.put("DGS", 9, Transition.to(8).pop(2));
        // Segment Group 9: CTA-COM
        builder.put("CTA", 8, Transition.to(9));
        builder.put("CTA", 9, Transition.to(9).pop(1));
        // Segment Group 6: EQD-RFF-EQN-TMD-DTM-LOC-MEA-DIM-SG7-SEL-FTX-SG8-EQA-HAN-SG10-NAD
        builder.put("EQA", 7, Transition.to(6).pop(1));
        builder.put("EQA", 8, Transition.to(6).pop(1));
        builder.put("EQA", 9, Transition.to(6).pop(2));
        builder.put("HAN", 7, Transition.to(6).pop(1));
        builder.put("HAN", 8, Transition.to(6).pop(1));
        builder.put("HAN", 9, Transition.to(6).pop(2));
        // Segment Group 10: TDT-DTM-RFF-SG11
        builder.put("TDT", 6, Transition.to(10));
        builder.put("TDT", 7, Transition.to(10).pop(1));
        builder.put("TDT", 8, Transition.to(10).pop(1));
        builder.put("TDT", 9, Transition.to(10).pop(2));
        // Segment Group 11: LOC
        builder.put("LOC", 10, Transition.to(11));
        // Segment Group 6: EQD-RFF-EQN-TMD-DTM-LOC-MEA-DIM-SG7-SEL-FTX-SG8-EQA-HAN-SG10-NAD
        builder.put("NAD", 7, Transition.to(6).pop(1));
        builder.put("NAD", 8, Transition.to(6).pop(1));
        builder.put("NAD", 9, Transition.to(6).pop(2));
        builder.put("NAD", 10, Transition.to(6).pop(1));
        builder.put("NAD", 11, Transition.to(6).pop(2));
        // Segment group 0: UNH - BGM -DTM - SG2 - SG4 - SG6 - CNT - UNT
        builder.put("CNT", 6, Transition.to(0).pop(1));
        builder.put("CNT", 7, Transition.to(0).pop(2));
        builder.put("CNT", 8, Transition.to(0).pop(2));
        builder.put("CNT", 9, Transition.to(0).pop(3));
        builder.put("CNT", 10, Transition.to(0).pop(2));
        builder.put("CNT", 11, Transition.to(0).pop(3));
        // Segment group 0: UNH - BGM -DTM - SG2 - SG4 - SG6 - CNT - UNT - closing all groups including group 0 by mandatory UNT
        builder.put("UNT", 0, Transition.to(0).pop(1));
        builder.put("UNT", 6, Transition.to(0).pop(2));
        builder.put("UNT", 7, Transition.to(0).pop(3));
        builder.put("UNT", 8, Transition.to(0).pop(3));
        builder.put("UNT", 9, Transition.to(0).pop(4));
        builder.put("UNT", 10, Transition.to(0).pop(3));
        builder.put("UNT", 11, Transition.to(0).pop(4));
        return builder.build();
    }

}
