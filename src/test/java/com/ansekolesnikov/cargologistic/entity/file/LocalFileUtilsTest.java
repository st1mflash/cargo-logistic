package com.ansekolesnikov.cargologistic.entity.file;

import com.ansekolesnikov.cargologistic.entity.LocalFile;
import com.ansekolesnikov.cargologistic.entity.utils.LocalFileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalFileUtilsTest {
    @Test
    public void testGetFullAddress() {
        LocalFile localFile = new LocalFile("src/test/resources/import/packages/test_packs.txt");
        LocalFileUtils localFileUtils = new LocalFileUtils();

        String expected = "src/test/resources/import/packages/test_packs.txt";
        String actual = localFileUtils.calculateFilePathNameFormat(localFile);

        assertEquals(expected, actual);
    }

    @Test
    public void testGetListJSONCars() {
        LocalFile localFile = new LocalFile("src/test/resources/import/car/test_car.json");
        LocalFileUtils localFileUtils = new LocalFileUtils();

        try {
            List<JSONObject> expectedList = List.of(
                    new JSONObject("{\"width\": \"6\", \"id\": \"952472\", \"cargo\": \"555551999666999666999333100000000000\", \"height\": \"6\"}")
            );
            List<JSONObject> actualList = localFileUtils.importListJsonCars(localFile);

            assertEquals(expectedList.size(), actualList.size());
            for (int i = 0; i < expectedList.size(); i++) {
                assertEquals(expectedList.get(i).toString(), actualList.get(i).toString());
            }
        } catch (JSONException ignored) {}
    }
}
