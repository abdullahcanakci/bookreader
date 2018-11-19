package com.example.abdullah.bookreader.helpers;

import com.example.abdullah.bookreader.data.models.BookModel;
import com.example.abdullah.bookreader.data.models.FileModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileSelectionHelper {
    /**
     * Converts provided FileModels to BookModels
     * @param models to be converted
     */

    public static List<BookModel> convertToBookModels(List<FileModel> models){
        List<BookModel> books = null;
        if(models != null && !models.isEmpty()){
            books = new ArrayList<>();
            for(FileModel model: models){
                if(!FileManager.validExtension(FileManager.getFileExtension(model.getPath()))){
                    continue;
                }
                BookModel b = new BookModel();
                b.setName(getName(model.getTitle()));
                b.setDate(System.currentTimeMillis());
                b.setPath(model.getPath());
                books.add(b);
            }
        }
        return books;
    }

    private static String getName(String fileName){
        int dot = fileName.lastIndexOf(".");
        return fileName.substring(0, dot);
    }
}
