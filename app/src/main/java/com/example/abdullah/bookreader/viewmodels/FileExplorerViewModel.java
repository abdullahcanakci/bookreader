package com.example.abdullah.bookreader.viewmodels;

import android.content.Context;
import android.os.Environment;

import com.example.abdullah.bookreader.AppExecutors;
import com.example.abdullah.bookreader.data.Repository;
import com.example.abdullah.bookreader.data.models.FileModel;
import com.example.abdullah.bookreader.helpers.FileManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FileExplorerViewModel extends ViewModel {
    private final Repository repo;
    private Context mContext;
    private Stack<String> mStack = new Stack<>();
    public String mStartPath;
    private MutableLiveData<List<FileModel>>  mFileList;
    private FileManager mManager;
    public FileExplorerViewModel(Context context, Repository repo) {
        this.mContext = context;
        this.repo = repo;
        mStartPath = Environment.getExternalStorageDirectory().getPath();
        mFileList = new MutableLiveData<>();
        mManager = new FileManager();
    }

    public boolean goToDir(String dir){
        if(!mStack.isEmpty() && mStack.peek().equals(dir)){
           return false;
        }
        changeDirectory(dir);
        mStack.push(dir);
        return true;
    }

    /**
     * Goes back 1 level on the stack
     * @return true if back traversel is succeded
     */
    public boolean goBack(){
        if(mStack.isEmpty()){
           return false;
        }
        mStack.pop();
        if(mStack.isEmpty()){
            return false;
        }
        changeDirectory(mStack.peek());
        return true;
    }

    private void changeDirectory(String dir){
        AppExecutors.getInstance().diskIO().execute(() -> {
            List<String> files = mManager.getFilesInDir(dir);
            FileModel tempModel;
            List<FileModel> fileModels = new ArrayList<>();

            for (int i = 0; i < files.size(); i++) {
                File f = new File(files.get(i));
                tempModel = new FileModel(f.isFile());
                tempModel.setPath(f.getPath());
                tempModel.setTitle(f.getName());
                fileModels.add(tempModel);
            }
            mFileList.postValue(fileModels);
        });
    }

    public LiveData<List<FileModel>> getFileList() {
        return mFileList;
    }

    List<FileModel> getSelectedItems() {

        List<FileModel> selectedFiles = new ArrayList<>();
        for (FileModel model : mFileList.getValue()) {
            if (model.isChecked()) {
                selectedFiles.add(model);
            }
        }

        return selectedFiles;
    }

    public void pushSelectedItemsToRepository() {
        AppExecutors.getInstance().diskIO().execute(() -> {
            List<FileModel> files = getSelectedItems();
            if(!files.isEmpty())
                repo.insertFileModels(files);
        });
    }
}
