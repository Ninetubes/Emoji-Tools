/*
 * Copyright (C) 2015 - 2016 Mitch Talmadge (https://mitchtalmadge.com/)
 * Emoji Tools helps users and developers of Android, iOS, and OS X extract, modify, and repackage Emoji fonts.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.mitchtalmadge.emojitools.gui.tabcontrollers;

import com.mitchtalmadge.emojitools.EmojiTools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import com.mitchtalmadge.emojitools.EmojiTools;
import com.mitchtalmadge.emojitools.operations.Operation;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class TabController implements Initializable {

    @FXML
    Label filePathTitleLabel;

    @FXML
    TextField filePathField;

    @FXML
    Button browseButton;

    @FXML
    Button openRootDirectoryButton;

    @FXML
    Button startButton;

    Operation currentOperation;
    boolean operationsCancelled;
    File selectedFile;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTab();
    }

    abstract void initializeTab();

    @FXML
    void onBrowseButtonFired(ActionEvent actionEvent) {
        FileChooser.ExtensionFilter extensionFilter = getFileChooserExtensionFilter();
        File chooserFile;
        if (extensionFilter != null) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle(filePathTitleLabel.getText());
            fileChooser.getExtensionFilters().add(extensionFilter);
            fileChooser.setInitialDirectory(EmojiTools.getRootDirectory());
            chooserFile = fileChooser.showOpenDialog(browseButton.getScene().getWindow());
        } else {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle(filePathTitleLabel.getText());
            directoryChooser.setInitialDirectory(EmojiTools.getRootDirectory());
            chooserFile = directoryChooser.showDialog(browseButton.getScene().getWindow());
        }

        if (chooserFile != null) {
            if (validateSelectedFile(chooserFile)) {
                this.selectedFile = chooserFile;
                this.filePathField.setText(selectedFile.getName());
            }
        }
        validateStartButton();
    }

    protected abstract void validateStartButton();

    protected abstract FileChooser.ExtensionFilter getFileChooserExtensionFilter();

    protected abstract boolean validateSelectedFile(File file);

    @FXML
    void onOpenRootDirectoryButtonFired(ActionEvent actionEvent) {
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(EmojiTools.getRootDirectory());
        } catch (IOException e) {
            EmojiTools.submitError(e);
        }
    }

    @FXML
    void onStartButtonFired(ActionEvent actionEvent) {
        //Make sure file still exists
        if(selectedFile == null || !selectedFile.exists())
        {
            selectedFile = null;
            filePathField.clear();
            validateStartButton();
            return;
        }

        startOperations();
    }

    abstract void startOperations();
}
