package org.stowbase.client.objects;

/**
 * Struct with UN number and an IMDG class
 */
public class DangerousGoods {

    /**
     * UN number
     */
    public String unNumber;

    /**
     * IMDG Code class
     */
    public String imdgClass;

    /**
     * Empty constructor
     */
    public DangerousGoods() {
        // 
    }

    /**
     * Simple constructor
     * 
     * @param unNumber
     * @param imdgClass
     */
    public DangerousGoods(String unNumber, String imdgClass) {
        this.unNumber = unNumber;
        this.imdgClass = imdgClass;
    }

    @Override
    public String toString() {
        return "DangerousGoods[" + unNumber + "," + imdgClass + "]";
    }

}
