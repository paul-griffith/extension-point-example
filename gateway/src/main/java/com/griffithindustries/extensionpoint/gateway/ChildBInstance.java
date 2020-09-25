package com.griffithindustries.extensionpoint.gateway;

public class ChildBInstance extends Extendable {
    private final String name;
    private final Integer integer;

    public ChildBInstance(BaseRecord baseRecord, ChildBRecord childBRecord) {
        this.name = baseRecord.getString(BaseRecord.Name);
        this.integer = childBRecord.getInt(ChildBRecord.SpecificInt);
    }
}
