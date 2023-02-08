package net.lstwo.slidepoint.object;

public class SlideObject {
    private int type;
    private String name;

    // 0 = TEXT
    // 1 = LABEL
    // 2 = IMAGE
    // 3 = CANVAS

    public SlideObject(String name, int type) {
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }
}
