package formsdialogs.setparamsofobjectdialog;

import model.builder.Param;

/**
 * Created by roman-sosnovsky on 21.12.14.
 */
class ParamsTableEntry {
    private String name;
    private float value;
    private Param param;

    public ParamsTableEntry(String name, float value, Param param) {
        this.name = name;
        this.value = value;
        this.param = param;

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
