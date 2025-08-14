package com.example.cadastropet.Service;
import com.example.cadastropet.Exceptions.ExceptionsCheck;
import com.example.cadastropet.Model.CadastroModel;
import com.example.cadastropet.Repository.CadastroRepository;
import com.example.cadastropet.dtos.CadastroRecordDTO;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.cadastropet.Model.CadastroModel.VALUE_NOT_INFORMED;

@Service
public class CadastroService {
    @Autowired
    CadastroRepository repository;
    ExceptionsCheck exceptionsCheck = new ExceptionsCheck();

    public ResponseEntity<CadastroModel> save(@RequestBody @Valid CadastroRecordDTO cadastroRecordDTO){
        var cadastroModel = new CadastroModel();
        BeanUtils.copyProperties(cadastroRecordDTO, cadastroModel);
        cadastroModel.HandleEmptyFields(cadastroRecordDTO);
        exceptionsCheck.CheckExceptionsSave(cadastroRecordDTO, cadastroModel);
        cadastroModel.setAddress(cadastroModel.GenerateFullAddress());
        cadastroModel.setName(cadastroModel.GenerateName());
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(cadastroModel));
    }
    public ResponseEntity<Object> DeleteById(Long id){
        var pet = repository.findById(id);
        if (pet.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Content deleted successfully");
    }
    public ResponseEntity<Object> Update(@PathVariable("id") Long id, @RequestBody @Valid CadastroRecordDTO cadastroRecordDTO){
        Optional<CadastroModel> pet = repository.findById(id);
        var cadastroModel = new CadastroModel();
        if(pet.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        cadastroModel = pet.get();
        exceptionsCheck.CheckPutMethodException(cadastroModel.getType(), cadastroModel.getGender(), cadastroRecordDTO);
        BeanUtils.copyProperties(cadastroRecordDTO, cadastroModel);
        exceptionsCheck.CheckExceptionsSave(cadastroRecordDTO, cadastroModel);
        cadastroModel.HandleEmptyFields(cadastroRecordDTO);
        cadastroModel.setAddress(cadastroModel.GenerateFullAddress());
        cadastroModel.setName(cadastroModel.GenerateName());
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(cadastroModel));
    }
    public ResponseEntity<List<CadastroModel>> GetPets(Long id, String firstname, String lastname, String address, String weight, String age, String gender, String type, String race){
        int active_filters = 0;
        List<CadastroModel> pets = repository.findAll();
        List<CadastroModel> filters = pets.stream()
                .filter(p -> id == null || p.getPetid().equals(id))
                .filter(p -> firstname == null || (p.getName() != null && p.getName().toLowerCase().contains(firstname.toLowerCase())))
                .filter(p -> lastname == null || (p.getName() != null && p.getName().toLowerCase().contains(lastname.toLowerCase())))
                .filter(p -> address == null || p.getAddress().equalsIgnoreCase(address))
                .filter(p -> weight == null || p.getWeight().equalsIgnoreCase(weight))
                .filter(p -> age == null || p.getAge().equalsIgnoreCase(age))
                .filter(p -> gender == null || p.getGender().name().equalsIgnoreCase(gender))
                .filter(p -> type == null || p.getType().name().equalsIgnoreCase(type))
                .filter(p -> race == null || p.getRace().equalsIgnoreCase(race))


                .collect(Collectors.toList());
        if(id != null){
            active_filters++;
        }
        if(firstname != null){
            active_filters++;
        }
        if(lastname != null){
            active_filters++;
        }
        if(address != null){
            active_filters++;
        }
        if(weight != null && !Objects.equals(weight, VALUE_NOT_INFORMED)){
            active_filters++;
        }
        if(age != null && !Objects.equals(age, VALUE_NOT_INFORMED)){
            active_filters++;
        }
        if(gender != null){
            active_filters++;
        }
        if(type != null){
            active_filters++;
        }
        if (race != null && !Objects.equals(race, VALUE_NOT_INFORMED)) {
            active_filters++;
        }
        exceptionsCheck.CheckExceptionsGet(active_filters);
        return ResponseEntity.ok(filters);

    }
}
