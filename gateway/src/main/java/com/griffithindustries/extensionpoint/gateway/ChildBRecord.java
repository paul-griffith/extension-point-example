package com.griffithindustries.extensionpoint.gateway;

import com.inductiveautomation.ignition.gateway.localdb.persistence.IntField;
import com.inductiveautomation.ignition.gateway.localdb.persistence.LongField;
import com.inductiveautomation.ignition.gateway.localdb.persistence.PersistentRecord;
import com.inductiveautomation.ignition.gateway.localdb.persistence.RecordMeta;
import com.inductiveautomation.ignition.gateway.localdb.persistence.ReferenceField;
import com.inductiveautomation.ignition.gateway.model.GatewayContext;
import simpleorm.dataset.SFieldFlags;

public class ChildBRecord extends PersistentRecord {
    public static final RecordMeta<ChildBRecord> META = new RecordMeta<>(ChildBRecord.class, "extension_point_childB");

    public static final LongField ProfileId = new LongField(META, "ProfileId", SFieldFlags.SPRIMARY_KEY);
    public static final ReferenceField<BaseRecord> Profile =
        new ReferenceField<>(META, BaseRecord.META, "Profile", ProfileId);

    public static final IntField SpecificInt = new IntField(META, "intField", SFieldFlags.SMANDATORY);

    @Override
    public RecordMeta<?> getMeta() {
        return META;
    }

    public static class ChildBType extends AbstractExtensionType {
        public ChildBType() {
            super("B");
        }

        @Override
        public RecordMeta<? extends PersistentRecord> getSettingsRecordType() {
            return ChildBRecord.META;
        }

        @Override
        ChildBInstance createNewInstance(GatewayContext context, BaseRecord baseRecord) {
            ChildBRecord childRecord = findProfileSettingsRecord(context, baseRecord);
            return new ChildBInstance(baseRecord, childRecord);
        }
    }
}
