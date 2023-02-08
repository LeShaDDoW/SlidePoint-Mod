package net.lstwo.slidepoint.apps;

import com.mrcrayfish.device.api.app.*;
import com.mrcrayfish.device.api.app.Component;
import com.mrcrayfish.device.api.app.Dialog;
import com.mrcrayfish.device.api.app.component.*;
import com.mrcrayfish.device.api.app.component.Button;
import com.mrcrayfish.device.core.Laptop;
import com.mrcrayfish.device.object.Tool;
import com.mrcrayfish.device.programs.system.layout.StandardLayout;
import net.lstwo.slidepoint.object.SlideObject;
import net.minecraft.client.gui.Gui;
import net.minecraft.nbt.NBTTagCompound;
import com.mrcrayfish.device.object.Canvas;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SlidePoint extends Application {



    public Tool noneTool = new Tool() {
        @Override
        public void handleClick(Canvas canvas, int i, int i1) {

        }

        @Override
        public void handleRelease(Canvas canvas, int i, int i1) {

        }

        @Override
        public void handleDrag(Canvas canvas, int i, int i1) {

        }
    };

    @Override
    public void init(@Nullable NBTTagCompound nbtTagCompound) {
        setCurrentLayout(MainMenu());
    }

    java.util.List<Component> configComponents = new List<Component>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<Component> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(Component component) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Component> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends Component> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public Component get(int index) {
            return null;
        }

        @Override
        public Component set(int index, Component element) {
            return null;
        }

        @Override
        public void add(int index, Component element) {

        }

        @Override
        public Component remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<Component> listIterator() {
            return null;
        }

        @Override
        public ListIterator<Component> listIterator(int index) {
            return null;
        }

        @Override
        public List<Component> subList(int fromIndex, int toIndex) {
            return null;
        }
    };
    StandardLayout layout = new StandardLayout("", 362, 240, this, null);

    private StandardLayout MainMenu() {
        StandardLayout mainMenu = new StandardLayout("Main Menu", 86, 91, this, null);
        mainMenu.setIcon(Icons.HOME);


        Button newButton = new Button(5, 25, "New", Icons.NEW_FILE);
        Button importButton = new Button(5, 46, "Import",  Icons.IMPORT);

        importButton.setSize(76, 20);
        newButton.setSize(76, 20);

        importButton.setToolTip("Import Text File", "Import a Text File to Edit!");
        newButton.setToolTip("New Text File", "Create a New Text File!");

        newButton.setClickListener((MouseX, MouseY, MouseButton) -> {
            setCurrentLayout(editor());
        });

        importButton.setClickListener((MouseX, MouseY, MouseButton) -> {
            Dialog.OpenFile dialog = new Dialog.OpenFile(this);
            dialog.setResponseHandler((b, file) -> {
                if(b) {
                    return true;
                } else {
                    return false;
                }
            });
            openDialog(dialog);
        });


        mainMenu.addComponent(newButton);
        mainMenu.addComponent(importButton);

        return mainMenu;
    }

    private StandardLayout editor() {
        // BACKGROUND COLORS
        layout.setBackground((gui, mc, x, y, width, height, mouseX, mouseY, windowActive) -> {
            Color temp = new Color(Laptop.getSystem().getSettings().getColorScheme().getBackgroundColor());
            Gui.drawRect(x, y + 21, x + 100, y + 164, temp.darker().getRGB());
            Gui.drawRect(x + 100, y + 21, x + 101, y + 164, temp.darker().darker().getRGB());

            Gui.drawRect(x + 139, y + 39, x + 326, y +  141, temp.brighter().brighter().getRGB());
            Gui.drawRect(x + 140, y + 40, x + 325, y +  140, temp.brighter().brighter().brighter().getRGB());
        });

        // SLIDE LIST
        ItemList<SlideObject> slideList = new ItemList<>(2, 22, 79, 4);

        Button renameSlide = new Button(83, 23, Icons.RENAME);
        renameSlide.setToolTip("Rename", "Rename the selected Object");

        Button deleteSlide = new Button(83, 43, Icons.TRASH);
        deleteSlide.setToolTip("Delete", "Delete the selected Object");

        Button swapSlide = new Button(83, 63, Icons.EDIT);
        swapSlide.setToolTip("Swap Configuration", "Swap between Slide Config and Object Config");

        // ITEM LIST
        ItemList<SlideObject> itemList = new ItemList<>(2, 81, 79, 4);

        Button renameObject = new Button(83, 83, Icons.RENAME);
        renameObject.setToolTip("Rename", "Rename the selected Object");

        Button deleteObject = new Button(83, 102, Icons.TRASH);
        deleteObject.setToolTip("Delete", "Delete the selected Object");

        Button swapConfig = new Button(83, 121, Icons.EDIT);
        swapConfig.setToolTip("Swap Configuration", "Swap between Slide Config and Object Config");



        // ADDING OBJECTS
        topBar(itemList, slideList);
        layout.addComponent(swapConfig);
        layout.addComponent(deleteObject);
        layout.addComponent(renameObject);
        layout.addComponent(itemList);
        layout.addComponent(swapSlide);
        layout.addComponent(deleteSlide);
        layout.addComponent(renameSlide);
        layout.addComponent(slideList);

        return layout;
    }

    void topBar(ItemList<SlideObject> itemList, ItemList slideList) {
        // BUTTONS
        Button textBtn = new Button(2, 2, "Text", Icons.RENAME);
        textBtn.setToolTip("Add Text", "Add a Multiline Text Object");
        textBtn.setSize(44, 16);

        Button labelBtn = new Button(50, 2, "Label", Alphabet.UPPERCASE_T );
        labelBtn.setToolTip("Add label", "Add a Label / Title (One lined)");
        labelBtn.setSize(44, 16);

        Button imageBtn = new Button(98, 2, "Image", Icons.PICTURE);
        imageBtn.setToolTip("Add Image", "Add an Image From URL");
        imageBtn.setSize(46, 16);

        Button canvasBtn = new Button(148, 2, "Canvas", Icons.EDIT);
        canvasBtn.setToolTip("Add Canvas", "Add a Pixel Painter Canvas");
        canvasBtn.setSize(56, 16);

        Button slideBtn = new Button(206, 2, "New Slide", Icons.PLUS);
        slideBtn.setToolTip("Add Slide", "Add a new Slide");
        slideBtn.setSize(69, 16);

        // CLICK EVENTS
        textBtn.setClickListener((i, i1, i2) -> {
            itemList.addItem(new SlideObject("Text", 0));
        });

        labelBtn.setClickListener((i, i1, i2) -> {
            itemList.addItem(new SlideObject("Label",1));
        });

        imageBtn.setClickListener((i, i1, i2) -> {
            itemList.addItem(new SlideObject("Image",2));
        });

        canvasBtn.setClickListener((i, i1, i2) -> {
            itemList.addItem(new SlideObject("Canvas", 3));
        });

        slideBtn.setClickListener((i, i1, i2) -> {

        });

        // ADDING OBJECTS
        layout.addComponent(slideBtn);
        layout.addComponent(canvasBtn);
        layout.addComponent(textBtn);
        layout.addComponent(labelBtn);
        layout.addComponent(imageBtn);
    }


    @Override
    public void load(NBTTagCompound nbtTagCompound) {

    }

    @Override
    public void save(NBTTagCompound nbtTagCompound) {

    }
}
