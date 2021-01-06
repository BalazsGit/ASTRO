package config;

import burst.kit.entity.BurstAddress;

public interface PropertyService {
    boolean getBoolean(Prop<Boolean> prop);
    int getInt(Prop<Integer> prop);
    long getLong(Prop<Long> prop);
    float getFloat(Prop<Float> prop);
    String getString(Prop<String> prop);
    String[] getStringList(Prop<String> prop);
    BurstAddress getBurstAddress(Prop<BurstAddress> prop);

    void setProperty(String key, String value);

    void reload(String fileName);
}
