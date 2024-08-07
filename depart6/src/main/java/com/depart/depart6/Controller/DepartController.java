package com.depart.depart6.Controller;

import com.depart.depart6.Entity.Depart;
import com.depart.depart6.Entity.Product;
import com.depart.depart6.Service.DepartService;
import lombok.NoArgsConstructor;
import org.springframework.cloud.openfeign.support.FeignHttpClientProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/depart/v1")
public class DepartController {
    DepartService departService;

    public DepartController(DepartService departService) {
        this.departService = departService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Depart>> getAllDepart(){
        return ResponseEntity.ok().body(departService.getAll());
    }

    @PostMapping("/departSave")
    public  ResponseEntity<Depart> saveDepart(@RequestBody Depart depart){
        return ResponseEntity.ok().body(departService.save(depart));
    }

    @PostMapping("/save/ımage")
    public ResponseEntity<?> saveImage(@RequestBody MultipartFile multipartFile,@RequestBody long id){
        return ResponseEntity.ok().body(departService.saveImage(multipartFile,id));
    }

    @PostMapping("/save/product")
    public ResponseEntity<?> saveProduct(@RequestBody Product product){
        return ResponseEntity.ok().body(departService.saveProduct(product));
    }

}
