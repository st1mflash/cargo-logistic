package com.ansekolesnikov.cargologistic.entity.pack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PackModelTest {
    @Test
    void testPackWidthForTypes1to5() {
        PackModel packModel1 = new PackModel(1);
        assertEquals(1, packModel1.getWidth());

        PackModel packModel2 = new PackModel(2);
        assertEquals(2, packModel2.getWidth());

        PackModel packModel3 = new PackModel(3);
        assertEquals(3, packModel3.getWidth());

        PackModel packModel4 = new PackModel(4);
        assertEquals(4, packModel4.getWidth());

        PackModel packModel5 = new PackModel(5);
        assertEquals(5, packModel5.getWidth());
    }

    @Test
    void testPackWidthForTypes6and9() {
        PackModel packModel6 = new PackModel(6);
        assertEquals(3, packModel6.getWidth());

        PackModel packModel9 = new PackModel(9);
        assertEquals(3, packModel9.getWidth());
    }

    @Test
    void testPackWidthForTypes7and8() {
        PackModel packModel7 = new PackModel(7);
        assertEquals(4, packModel7.getWidth());

        PackModel packModel8 = new PackModel(8);
        assertEquals(4, packModel8.getWidth());
    }

    @Test
    void testSetCarId() {
        PackModel packModel = new PackModel(1);
        packModel.setCarId(123);
        assertEquals(123, packModel.getCarId());
    }

    /*
    @Test
    void testGetType() {
        Pack pack = new Pack(2);
        assertEquals(2, pack.getCode());
    }

     */
}
