public class institutions {

    private String name;
    private boolean selected;

    public institutions(String name) {
        this.name = name;
        this.selected = false; // Default selection status is false
    }

    public String getName() {
        return name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;

    }
}
