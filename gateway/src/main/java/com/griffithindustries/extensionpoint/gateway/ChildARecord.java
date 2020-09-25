package com.griffithindustries.extensionpoint.gateway;

import com.inductiveautomation.ignition.gateway.localdb.persistence.LongField;
import com.inductiveautomation.ignition.gateway.localdb.persistence.PersistentRecord;
import com.inductiveautomation.ignition.gateway.localdb.persistence.RecordMeta;
import com.inductiveautomation.ignition.gateway.localdb.persistence.ReferenceField;
import com.inductiveautomation.ignition.gateway.localdb.persistence.StringField;
import com.inductiveautomation.ignition.gateway.model.GatewayContext;
import simpleorm.dataset.SFieldFlags;

public class ChildARecord extends PersistentRecord {
    public static final RecordMeta<ChildARecord> META = new RecordMeta<>(ChildARecord.class, "extension_point_childA");

    public static final LongField ProfileId = new LongField(META, "ProfileId", SFieldFlags.SPRIMARY_KEY);
    public static final ReferenceField<BaseRecord> Profile =
        new ReferenceField<>(META, BaseRecord.META, "Profile", ProfileId);

    public static final StringField SpecificString = new StringField(META, "specificString", SFieldFlags.SMANDATORY);

    @Override
    public RecordMeta<?> getMeta() {
        return META;
    }

    public static class ChildAType extends AbstractExtensionType {
        public ChildAType() {
            super("A");
        }

        @Override
        public RecordMeta<? extends PersistentRecord> getSettingsRecordType() {
            return ChildARecord.META;
        }

        @Override
        ChildAInstance createNewInstance(GatewayContext context, BaseRecord baseRecord) {
            ChildARecord childRecord = findProfileSettingsRecord(context, baseRecord);
            return new ChildAInstance(baseRecord, childRecord);
        }
    }
}
