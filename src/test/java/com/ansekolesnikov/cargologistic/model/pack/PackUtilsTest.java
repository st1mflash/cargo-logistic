package com.ansekolesnikov.cargologistic.model.pack;

import com.ansekolesnikov.cargologistic.model.pack.utils.PackUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PackUtilsTest {
    @Test
    void testCalcPackageWidthByType() {
        PackUtils packUtils = new PackUtils();

        assertEquals(1, packUtils.calcPackageWidthByType(1));
        assertEquals(2, packUtils.calcPackageWidthByType(2));
        assertEquals(3, packUtils.calcPackageWidthByType(3));
        assertEquals(4, packUtils.calcPackageWidthByType(4));
        assertEquals(5, packUtils.calcPackageWidthByType(5));
        assertEquals(3, packUtils.calcPackageWidthByType(6));
        assertEquals(4, packUtils.calcPackageWidthByType(7));
        assertEquals(3, packUtils.calcPackageWidthByType(9));
        assertEquals(0, packUtils.calcPackageWidthByType(10));
    }
}
