package formsdialogs.startupform;

/**
 * Created by roman-sosnovsky on 20.01.15.
 */
public class AvailableLevelListEntry {
    private String name;
    private String linkTolevel;
    private String description;

    public AvailableLevelListEntry(String name, String linkTolevel, String description) {
        this.name = name;
        this.linkTolevel = linkTolevel;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkTolevel() {
        return linkTolevel;
    }

    public void setLinkTolevel(String linkTolevel) {
        this.linkTolevel = linkTolevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
