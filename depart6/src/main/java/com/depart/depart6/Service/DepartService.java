package com.depart.depart6.Service;

import com.depart.depart6.Entity.Depart;
import com.depart.depart6.Repository.DepartRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartService {

    private DepartRepository departRepository;

    public DepartService(DepartRepository departRepository) {
        this.departRepository = departRepository;
    }

    public List<Depart> getAll(){
        return departRepository.findAll();

    }

    public Depart save(Depart depart){
        return departRepository.save(depart);
    }
}
