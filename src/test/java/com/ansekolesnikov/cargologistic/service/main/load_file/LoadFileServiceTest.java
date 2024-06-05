package com.ansekolesnikov.cargologistic.service.main.load_file;

import com.ansekolesnikov.cargologistic.service.LoadFileService;
//import com.ansekolesnikov.cargologistic.service.utils.LoadFileServiceUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest
@ExtendWith(MockitoExtension.class)
//@ContextConfiguration("src/main/java/com/ansekolesnikov/cargologistic/config/SpringAppConfig.java")
public class LoadFileServiceTest {
    private static final String PATH_IMPORT_PACKAGE = "src/main/resources/import/packages/";
    //@InjectMocks
    //@Autowired
    //private LoadFileService loadFileCargoService = new LoadFileService();

    //@Mock
    //@Autowired
    //private LoadFileServiceUtils serviceUtils = new LoadFileServiceUtils();

    //@Mock
    //@Autowired
    //private LoadCarServiceValidation serviceValidation = new LoadCarServiceValidation();

/*
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    */


    @Test
    public void testRunService_ValidParams() {
        /*
        // Arrange
        String params = "def.txt max 4";
        String expectedOutput = "Expected output";

        LocalFile localFile = new LocalFile(PATH_IMPORT_PACKAGE + "def.txt");
        List<Pack> importedPackList = new ArrayList<>();
        List<Car> loadedCarList = new ArrayList<>();

        LoadFileCargoServiceValidation serviceValidation = new LoadFileCargoServiceValidation(localFile,serviceUtils.getAlgorithmFromStringParams(params), serviceUtils.getCountCarsFromStringParams(params));

        System.out.println(serviceUtils.getFileNameFromStringParams(params));
        Mockito.when(serviceUtils.getFileNameFromStringParams(params)).thenReturn("def.txt");
        Mockito.when(serviceUtils.getAlgorithmFromStringParams(params)).thenReturn("max");
        Mockito.when(serviceUtils.getCountCarsFromStringParams(params)).thenReturn(4);
        Mockito.when(serviceUtils.getListPacksFromFile(localFile)).thenReturn(importedPackList);
        Mockito.when(serviceUtils.loadCars(importedPackList, 2, "testAlgorithm")).thenReturn(loadedCarList);
        Mockito.when(serviceValidation.isValid()).thenReturn(true);
        Mockito.when(serviceValidation.isValidCountCars(loadedCarList)).thenReturn(true);
        Mockito.when(serviceUtils.getCarsInfo(loadedCarList)).thenReturn(expectedOutput);

        // Act
        String result = loadFileCargoService.runService(params);

        // Assert
        assertEquals(expectedOutput, result);
        */
    }

    @Test
    public void testRunService_InvalidParams() {
        // Arrange
        //String params = "sampleParams";
        String params = "def.txt max 4";

        //Mockito.when(serviceValidation.isValid()).thenReturn(false);
        //Mockito.when(serviceValidation.getUserErrorMessage()).thenReturn("Invalid params");

        // Act
        //String result = loadCargoService.runService(params);

        // Assert
        //assertEquals("Invalid params", result);
    }
}