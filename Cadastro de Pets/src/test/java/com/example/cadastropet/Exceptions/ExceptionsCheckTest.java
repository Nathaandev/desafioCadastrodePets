package com.example.cadastropet.Exceptions;

import com.example.cadastropet.Enum.CatOrDog;
import com.example.cadastropet.Enum.MascOrFem;
import com.example.cadastropet.Model.CadastroModel;
import com.example.cadastropet.Repository.CadastroRepository;
import com.example.cadastropet.Service.CadastroService;
import com.example.cadastropet.dtos.CadastroRecordDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

import static com.example.cadastropet.Enum.CatOrDog.Cat;
import static com.example.cadastropet.Enum.CatOrDog.Dog;
import static com.example.cadastropet.Enum.MascOrFem.Fem;
import static com.example.cadastropet.Enum.MascOrFem.Masc;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ExceptionsCheckTest {

    @BeforeEach
    void setUp() {
        exceptionsCheck = new ExceptionsCheck();
        cadastroModel = new CadastroModel(7L, "Tralala", "32", "14", "Nathan", "Carlos", "12", "Rio de Janeiro", "Estrada do Mendanha", Dog, Masc);
        cadastroRecordDTO = new CadastroRecordDTO("Nathan", "Mendes", "tralala", "Estrada do Mendanha", "476", "Rj", "12", Dog, Fem, "17");
    }

    @InjectMocks
    private CadastroService cadastroService;
    @Mock
    private CadastroModel cadastroModel;
    @Mock
    private CadastroRecordDTO cadastroRecordDTO;
    @Mock
    private ExceptionsCheck exceptionsCheck;
    @Mock
    private CadastroRepository repository;

    @Test
    void ShouldThrowExceptionWhenAgeIsHigherThan19(){
       cadastroModel.setAge("21");
        AgeHigherThan19Exception exception = assertThrows(AgeHigherThan19Exception.class, () -> exceptionsCheck.CheckExceptionsSave(cadastroRecordDTO, cadastroModel));
    }


    @Test
    void ShouldThrowExceptionWhenWeightIsOutOfBounds(){
        cadastroModel.setWeight("72");
        //cadastroModel.setWeight("-1");
        WeightOutOfBoundsException exception = assertThrows(WeightOutOfBoundsException.class, () -> exceptionsCheck.CheckExceptionsSave(cadastroRecordDTO, cadastroModel));
    }
    @Test
    void ShouldThrowExceptionWhenFirstNameIsEmpty(){
        cadastroModel.setFirstname("");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> exceptionsCheck.CheckExceptionsSave(cadastroRecordDTO, cadastroModel));
    }
    @Test
    void ShouldThrowExceptionWhenFirstNameHasSpecialCharacters(){
        cadastroModel.setFirstname("Nathan@");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> exceptionsCheck.CheckExceptionsSave(cadastroRecordDTO, cadastroModel));
    }
    @Test
    void ShouldThrowExceptionWhenLastNameIsEmpty(){
        cadastroModel.setLastname("");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> exceptionsCheck.CheckExceptionsSave(cadastroRecordDTO, cadastroModel));
    }
    @Test
    void ShouldThrowExceptionWhenLastNameHasSpecialCharacters(){
        cadastroModel.setLastname("Mendes@");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> exceptionsCheck.CheckExceptionsSave(cadastroRecordDTO, cadastroModel));
    }
    @Test
    void ShouldThrowExceptionWhenGenderIsEmpty(){
        cadastroModel.setGender(null);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> exceptionsCheck.CheckExceptionsSave(cadastroRecordDTO, cadastroModel));
    }
    @Test
    void ShouldThrowExceptionWhenRaceIsInvalid(){
        cadastroModel.setRace("181");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> exceptionsCheck.CheckExceptionsSave(cadastroRecordDTO, cadastroModel));
    }
    @Test
    void ShouldThrowExceptionWhenNumberIsInvalid(){
        cadastroModel.setNumber("aaa");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> exceptionsCheck.CheckExceptionsSave(cadastroRecordDTO, cadastroModel));
    }
    @Test
    void ShouldThrowExceptionWhenCityIsEmptyOrHasSpecialCharacters() {
        cadastroModel.setCity("");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> exceptionsCheck.CheckExceptionsSave(cadastroRecordDTO, cadastroModel));
    }
    @Test
    void ShouldThrowExceptionWhenThereAreMoreThan2FiltersActive(){
        int activeFilters = 3;
        FilterMaxQuantityException exception = assertThrows(FilterMaxQuantityException.class, () -> exceptionsCheck.CheckExceptionsGet(activeFilters));
    }
    @Test
    void ShouldThrowExceptionWhenTriesToChangeTheSexOrTheTypeOfThePet(){
        MascOrFem sex = Masc;
        CatOrDog type = Cat;
        InvalidPutMethodException exception = assertThrows(InvalidPutMethodException.class, () -> exceptionsCheck.CheckPutMethodException(type, sex, cadastroRecordDTO));
    }
    @AfterEach
    void tearDown() {
        cadastroModel.setAge("18");
        cadastroModel.setWeight("32");
        cadastroModel.setFirstname("Nathan");
        cadastroModel.setLastname("Mendes");
        cadastroModel.setAddress("Estrada do Mendanha");
        cadastroModel.setNumber("476");
        cadastroModel.setCity("Rj");
        cadastroModel.setGender(Masc);
        cadastroModel.setType(Dog);
        cadastroModel.setRace("Tralala");
        cadastroModel.setAge("12");
    }

}