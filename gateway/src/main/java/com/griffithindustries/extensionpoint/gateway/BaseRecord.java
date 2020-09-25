package com.griffithindustries.extensionpoint.gateway;

import com.inductiveautomation.ignition.gateway.localdb.persistence.Category;
import com.inductiveautomation.ignition.gateway.localdb.persistence.IdentityField;
import com.inductiveautomation.ignition.gateway.localdb.persistence.PersistentRecord;
import com.inductiveautomation.ignition.gateway.localdb.persistence.RecordMeta;
import com.inductiveautomation.ignition.gateway.localdb.persistence.StringField;
import simpleorm.dataset.SFieldFlags;

public class BaseRecord extends PersistentRecord {
    public static final RecordMeta<BaseRecord> META = new RecordMeta<>(BaseRecord.class, "extensionPointBase").setNounKey("extensionPoint").setNounPluralKey("extensionPoints");
    public static final IdentityField Id = new IdentityField(META);
    public static final StringField Type = new StringField(META, "Type", SFieldFlags.SMANDATORY, SFieldFlags.SDESCRIPTIVE);
    public static final StringField Name = new StringField(META, "Name", SFieldFlags.SMANDATORY, SFieldFlags.SDESCRIPTIVE);

    static final Category Main = new Category("Main", 1000).include(Id, Name);

    static {
        Type.getFormMeta().setVisible(false);
    }

    @Override
    public RecordMeta<?> getMeta() {
        return META;
    }

    public String getType() {
        return getString(Type);
    }
}
