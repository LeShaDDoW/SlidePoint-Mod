package net.lstwo.slidepoint.apps;

import com.mrcrayfish.device.api.app.Application;
import com.mrcrayfish.device.api.app.Dialog;
import com.mrcrayfish.device.api.app.Icons;
import com.mrcrayfish.device.api.app.component.*;
import com.mrcrayfish.device.core.io.FileSystem;
import com.mrcrayfish.device.programs.system.layout.StandardLayout;
import net.lstwo.slidepoint.NBTLineBreakRemover;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicReference;

public class TextEditPlus extends Application {
    TextArea text = new TextArea(5, 25, 190, 120);
    boolean isEditing = false;
    @Override
    public void init(@Nullable NBTTagCompound nbtTagCompound) {
        setCurrentLayout(mainMenu());
    }

    private StandardLayout mainMenu() {
        StandardLayout mainMenu = new StandardLayout("Main Menu", 86, 91, this, null);
        mainMenu.setIcon(Icons.HOME);


        Button newButton = new Button(5, 25, "New", Icons.NEW_FILE);
        Button importButton = new Button(5, 46, "Import",  Icons.IMPORT);

        importButton.setSize(76, 20);
        newButton.setSize(76, 20);

        importButton.setToolTip("Import Text File", "Import a Text File to Edit!");
        newButton.setToolTip("New Text File", "Create a New Text File!");

        newButton.setClickListener((MouseX, MouseY, MouseButton) -> {
            setCurrentLayout(newMenu());
        });

        importButton.setClickListener((MouseX, MouseY, MouseButton) -> {
            Dialog.OpenFile dialog = new Dialog.OpenFile(this);
            dialog.setResponseHandler((b, file) -> {
                if(b) {
                    text.setText(file.getData().getString("text"));
                    setCurrentLayout(textEditor());
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

    private StandardLayout newMenu() {
        StandardLayout newMenu = new StandardLayout("Create New", 90, 80, this, mainMenu());

        Label nameText = new Label("Name:", 5, 25);
        TextField nameField = new TextField(5, 36, 80);
        Button createButton = new Button(5, 57, "Create", Icons.NEW_FILE);

        createButton.setSize(80, 20);
        createButton.setToolTip("Create Text File", "Create A New Text File!");

        createButton.setClickListener((i, i1, i2) -> {
            text.clear();
            isEditing = true;
            setCurrentLayout(textEditor());
        });

        newMenu.addComponent(nameText);
        newMenu.addComponent(nameField);
        newMenu.addComponent(createButton);

        return newMenu;
    }

    AtomicReference<String> filePath = new AtomicReference<>("bruh");

    private StandardLayout textEditor() {
        StandardLayout textEditor = new StandardLayout("Text Edit Plus Editor", 200, 150, this, null);
        textEditor.setIcon(Icons.FILE);

        Button saveAsButton = new Button(179, 2, Icons.SAVE_AS);
        Button home = new Button(158, 2, Icons.HOME);
        Button clear = new Button(137, 2, Icons.FORBIDDEN);



        saveAsButton.setToolTip("Save As", "Save As A New File!");
        home.setToolTip("Home", "Go Back To The Main Menu!");

        home.setClickListener((i, i1, i2) -> {
            if(text.getText() != null || text.getText().matches("")) {
                Dialog.Confirmation saveDialog = new Dialog.Confirmation("Do You want to go to the Main Menu? Unsaved Work Will be lost!");
                saveDialog.setTitle("Continue?");
                saveDialog.setPositiveText("Yes");
                saveDialog.setNegativeText("Cancel");
                saveDialog.setPositiveListener((i3, i11, i21) -> {
                    isEditing = false;
                    setCurrentLayout(mainMenu());
                    text.clear();
                });
                openDialog(saveDialog);
            }
        });

        saveAsButton.setClickListener((i, i1, i2) -> {
            SaveText();
        });

        clear.setClickListener((i, i1, i2) -> {
            Dialog.Confirmation dialog = new Dialog.Confirmation("Are you Sure You want to Clear?");
            dialog.setTitle("Clear Text?");
            dialog.setPositiveListener((i3 ,i4, i5) -> {
                text.clear();
            });
            openDialog(dialog);
        });

        textEditor.addComponent(home);
        textEditor.addComponent(saveAsButton);
        textEditor.addComponent(text);
        textEditor.addComponent(clear);

        return textEditor;
    }

    private void SaveText() {
        NBTTagCompound fileNBT = new NBTTagCompound();
        fileNBT.setString("text", text.getText());
        NBTLineBreakRemover.removeLineBreaks(fileNBT, "text");
        Dialog.SaveFile dialog = new Dialog.SaveFile(this, fileNBT);
        dialog.setFolder(FileSystem.DIR_HOME);
        dialog.setResponseHandler((b, _file) -> {
            if(b) {
                filePath.set(_file.getLocation());
                return true;
            } else {
                return false;
            }
        });
        openDialog(dialog);
    }

    @Override
    public void load(NBTTagCompound nbtTagCompound) {

    }

    @Override
    public void save(NBTTagCompound nbtTagCompound) {

    }
}
