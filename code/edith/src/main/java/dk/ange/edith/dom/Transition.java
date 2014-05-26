package dk.ange.edith.dom;

/**
 * Describe a transition in the parser used in Grouper
 */
public class Transition {

    private final int state;

    private final int pop;

    private final int occurrence;

    /**
     * @param state
     * @return A new Transition that will change state to <code>to</code>. Will not pop any groups, the occurrence
     *         number is 1.
     */
    public static Transition to(final int state) {
        return new Transition(state, 0, 1);
    }

    private Transition(final int state, final int pop, final int occurrence) {
        this.state = state;
        this.occurrence = occurrence;
        this.pop = pop;
    }

    /**
     * @param newPop
     * @return a new Transition that will is similar to the old but will pop newPop groups
     */
    public Transition pop(final int newPop) {
        return new Transition(state, newPop, occurrence);
    }

    /**
     * @param newOccurrence
     * @return a new Transition that will is similar to the old but with change occurrence number
     */
    public Transition occurrence(final int newOccurrence) {
        return new Transition(state, pop, newOccurrence);
    }

    /**
     * @return true if there should be created a new group when this transition is done
     */
    public boolean createNewGroup() {
        return state > 0;
    }

    /**
     * @return the group number to change to when this transition is done
     */
    public int groupNumber() {
        return state;
    }

    /**
     * @return the state to change to
     */
    public int getState() {
        return state;
    }

    /**
     * @return the number of groups to pop from the stack
     */
    public int getPop() {
        return pop;
    }

    /**
     * @return the occurrence number of the tag in the message format
     */
    public int getOccurrence() {
        return occurrence;
    }

}
