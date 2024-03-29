package com.example.abdullah.bookreader.data.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "books")
public class BookModel {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long mId;

    @ColumnInfo(name = "name")
    private String mName;

    /**
     * Date of publishing
     */
    @ColumnInfo(name = "date")
    private long mDate;

    /**
     * Last date of interaction by user.
     * New books are tagged by date of add
     */
    @ColumnInfo(name = "interaction_date")
    private long mInteractionDate;

    /**
     * Status flag for book
     */
    @ColumnInfo(name = "status")
    private int mStatus;

    @ColumnInfo(name = "file_path")
    private String mPath;

    @ColumnInfo(name = "read_count")
    private int mReadCount;

    @Ignore
    public BookModel() {
    }

    public BookModel(long mId, String mName) {
        this.mId = mId;
        this.mName = mName;
    }

    public long getId() {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public long getDate() {
        return mDate;
    }

    public void setDate(long date) {
        mDate = date;
    }

    public long getInteractionDate() {
        return mInteractionDate;
    }

    public void setInteractionDate(long interactionDate) {
        mInteractionDate = interactionDate;
    }

    public int getStatus() {
        return mStatus;
    }
    public String getStatusText(){
        return mStatus + "%";
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public int getReadCount() {
        return mReadCount;
    }

    public void setReadCount(int readCount) {
        mReadCount = readCount;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String Path) {
        this.mPath = Path;
    }
}
