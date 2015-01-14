package formsdialogs.setparamsofobjectdialog;

import model.builder.Param;

/**
 * Created by roman-sosnovsky on 21.12.14.
 */
class ParamsTableEntry {
    private String name;
    private float value;
    private Param param;
    private float minValue, maxValue;

    public ParamsTableEntry(String name,
                            float value,
                            float minValue,
                            float maxValue,
                            Param param) {
        this.name = name;
        this.value = value;
        this.param = param;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public float getMinValue() {
        return minValue;
    }

    public void setMinValue(float minValue) {
        this.minValue = minValue;
    }

    public float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Param getParam() {
        return param;
    }

    public void setParam(Param param) {
        this.param = param;
    }
}
