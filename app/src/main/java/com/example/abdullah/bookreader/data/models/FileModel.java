package com.example.abdullah.bookreader.data.models;

import android.view.View;

import com.example.abdullah.bookreader.R;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * This class will be used for holding file info by {@link com.example.abdullah.bookreader.helpers.FileManager} and  {@link com.example.abdullah.bookreader.fragments.FileExplorerFragment}
 * This entity will be used when the FileExplorerFragment wants to send a file info to the listeners.
 * Whenever a list is appended to the db a event will be announced to the listener so they can do the right thing.
 * Whenever a info put into the db and that info is computed the table must be dropped.
 */

@Entity(tableName = "fileModels")
public class FileModel {
    @Ignore
    private static int FILE_ICON = R.drawable.file;
    @Ignore
    private static int FOLDER_ICON = R.drawable.folder;

    //
    @Ignore
    private boolean mIsFile;
    @Ignore
    private String mSubTitle;
    @Ignore
    private int mIcon;
    @Ignore
    private boolean isChecked;
    @Ignore
    private int checkboxVisibility = View.INVISIBLE;

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String mTitle;
    private String mPath;

    public FileModel(long id, String mTitle, String mPath) {
        this.id = id;
        this.mTitle = mTitle;
        this.mPath = mPath;
    }

    @Ignore
    public FileModel(boolean isFile) {
        this.mIsFile = isFile;
        if (isFile)
            checkboxVisibility = View.VISIBLE;
        mIcon = mIsFile ? FILE_ICON : FOLDER_ICON;
    }

    public boolean isFile() {
        return mIsFile;
    }

    public void setFile(boolean file) {
        mIsFile = file;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getSubTitle() {
        return mSubTitle;
    }

    public void setSubTitle(String subTitle) {
        mSubTitle = subTitle;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getIcon() {
        return mIcon;
    }

    public void setPath(String path) {
        this.mPath = path;
    }

    public String getPath() {
        return mPath;
    }

    public int getCheckboxVisibility() {
        return checkboxVisibility;
    }

    public void setCheckboxVisibility(int checkboxVisibility) {
        this.checkboxVisibility = checkboxVisibility;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
