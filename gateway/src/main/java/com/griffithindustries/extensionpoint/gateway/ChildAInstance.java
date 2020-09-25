package com.griffithindustries.extensionpoint.gateway;

public class ChildAInstance extends Extendable {
    private final String name;
    private final String string;

    public ChildAInstance(BaseRecord baseRecord, ChildARecord childARecord) {
        this.name = baseRecord.getString(BaseRecord.Name);
        this.string = childARecord.getString(ChildARecord.SpecificString);
    }
}
