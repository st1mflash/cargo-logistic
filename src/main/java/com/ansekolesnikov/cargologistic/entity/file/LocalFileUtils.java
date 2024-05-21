package com.ansekolesnikov.cargologistic.entity.file;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LocalFileUtils {
    public String calculateFilePathNameFormat(LocalFile localFile) {
        return localFile.getPath() + localFile.getName() + localFile.getFormat();
    }
    public List<JSONObject> importListJsonCars(LocalFile localFile) {
        String content = localFile.getContent();
        List<JSONObject> listJSONObj = new ArrayList<>();
        String[] arrClearContent = content
                .replaceAll("[{\\[\\]]", "")
                .split("},");
        for (String s : arrClearContent) {
            listJSONObj.add(new JSONObject("{" + s + "}"));
        }
        return listJSONObj;
    }
}
