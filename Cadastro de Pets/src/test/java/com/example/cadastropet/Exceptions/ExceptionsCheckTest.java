package com.example.cadastropet.Exceptions;

import com.example.cadastropet.Enum.CatOrDog;
import com.example.cadastropet.Enum.MascOrFem;
import com.example.cadastropet.Model.CadastroModel;
import com.example.cadastropet.Repository.CadastroRepository;
import com.example.cadastropet.Service.CadastroService;
import com.example.cadastropet.dtos.CadastroRecordDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.cadastropet.Enum.CatOrDog.Cat;
import static com.example.cadastropet.Enum.CatOrDog.Dog;
import static com.example.cadastropet.Enum.MascOrFem.Masc;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ExceptionsCheckTest {

    @BeforeEach
    void setUp() {
        exceptionsCheck = new ExceptionsCheck();
        cadastroModel = new CadastroModel(1L, "Tralala", "22", "11", "nathan", "Car", "12", "Rio de Janeiro", "Rua da Paixão", Dog, Masc);
    }

    @InjectMocks
    private CadastroService cadastroService;
    @Mock
    private CadastroModel cadastroModel;
    @Mock
    private ExceptionsCheck exceptionsCheck;
    @Mock
    private CadastroRecordDTO dto = new CadastroRecordDTO("Nathan", "Mendes", "tralala", "Estrada do Mendanha", "476", "Rj", "12", Dog, Masc, "15");
    @Mock
    private CadastroRepository repository;

    @Test
    void ShouldThrowExceptionWhenAgeIsHigherThan19(){
        CadastroRecordDTO dto = new CadastroRecordDTO("Carlos", "Marques", "tralala", "Rua da Paixão", "476", "Rj", "12", Dog, Masc, "20");
        AgeHigherThan19Exception exception = assertThrows(AgeHigherThan19Exception.class, () -> exceptionsCheck.CheckExceptionsSave(dto, cadastroModel));
    }
    @Test
    void ShouldThrowExceptionWhenWeightIsOutOfBounds(){
        CadastroRecordDTO dto = new CadastroRecordDTO("Carlos", "Marques", "tralala", "Estrada da paixão", "476", "Rj", "0.2", Dog, Masc, "18");
        WeightOutOfBoundsException exception = assertThrows(WeightOutOfBoundsException.class, () -> exceptionsCheck.CheckExceptionsSave(dto, cadastroModel));
    }
    @Test
    void ShouldThrowExceptionWhenFirstNameIsEmpty(){
        CadastroRecordDTO dto = new CadastroRecordDTO("", "Marques", "tralala", "Estrada da paixão", "476", "Rj", "12", Dog, Masc, "18");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> exceptionsCheck.CheckExceptionsSave(dto, cadastroModel));
    }
    @Test
    void ShouldThrowExceptionWhenFirstNameHasSpecialCharacters(){
        CadastroRecordDTO dto = new CadastroRecordDTO("Carlos@@2", "Marques", "tralala", "Estrada da paixão", "476", "Rj", "12", Dog, Masc, "18");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> exceptionsCheck.CheckExceptionsSave(dto, cadastroModel));
    }
    @Test
    void ShouldThrowExceptionWhenLastNameIsEmpty(){
        CadastroRecordDTO dto = new CadastroRecordDTO("Carlos", "", "tralala", "Estrada da paixão", "476", "Rj", "12", Dog, Masc, "18");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> exceptionsCheck.CheckExceptionsSave(dto, cadastroModel));
    }
    @Test
    void ShouldThrowExceptionWhenLastNameHasSpecialCharacters(){
        CadastroRecordDTO dto = new CadastroRecordDTO("Carlos", "Marques@@2", "tralala", "Estrada da paixão", "476", "Rj", "12", Dog, Masc, "18");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> exceptionsCheck.CheckExceptionsSave(dto, cadastroModel));
    }
    @Test
    void ShouldThrowExceptionWhenRaceIsInvalid(){
        CadastroRecordDTO dto = new CadastroRecordDTO("Carlos", "Marques", "55", "Estrada da paixão", "476", "Rj", "12", Dog, Masc, "18");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> exceptionsCheck.CheckExceptionsSave(dto, cadastroModel));
    }
    @Test
    void ShouldThrowExceptionWhenNumberIsInvalid(){
        CadastroRecordDTO dto = new CadastroRecordDTO("Carlos", "Marques", "tralala", "Estrada da paixão", "6aa", "Rj", "12", Dog, Masc, "18");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> exceptionsCheck.CheckExceptionsSave(dto, cadastroModel));
    }
    @Test
    void ShouldThrowExceptionWhenCityIsEmptyOrHasSpecialCharacters() {
        CadastroRecordDTO dto = new CadastroRecordDTO("Carlos", "Marques", "tralala", "Estrada da paixão", "6", "", "12", Dog, Masc, "18");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> exceptionsCheck.CheckExceptionsSave(dto, cadastroModel));
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
        InvalidPutMethodException exception = assertThrows(InvalidPutMethodException.class, () -> exceptionsCheck.CheckPutMethodException(type, sex, dto));
    }
}