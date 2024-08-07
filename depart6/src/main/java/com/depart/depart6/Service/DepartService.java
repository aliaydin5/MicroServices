package com.depart.depart6.Service;

import com.depart.depart6.Dto.RabbitMessage;
import com.depart.depart6.Entity.Depart;
import com.depart.depart6.Entity.File;
import com.depart.depart6.Entity.Product;
import com.depart.depart6.Entity.UsersFile;
import com.depart.depart6.Repository.DepartRepository;
import com.depart.depart6.Repository.ProductRepo;
import com.depart.depart6.Repository.UsersFileRepo;
import lombok.NoArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.xpath.XPath;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@EnableRabbit
public class DepartService {
    @Autowired
    private DepartRepository departRepository;

    @Autowired
    private UsersFileRepo usersFileRepo;

    @Autowired
    private ProductRepo productRepo;
    private final Path pt= Paths.get("uploads");



    public void make(){

        try {
            Files.createDirectories(pt);

        }catch (Exception e){

        }
    }

    public List<Depart> getAll(){
        return departRepository.findAll();

    }

    public Depart save(Depart depart){
        return departRepository.save(depart);
    }


    public String saveImage(MultipartFile multipartFile,long id){

        try {
            Path pth=this.pt.resolve(multipartFile.getOriginalFilename());
            Files.copy(multipartFile.getInputStream(), pth);
            File file=new File();
            file.setPath(pth.toString());
            UsersFile usersFile=new UsersFile();
            usersFile.setUserId(id);
            usersFile.setFileId(file.getId());
            usersFileRepo.save(usersFile);
            return new String("Dosya kaydedildi");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /*@RabbitListener(queues = "Mobile")
    public void getRabbitMQMessage(RabbitMessage rabbitMessage){
        Product product=productRepo.findByName(rabbitMessage.getName());
        int urunsayısı= product.getUrunsayisi()- rabbitMessage.getId();
        product.setUrunsayisi(urunsayısı);
        productRepo.save(product);

        System.out.println("Name: " + rabbitMessage.getName() + ", ID: " + rabbitMessage.getId());

    }*/


    public Product saveProduct(Product product){

        Product product1=productRepo.save(product);
        return product1;
    }
}
