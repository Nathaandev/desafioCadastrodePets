package com.example.cadastropet.Service;

import com.example.cadastropet.Enum.CatOrDog;
import com.example.cadastropet.Enum.MascOrFem;
import com.example.cadastropet.Exceptions.ExceptionsCheck;
import com.example.cadastropet.Model.CadastroModel;
import com.example.cadastropet.Repository.CadastroRepository;
import com.example.cadastropet.dtos.CadastroRecordDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static com.example.cadastropet.Enum.CatOrDog.Dog;
import static com.example.cadastropet.Enum.MascOrFem.Masc;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CadastroServiceTest {

    @InjectMocks
    private CadastroService cadastroService;

    @Mock
    private CadastroRepository cadastroRepository;

    @Mock
    private CadastroModel cadastroModel;
    @Mock
    private CadastroRecordDTO cadastroRecordDTO;
    @Mock
    private ExceptionsCheck exceptionsCheck;

    @Test
    void FindAll() {
        CadastroModel cadastroModel = new CadastroModel(7L, "Tralala", "52",
                "11", "Nathan", "Carlos", "12",
                "Rio de Janeiro", "Rua da Paixão", Dog, Masc);
        CadastroModel cadastroModel2 = new CadastroModel(7L, "Tralala", "52", "11", "Nathan", "Carlos", "12", "Rio de Janeiro", "Rua da Paixão", Dog, Masc);
        Mockito.when(cadastroRepository.findAll()).thenReturn(Arrays.asList(cadastroModel, cadastroModel2));
        List<CadastroModel> cadastros = cadastroService.getAll().getBody();
        Assertions.assertEquals(2, cadastros.size());
    }
    @Test
    void save(){
        CadastroRecordDTO dto = new CadastroRecordDTO("Nathan", "Mendes", "tralala", "Rua da Paixão", "476", "Rj", "12", Dog, Masc, "12");
        CadastroModel cadastroModel = new CadastroModel(null, "Tralala", "0.2", "11", "Nathan", "Carlos", "12", "Rio de Janeiro", "Rua da paixão", Dog, Masc);
        Mockito.when(cadastroRepository.save(Mockito.any(CadastroModel.class))).thenReturn(cadastroModel);
        var result = cadastroService.save(dto);
        Assertions.assertEquals(cadastroModel, result.getBody());
    }
    @Test
     void delete(){
        Long id = 1L;
        Mockito.when(cadastroRepository.findById(id)).thenReturn(java.util.Optional.ofNullable(cadastroModel));
        cadastroService.DeleteById(id);
        Mockito.verify(cadastroRepository, Mockito.times(1)).deleteById(id);
    }
    @Test
    void update(){
        CadastroRecordDTO dto = new CadastroRecordDTO("Nathan", "Mendes", "tralala", "Rua da Paixão", "476", "Rj", "12", Dog, Masc, "12");
        Long id = 1L;
        Mockito.when(cadastroRepository.findById(id)).thenReturn(java.util.Optional.ofNullable(cadastroModel));
        Mockito.when(cadastroRepository.save(Mockito.any(CadastroModel.class))).thenReturn(cadastroModel);
        var result = cadastroService.Update(id, dto);
        Assertions.assertEquals(cadastroModel, result.getBody());
    }
    @BeforeEach
    void setUp() {
        cadastroModel = new CadastroModel(
                1L,
                "Tralala",
                "52",
                "12",
                "Marcelo",
                "Carvalho",
                "42",
                "Rio de Janeiro",
                "Rua da paixão",
                Dog,
                Masc);
    }

    @Test
    void testGetPetsWithMultipleFilters() {
        Mockito.when(cadastroRepository.findAll()).thenReturn(List.of(cadastroModel));

        ResponseEntity<List<CadastroModel>> response = cadastroService.GetPets(
                1L, "Marcelo", "Carvalho", "Rua da paixão, 42 - Rio de Janeiro", "52", "12", "Masc", "Dog", "Tralala"
        );

        Assertions.assertEquals(1, response.getBody().size());
    }

    @Test
    void testCheckExceptionIsCalled() {
        Mockito.when(cadastroRepository.findAll()).thenReturn(List.of(cadastroModel));

        cadastroService.GetPets(1L, null, null, null, null, null, null, null, null);

        Mockito.verify(exceptionsCheck, Mockito.times(1)).CheckExceptionsGet(Mockito.anyInt());
    }
    @Test
    void testGetPetsWithFirstnameFilter() {
        Mockito.when(cadastroRepository.findAll()).thenReturn(List.of(cadastroModel));
        var response = cadastroService.GetPets(
                null, "Marcelo", null, null, null, null, null, null, null
        );

        Assertions.assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetPetsWithLastnameFilter() {
        Mockito.when(cadastroRepository.findAll()).thenReturn(List.of(cadastroModel));

        var response = cadastroService.GetPets(
                null, null, "Carvalho", null, null, null, null, null, null
        );

        Assertions.assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetPetsWithAddressFilter() {
        Mockito.when(cadastroRepository.findAll()).thenReturn(List.of(cadastroModel));

        var response = cadastroService.GetPets(
                null, null, null, "Rua da Paixão, 42 - Rio de Janeiro", null, null, null, null, null
        );

        Assertions.assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetPetsWithWeightFilter() {
        Mockito.when(cadastroRepository.findAll()).thenReturn(List.of(cadastroModel));

        var response = cadastroService.GetPets(
                null, null, null, null, "52", null, null, null, null
        );

        Assertions.assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetPetsWithAgeFilter() {
        Mockito.when(cadastroRepository.findAll()).thenReturn(List.of(cadastroModel));

        var response = cadastroService.GetPets(
                null, null, null, null, null, "12", null, null, null
        );

        Assertions.assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetPetsWithGenderFilter() {
        Mockito.when(cadastroRepository.findAll()).thenReturn(List.of(cadastroModel));

        var response = cadastroService.GetPets(
                null, null, null, null, null, null, "Masc", null, null
        );

        Assertions.assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetPetsWithTypeFilter() {
        Mockito.when(cadastroRepository.findAll()).thenReturn(List.of(cadastroModel));

        var response = cadastroService.GetPets(
                null, null, null, null, null, null, null, "Dog", null
        );

        Assertions.assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetPetsWithRaceFilter() {
        Mockito.when(cadastroRepository.findAll()).thenReturn(List.of(cadastroModel));

        var response = cadastroService.GetPets(
                null, null, null, null, null, null, null, null, "Tralala"
        );

        Assertions.assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetPetsWithIdFilter() {
        Mockito.when(cadastroRepository.findAll()).thenReturn(List.of(cadastroModel));

        var response = cadastroService.GetPets(
                1L, null, null, null, null, null, null, null, null
        );

        Assertions.assertEquals(1, response.getBody().size());
    }
}